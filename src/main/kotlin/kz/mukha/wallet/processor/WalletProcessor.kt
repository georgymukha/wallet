package kz.mukha.wallet.processor

import kz.mukha.wallet.data.dto.WalletDto
import kz.mukha.wallet.data.dto.toDto
import kz.mukha.wallet.data.jooq.postgres.tables.Wallets.WALLETS
import kz.mukha.wallet.data.jooq.postgres.tables.records.WalletsRecord
import kz.mukha.wallet.domain.wallet.Wallet
import kz.mukha.wallet.domain.wallet.toEntity
import kz.mukha.wallet.supabase.DatabaseConnector
import org.jooq.DSLContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class WalletProcessor {

    private val context: DSLContext = DatabaseConnector.connect()

    @PostMapping("/wallets")
    fun createWallet(@RequestBody wallet: WalletDto): ResponseEntity<String> {
        val walletId = createWalletByWalletDto(wallet)

        return ResponseEntity.status(HttpStatus.CREATED).body("Wallet created successfully with id: $walletId")
    }

    @GetMapping("/wallets")
    fun getUserWallets(@RequestParam("userId") userId: UUID): ResponseEntity<List<WalletDto>> {
        val wallets: List<WalletDto> = getUserWalletsByOwnerId(userId)

        return ResponseEntity.ok(wallets)
    }

    @GetMapping("/wallets/{walletId}")
    fun getWalletDetails(@PathVariable("walletId") walletId: UUID): ResponseEntity<Wallet> {
        val wallet = getWalletById(walletId)

        return if (wallet != null) {
            ResponseEntity.ok(wallet)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/wallets/{walletId}")
    fun updateWallet(@PathVariable("walletId") walletId: UUID, @RequestBody wallet: WalletDto): ResponseEntity<String> {
        val actualRecord = getWalletById(walletId)

        val walletRecord = WalletsRecord(
            walletId,
            wallet.ownerId,
            wallet.walletName,
            wallet.currency,
            wallet.createdAt,
            wallet.updatedAt,
            wallet.saveItems,
        )
        updateWallet(walletRecord)

        return ResponseEntity.ok("Wallet with id: $walletId updated successfully")
    }

    @DeleteMapping("/wallets/{walletId}")
    fun deleteWallet(@PathVariable("walletId") walletId: UUID): ResponseEntity<String> {
        deleteWalletById(walletId)

        return ResponseEntity.ok("Wallet deleted successfully")
    }

    private fun createWalletByWalletDto(wallet: WalletDto): UUID {
        val walletId: UUID = UUID.randomUUID()
        val walletRecord = WalletsRecord(
            walletId,
            wallet.ownerId,
            wallet.walletName,
            wallet.currency,
            wallet.createdAt,
            wallet.updatedAt,
            wallet.saveItems,
        )

        context.insertInto(WALLETS)
            .set(walletRecord)
            .execute()

        return walletRecord.walletId
    }

    private fun getUserWalletsByOwnerId(userId: UUID): List<WalletDto> {
        val wallets = context.selectFrom(WALLETS)
            .where(WALLETS.OWNER_ID.eq(userId))
            .fetch()

        return wallets.map { it.toDto() }
    }

    private fun getWalletById(walletId: UUID): Wallet? {
        val wallet = context.selectFrom(WALLETS)
            .where(WALLETS.WALLET_ID.eq(walletId))
            .fetchOne()

        return wallet?.toEntity()
    }

    private fun updateWallet(wallet: WalletsRecord) {
        context.update(WALLETS)
            .set(wallet)
            .where(WALLETS.WALLET_ID.eq(wallet.walletId))
            .execute()
    }

    private fun deleteWalletById(walletId: UUID) {
        context.deleteFrom(WALLETS)
            .where(WALLETS.WALLET_ID.eq(walletId))
            .execute()
    }
}