package kz.mukha.wallet.domain

import kz.mukha.wallet.data.ItemTypeEnum
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class TransactionItem(
    val transactionItemId: UUID,
    val transactionId: UUID,
    val itemType: ItemTypeEnum,
    val commoditySum: BigDecimal,
    val quantity: BigDecimal,
    val price: BigDecimal,
    val itemName: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)