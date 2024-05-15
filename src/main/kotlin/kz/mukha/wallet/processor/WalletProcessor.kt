package kz.mukha.wallet.processor

import kz.mukha.wallet.data.dto.WalletDto
import kz.mukha.wallet.data.jooq.postgres.tables.Wallets.WALLETS
import kz.mukha.wallet.data.jooq.postgres.tables.records.WalletsRecord
import kz.mukha.wallet.domain.wallet.Wallet
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

    @PostMapping("/wallet")
    fun createWallet(@RequestBody wallet: WalletDto): ResponseEntity<String> {
        TODO("Логика создания нового кошелька")
        // нужно создать новый кошелек в базе данных и вернуть успешный ответ или сообщение об ошибке

        val walletId = UUID.randomUUID().toString()
        return ResponseEntity.status(HttpStatus.CREATED).body("Wallet created successfully with id: $walletId")
    }

    @GetMapping("/wallets")
    fun getUserWallets(@RequestParam("userId") userId: UUID): ResponseEntity<List<Wallet>> {
        TODO("Логика получения списка кошельков пользователя")
        // нужно извлечь все кошельки пользователя из базы данных и вернуть их в ответе

        val wallets: List<Wallet> = listOf()
        return ResponseEntity.ok(wallets)
    }

    @GetMapping("/wallet/{walletId}")
    fun getWalletDetails(@PathVariable("walletId") walletId: UUID): ResponseEntity<Wallet> {
        TODO("Логика получения информации о конкретном кошельке")
        // нужно извлечь информацию о конкретном кошельке из базы данных и вернуть её в ответе

        val wallet: Wallet? = null
        return if (wallet != null) {
            ResponseEntity.ok(wallet)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/wallet/{walletId}")
    fun updateWallet(@PathVariable("walletId") walletId: UUID, @RequestBody wallet: WalletDto): ResponseEntity<String> {
        TODO("Логика обновления информации о кошельке")
        // нужно обновить информацию о кошельке в базе данных и вернуть успешный ответ или сообщение об ошибке

        return ResponseEntity.ok("Wallet updated successfully")
    }

    @DeleteMapping("/wallet/{walletId}")
    fun deleteWallet(@PathVariable("walletId") walletId: UUID): ResponseEntity<String> {
        TODO("Логика удаления кошелька")
        // нужно удалить кошелек из базы данных и вернуть успешный ответ или сообщение об ошибке

        return ResponseEntity.ok("Wallet deleted successfully")
    }

    private fun createWallet(wallet: WalletsRecord) {
        context.insertInto(WALLETS)
            .set(wallet)
            .execute()
    }

    private fun getUserWalletsByOwnerId(userId: UUID): List<WalletsRecord> {
        return context.selectFrom(WALLETS)
            .where(WALLETS.OWNER_ID.eq(userId))
            .fetch()
    }

    private fun getWalletById(walletId: UUID): WalletsRecord? {
        return context.selectFrom(WALLETS)
            .where(WALLETS.WALLET_ID.eq(walletId))
            .fetchOne()
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