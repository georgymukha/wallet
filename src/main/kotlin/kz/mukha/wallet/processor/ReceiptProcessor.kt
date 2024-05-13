package kz.mukha.wallet.processor

import kotlinx.serialization.json.Json
import kz.mukha.wallet.data.Receipt
import kz.mukha.wallet.data.jooq.postgres.tables.TransactionItems.TRANSACTION_ITEMS
import kz.mukha.wallet.data.jooq.postgres.tables.Transactions.TRANSACTIONS
import kz.mukha.wallet.data.jooq.postgres.tables.records.TransactionItemsRecord
import kz.mukha.wallet.data.jooq.postgres.tables.records.TransactionsRecord
import kz.mukha.wallet.domain.Transaction
import kz.mukha.wallet.domain.TransactionItem
import kz.mukha.wallet.domain.toItems
import kz.mukha.wallet.domain.toTransaction
import kz.mukha.wallet.supabase.DatabaseConnector
import org.jooq.DSLContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.HttpURLConnection
import java.net.URL
import java.util.UUID

@RestController
class ReceiptProcessor {

    private val context: DSLContext = DatabaseConnector.connect()
    private val jsonTool = Json {
        ignoreUnknownKeys = true
    }

    @GetMapping("/process-receipt")
    fun processReceipt(
        @RequestParam("i") receiptNumber: String,
        @RequestParam("f") rnk: String,
        @RequestParam("s") sum: String,
        @RequestParam("t") timestamp: String,
    ): ResponseEntity<String> {
        val initialUrl = "https://consumer.oofd.kz/?i=$receiptNumber&f=$rnk&s=$sum&t=$timestamp"

        val connection = URL(initialUrl).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.instanceFollowRedirects = false

        try {
            connection.connect()

            if (connection.responseCode == HttpStatus.FOUND.value()) {
                val location = connection.getHeaderField("Location")
                if (location!= null) {
                    val uuid = location.replace("/ticket/", "")
                    val ticketUrl = "https://consumer.oofd.kz/api/tickets/ticket/$uuid"
                    val responseEntity = getTicketDetails(ticketUrl)

                    if (responseEntity.statusCode.value() == HttpStatus.OK.value()) {
                        val receipt: Receipt = responseEntity.body!!
                        return try {
                            val transaction = processAndSaveReceipt(receipt)
                            ResponseEntity.ok("Successfully processed receipt. Transaction ID: ${transaction.transactionId}")
                        } catch (e: Exception) {
                            e.printStackTrace()
                            ResponseEntity.badRequest().body("Error processing receipt: ${e.message}")
                        }
                    }
                }
            }
        } finally {
            connection.disconnect()
        }

        return ResponseEntity.badRequest().build()
    }

    private fun getTicketDetails(
        url: String
    ): ResponseEntity<Receipt> {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.instanceFollowRedirects = false

        try {
            connection.connect()

            if (connection.responseCode == HttpStatus.OK.value()) {
                val response = connection.inputStream.bufferedReader().readText()
                val receipt: Receipt = jsonTool.decodeFromString(response)
                return ResponseEntity.ok(receipt)
            }
        } finally {
            connection.disconnect()
        }

        return ResponseEntity.badRequest().build()
    }

    private fun processAndSaveReceipt(receipt: Receipt): Transaction {
        // TODO: настроить получение walletId, например из JWT токена
        val walletId = UUID.fromString("e7dac401-5a1b-4eb0-9004-372f564daab8")
        val transaction = receipt.toTransaction(walletId)
        val items = receipt.toItems(transaction.transactionId)
        saveTransaction(transaction)
        saveItems(items)
        return transaction
    }

    private fun saveTransaction(transaction: Transaction) {
        val tr: TransactionsRecord = TransactionsRecord(
            transaction.transactionId,
            transaction.walletId,
            transaction.amount,
            transaction.operationType.toString(),
            transaction.description,
            transaction.transactionDate,
            transaction.createdAt,
            transaction.updatedAt,
            transaction.orgTitle,
            transaction.retailPlaceAddress,
            transaction.foundDate,
            transaction.kkmFnsId,
            transaction.fiscalId,
            transaction.totalSum,
        )

        context.startTransaction()
        val transactionId = context.insertInto(TRANSACTIONS)
            .set(tr)
            .returning(TRANSACTIONS.TRANSACTION_ID)
            .execute()
        context.commit()
    }

    private fun saveItems(items: List<TransactionItem>) {
        context.startTransaction()
        for (item in items) {
            val tr: TransactionItemsRecord = TransactionItemsRecord(
                item.transactionItemId,
                item.transactionId,
                item.commoditySum,
                item.quantity,
                item.price,
                item.itemName,
                item.createdAt,
                item.updatedAt,
                item.itemType.toString(),
            )

            context.insertInto(TRANSACTION_ITEMS)
                .set(tr)
                .returning(TRANSACTION_ITEMS.TRANSACTION_ITEM_ID)
                .execute()
        }
        context.commit()
    }
}