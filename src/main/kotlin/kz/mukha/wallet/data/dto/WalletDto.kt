package kz.mukha.wallet.data.dto

import java.time.LocalDateTime
import java.util.UUID

data class WalletDto(
    val walletId: UUID,
    val ownerId: UUID,
    val walletName: String,
    val currency: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val saveItems: Boolean
)