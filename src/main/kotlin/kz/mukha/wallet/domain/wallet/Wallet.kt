package kz.mukha.wallet.domain.wallet

import java.time.LocalDateTime
import java.util.UUID

data class Wallet(
    val walletId: UUID,
    val ownerId: UUID,
    val walletName: String,
    val currency: String = "398", // Код валюты по умолчанию
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val saveItems: Boolean = true
)