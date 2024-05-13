package kz.mukha.wallet.data

enum class OperationTypeEnum(val code: Int, val viewableName: String, val type: OperationType) {
    // действия относительно ККМ, поэтому идем от обратного для пользователя
    OPERATION_BUY(0, "Продажа", OperationType.INCOME),
    OPERATION_BUY_RETURN(1, "Возврат продажи", OperationType.EXPENSE),
    OPERATION_SELL(2, "Покупка", OperationType.EXPENSE),
    OPERATION_SELL_RETURN(3, "Возврат покупки", OperationType.INCOME);
}

enum class OperationType {
    EXPENSE,
    INCOME
}

enum class ItemTypeEnum(val code: Int, val viewableName: String) {
    ITEM_TYPE_COMMODITY(1, "Предмет потребления"),
    ITEM_TYPE_MARKUP(3, "Наценка"),
    ITEM_TYPE_DISCOUNT(5, "Скидка"),
}

enum class PaymentTypeEnum(val code: Int, val viewableName: String) {
    CASH(0, "Наличные"),
    CARD(1, "Банковская карта"),
    CREDIT(2, "Оплата в кредит"),
    TARE(3, "Оплата тарой")
}

enum class PermissionTypeEnum {
    WRITE,
    READ,
    ADMIN
}