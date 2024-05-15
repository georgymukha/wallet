package kz.mukha.wallet.data.dto

import kz.mukha.wallet.data.Receipt
import kz.mukha.wallet.data.jooq.postgres.tables.records.WalletsRecord
import java.time.LocalDateTime
import java.util.UUID

data class WalletDto(
    val walletId: UUID?,
    val ownerId: UUID,
    val walletName: String,
    val currency: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val saveItems: Boolean
)

fun WalletsRecord.toDto(): WalletDto {
    return WalletDto(
        this.walletId,
        this.ownerId,
        this.walletName,
        this.currency,
        this.createdAt,
        this.updatedAt,
        this.saveItems
    )
}