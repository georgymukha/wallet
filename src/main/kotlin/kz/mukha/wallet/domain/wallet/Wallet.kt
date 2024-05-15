package kz.mukha.wallet.domain.wallet

import kz.mukha.wallet.data.dto.WalletDto
import kz.mukha.wallet.data.jooq.postgres.tables.records.WalletsRecord
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

//fun WalletDto.toEntity() = Wallet(
//    this.walletId,
//    this.ownerId,
//    this.walletName,
//    this.currency,
//    this.createdAt,
//    this.updatedAt,
//    this.saveItems
//)

fun WalletsRecord.toEntity() = Wallet(
    this.walletId,
    this.ownerId,
    this.walletName,
    this.currency,
    this.createdAt,
    this.updatedAt,
    this.saveItems
)