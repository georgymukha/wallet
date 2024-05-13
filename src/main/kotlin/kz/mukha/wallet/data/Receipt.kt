package kz.mukha.wallet.data

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kz.mukha.wallet.serializers.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class Receipt(
    val ticket: Ticket,
    val ticketDescription: String,
    val orgTitle: String,
    val orgId: String,
    val retailPlaceAddress: String,
    val kkmSerialNumber: String,
    val kkmFnsId: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val foundDate: LocalDateTime,
    @SerialName("taxes")
    val taxesTotal: List<TaxTotal?>,
    val totalDiscount: Double,
    val totalMarkup: Double,
    val measureUnits: Map<String, String>,
    @SerialName("is_for_anon")
    val isForAnon: Boolean,
)

@Serializable
data class Ticket(
    val type: String,
    val transactionId: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val transactionDate: LocalDateTime,
    val fiscalId: String,
    val shiftNumber: Int,
    val protocolVersion: Int,
    val options: Options,
    val kkmRequestId: Int,
    val linkedKkmRequestId: Long?,
    @Serializable(with = LocalDateTimeSerializer::class)
    val linkedKkmRequestTime: LocalDateTime?,
    val domain: Domain,
    val totalSum: Double,
    val operationType: Int,
    val operator: Operator,
    val takenSum: Double,
    val changeSum: Double,
    val items: List<Item>,
    val payments: List<Payment>,
    val taxes: List<Tax?>,
//    val ticketAdInfo: List<String?>,
    val shiftDocumentNumber: Long?,
//    val extensionAuxiliaries: Map<String?, String?>?,
//    val modifiers: List<String>,
    val requestPayload: String?,
    val payload: String?
)

@Serializable
data class Options(
    val printedDocumentNumber: String
)

@Serializable
data class Domain(
    val domainType: Int
)

@Serializable
data class Operator(
    val code: Int,
    val name: String
)

@Serializable
data class Item(
    val itemType: Int,
    val commodity: Commodity,
//    val options: Map<String, String>,
//    val modifiers: List<@Contextual Any>
)

@Serializable
data class Commodity(
    val sum: Double,
    val sectionCode: String,
    val quantity: Double,
    val price: Double,
    val exciseStamp: String,
    val physicalLabel: String,
    val productId: String,
    @Contextual
    val barcode: Any?,
    val measureUnitCode: String,
    val code: Int,
    val taxes: List<Tax?>,
    val name: String
)

@Serializable
data class Tax(
    val sum: Double,
    val inTotalSum: Boolean,
    val layout: TaxLayout
)

@Serializable
data class TaxLayout(
    val type: String,
    val rate: Double
)

@Serializable
data class TaxTotal(
    val sum: Double,
    val rate: Double,
    val name: String
)

@Serializable
data class Payment(
    val sum: Double,
    val paymentType: String
)

fun main() {
    val jsonString = "{\"ticket\":{\"type\":\"TICKET\",\"transactionId\":\"394052_166389\",\"transactionDate\":\"2024-05-11T13:35:02.000\",\"fiscalId\":\"2173254610\",\"shiftNumber\":0,\"protocolVersion\":0,\"options\":{\"printedDocumentNumber\":\"166287\"},\"kkmRequestId\":0,\"linkedKkmRequestId\":null,\"linkedKkmRequestTime\":null,\"domain\":{\"domainType\":0},\"totalSum\":2580,\"operationType\":2,\"operator\":{\"code\":9,\"name\":\"Касса 1у\"},\"takenSum\":0,\"changeSum\":0,\"items\":[{\"itemType\":1,\"commodity\":{\"sum\":1290,\"sectionCode\":\"1\",\"quantity\":1,\"price\":1290,\"exciseStamp\":\"\",\"physicalLabel\":\"\",\"productId\":\"0\",\"barcode\":null,\"measureUnitCode\":\"796\",\"code\":0,\"taxes\":[{\"sum\":138.21,\"inTotalSum\":true,\"layout\":{\"type\":\"НДС\",\"rate\":0.12}}],\"name\":\"Штрих мебельный (блистер), Белый, 002\"},\"options\":{},\"modifiers\":[]},{\"itemType\":1,\"commodity\":{\"sum\":1290,\"sectionCode\":\"1\",\"quantity\":1,\"price\":1290,\"exciseStamp\":\"\",\"physicalLabel\":\"\",\"productId\":\"0\",\"barcode\":null,\"measureUnitCode\":\"796\",\"code\":0,\"taxes\":[{\"sum\":138.21,\"inTotalSum\":true,\"layout\":{\"type\":\"НДС\",\"rate\":0.12}}],\"name\":\"Паста реставрационная (блистер), Белый, 002\"},\"options\":{},\"modifiers\":[]}],\"payments\":[{\"sum\":2580,\"paymentType\":\"CARD\"}],\"taxes\":[],\"ticketAdInfo\":[],\"shiftDocumentNumber\":null,\"extensionAuxiliaries\":{},\"modifiers\":[],\"requestPayload\":null,\"payload\":null},\"ticketDescription\":\"transaction.ticket.sell, transaction.ticket.payment.type.card\",\"orgTitle\":\"ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \\\"МЕГАСТРОЙ АСТАНА DIY\\\"\",\"orgId\":\"090840015598\",\"retailPlaceAddress\":\"р-он Байконыр, Альмухана Сембинова 19/1\",\"kkmSerialNumber\":\"SWK00100844\",\"kkmFnsId\":\"010100344201\",\"foundDate\":\"2024-05-12T13:21:31.041\",\"taxes\":[{\"rate\":0.12,\"name\":\"\",\"sum\":276.42}],\"totalDiscount\":0,\"totalMarkup\":0,\"measureUnits\":{\"796\":\"шт \"},\"is_for_anon\":true}"
    val json = Json {
        ignoreUnknownKeys = true
    }

    val receipt: Receipt = json.decodeFromString(jsonString)
    println(receipt)
}