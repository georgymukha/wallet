package kz.mukha.wallet.domain

import kz.mukha.wallet.data.ItemTypeEnum
import kz.mukha.wallet.data.OperationType
import kz.mukha.wallet.data.OperationTypeEnum
import kz.mukha.wallet.data.Receipt
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Transaction(
    val transactionId: UUID,
    val walletId: UUID,
    val operationType: OperationType,
    val description: String?,
    val transactionDate: LocalDateTime,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val orgTitle: String?,
    val retailPlaceAddress: String?,
    val foundDate: LocalDateTime?,
    val kkmFnsId: String?,
    val fiscalId: String?,
    val totalSum: BigDecimal?
)

fun Receipt.toTransaction(walletId: UUID): Transaction {
    return Transaction(
        transactionId = UUID.randomUUID(),
        walletId = walletId,
        operationType = if (this.ticket.operationType == 2) OperationType.EXPENSE else OperationType.INCOME,
        description = this.ticketDescription,
        transactionDate = this.ticket.transactionDate,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        orgTitle = this.orgTitle,
        retailPlaceAddress = this.retailPlaceAddress,
        foundDate = this.foundDate,
        kkmFnsId = this.kkmFnsId,
        fiscalId = this.ticket.fiscalId,
        totalSum = this.ticket.totalSum,
    )
}

fun Receipt.toItems(transactionId: UUID): List<TransactionItem> {

//    val transactionItemId: UUID
//    val transactionId: UUID
//    val itemType: ItemTypeEnum
//    val commoditySum: BigDecimal
//    val quantity: BigDecimal
//    val price: BigDecimal
//    val itemName: String
//    val createdAt: LocalDateTime
//    val updatedAt: LocalDateTime


    return this.ticket.items.map {
        val itemType = ItemTypeEnum.entries.find { enum -> enum.code == it.itemType }
        TransactionItem(
            transactionItemId = UUID.randomUUID(),
            transactionId = transactionId,
            itemType = itemType!!,
            commoditySum = it.commodity.sum,
            quantity = it.commodity.quantity,
            price = it.commodity.price,
            itemName = it.commodity.name,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

}