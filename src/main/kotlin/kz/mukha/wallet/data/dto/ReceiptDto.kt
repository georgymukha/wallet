package kz.mukha.wallet.data.dto

import kz.mukha.wallet.data.Commodity
import kz.mukha.wallet.data.Domain
import kz.mukha.wallet.data.Item
import kz.mukha.wallet.data.ItemTypeEnum
import kz.mukha.wallet.data.OperationTypeEnum
import kz.mukha.wallet.data.Operator
import kz.mukha.wallet.data.Options
import kz.mukha.wallet.data.Payment
import kz.mukha.wallet.data.PaymentTypeEnum
import kz.mukha.wallet.data.Receipt
import kz.mukha.wallet.data.Tax
import kz.mukha.wallet.data.TaxLayout
import kz.mukha.wallet.data.TaxTotal
import kz.mukha.wallet.data.Ticket
import java.time.LocalDateTime
import java.util.UUID

data class ReceiptDto(
    val id: UUID,
    val ticket: TicketDto,
    val orgTitle: String,
    val retailPlaceAddress: String,
    val foundDate: LocalDateTime,
    val kkmFnsId: String,
)

data class TicketDto(
    val transactionDate: LocalDateTime,
    val fiscalId: String,
    val totalSum: Double,
    val operationType: Int,
    val operationTypeEnum: OperationTypeEnum = OperationTypeEnum.entries[operationType],
    val items: List<ItemDto>,
    val payments: List<PaymentDto>,
)

data class ItemDto(
    val itemType: Int,
    val commodity: CommodityDto,
    val itemTypeEnum: ItemTypeEnum = ItemTypeEnum.entries[itemType],
)

data class CommodityDto(
    val sum: Double,
    val quantity: Double,
    val price: Double,
//    val measureUnitCode: String,
    val name: String,
)

data class PaymentDto(
    val sum: Double,
    val paymentType: PaymentTypeEnum,
)

fun Receipt.toDto(): ReceiptDto {
    return ReceiptDto(
        id = UUID.randomUUID(),
        ticket = TicketDto(
            transactionDate = this.ticket.transactionDate,
            fiscalId = this.ticket.fiscalId,
            totalSum = this.ticket.totalSum,
            operationType = this.ticket.operationType,
            items = this.ticket.items.map { it.toDto() },
            payments = this.ticket.payments.map { it.toDto() },
        ),
        orgTitle = orgTitle,
        retailPlaceAddress = retailPlaceAddress,
        foundDate = foundDate,
        kkmFnsId = kkmFnsId,
    )
}

fun Item.toDto(): ItemDto {
    return ItemDto(
        itemType = this.itemType,
        commodity = this.commodity.toDto(),
    )
}

fun Commodity.toDto(): CommodityDto {
    return CommodityDto(
        sum = this.sum,
        quantity = this.quantity,
        price = this.price,
//        measureUnitCode = this.measureUnitCode,
        name = this.name,
    )
}

fun Payment.toDto(): PaymentDto {
    val paymentTypeStr = PaymentTypeEnum.valueOf(this.paymentType)

    return PaymentDto(
        sum = this.sum,
        paymentType = paymentTypeStr,
    )
}

fun main() {
    val receipt = Receipt(
        ticket= Ticket(
            type="TICKET",
            transactionId="394052_166389",
            transactionDate= LocalDateTime.parse("2024-05-11T13:35:02"),
            fiscalId="2173254610",
            shiftNumber=0,
            protocolVersion=0,
            options= Options(
                printedDocumentNumber="166287"
            ),
            kkmRequestId=0,
            linkedKkmRequestId=null,
            linkedKkmRequestTime=null,
            domain= Domain(domainType=0),
            totalSum=2580.0,
            operationType=2,
            operator= Operator(code=9, name="Касса 1у"),
            takenSum=0.0,
            changeSum=0.0,
            items= listOf(
                Item(itemType=1, commodity= Commodity(sum=1290.0, sectionCode="1", quantity=1.00, price=1290.0,exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(
                    Tax(sum=138.21, inTotalSum=true, layout= TaxLayout(type="НДС", rate=0.12))
                ), name="Штрих мебельный (блистер), Белый, 002")),
                Item(itemType=1, commodity=Commodity(sum=1290.0, sectionCode="1", quantity=1.00, price=1290.0, exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(
                    Tax(sum=138.21, inTotalSum=true, layout= TaxLayout(type="НДС", rate=0.12))
                ), name="Паста реставрационная (блистер), Белый, 002"))
            ),
            payments= listOf(Payment(sum=2580.0, paymentType="CARD")),
            taxes= listOf(), shiftDocumentNumber=null, requestPayload=null, payload=null),
        ticketDescription="transaction.ticket.sell, transaction.ticket.payment.type.card",
        orgTitle="ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"МЕГАСТРОЙ АСТАНА DIY\"", orgId="090840015598",
        retailPlaceAddress="р-он Байконыр, Альмухана Сембинова 19/1", kkmSerialNumber="SWK00100844",
        kkmFnsId="010100344201", foundDate=LocalDateTime.parse("2024-05-12T13:21:31.041"),
        taxesTotal= listOf(TaxTotal(sum=276.42, rate=0.12, name="")), totalDiscount=0.0, totalMarkup=0.0,
        measureUnits= mapOf("796" to "шт "), isForAnon=true
    )
    println(receipt.toDto())
}