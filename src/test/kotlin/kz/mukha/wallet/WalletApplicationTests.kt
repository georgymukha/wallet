package kz.mukha.wallet

import kotlinx.serialization.json.Json
import kz.mukha.wallet.data.Commodity
import kz.mukha.wallet.data.Domain
import kz.mukha.wallet.data.Item
import kz.mukha.wallet.data.Operator
import kz.mukha.wallet.data.Options
import kz.mukha.wallet.data.Payment
import kz.mukha.wallet.data.PaymentTypeEnum
import kz.mukha.wallet.data.Receipt
import kz.mukha.wallet.data.Tax
import kz.mukha.wallet.data.TaxLayout
import kz.mukha.wallet.data.TaxTotal
import kz.mukha.wallet.data.Ticket
import kz.mukha.wallet.data.dto.CommodityDto
import kz.mukha.wallet.data.dto.ItemDto
import kz.mukha.wallet.data.dto.PaymentDto
import kz.mukha.wallet.data.dto.ReceiptDto
import kz.mukha.wallet.data.dto.TicketDto
import kz.mukha.wallet.data.dto.toDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.UUID

@SpringBootTest
class WalletApplicationTests {

	@Test
	fun receiptDeserializationTest() {
		val jsonReceipt = "{\"ticket\":{\"type\":\"TICKET\",\"transactionId\":\"394052_166389\",\"transactionDate\":\"2024-05-11T13:35:02.000\",\"fiscalId\":\"2173254610\",\"shiftNumber\":0,\"protocolVersion\":0,\"options\":{\"printedDocumentNumber\":\"166287\"},\"kkmRequestId\":0,\"linkedKkmRequestId\":null,\"linkedKkmRequestTime\":null,\"domain\":{\"domainType\":0},\"totalSum\":2580,\"operationType\":2,\"operator\":{\"code\":9,\"name\":\"Касса 1у\"},\"takenSum\":0,\"changeSum\":0,\"items\":[{\"itemType\":1,\"commodity\":{\"sum\":1290,\"sectionCode\":\"1\",\"quantity\":1,\"price\":1290,\"exciseStamp\":\"\",\"physicalLabel\":\"\",\"productId\":\"0\",\"barcode\":null,\"measureUnitCode\":\"796\",\"code\":0,\"taxes\":[{\"sum\":138.21,\"inTotalSum\":true,\"layout\":{\"type\":\"НДС\",\"rate\":0.12}}],\"name\":\"Штрих мебельный (блистер), Белый, 002\"},\"options\":{},\"modifiers\":[]},{\"itemType\":1,\"commodity\":{\"sum\":1290,\"sectionCode\":\"1\",\"quantity\":1,\"price\":1290,\"exciseStamp\":\"\",\"physicalLabel\":\"\",\"productId\":\"0\",\"barcode\":null,\"measureUnitCode\":\"796\",\"code\":0,\"taxes\":[{\"sum\":138.21,\"inTotalSum\":true,\"layout\":{\"type\":\"НДС\",\"rate\":0.12}}],\"name\":\"Паста реставрационная (блистер), Белый, 002\"},\"options\":{},\"modifiers\":[]}],\"payments\":[{\"sum\":2580,\"paymentType\":\"CARD\"}],\"taxes\":[],\"ticketAdInfo\":[],\"shiftDocumentNumber\":null,\"extensionAuxiliaries\":{},\"modifiers\":[],\"requestPayload\":null,\"payload\":null},\"ticketDescription\":\"transaction.ticket.sell, transaction.ticket.payment.type.card\",\"orgTitle\":\"ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \\\"МЕГАСТРОЙ АСТАНА DIY\\\"\",\"orgId\":\"090840015598\",\"retailPlaceAddress\":\"р-он Байконыр, Альмухана Сембинова 19/1\",\"kkmSerialNumber\":\"SWK00100844\",\"kkmFnsId\":\"010100344201\",\"foundDate\":\"2024-05-12T13:21:31.041\",\"taxes\":[{\"rate\":0.12,\"name\":\"\",\"sum\":276.42}],\"totalDiscount\":0,\"totalMarkup\":0,\"measureUnits\":{\"796\":\"шт \"},\"is_for_anon\":true}"

		val expectedReceipt = Receipt(
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
				totalSum="2580.000".toBigDecimal(),
				operationType=2,
				operator= Operator(code=9, name="Касса 1у"),
				takenSum="0.000".toBigDecimal(),
				changeSum="0.000".toBigDecimal(),
				items= listOf(
					Item(itemType=1, commodity= Commodity(sum="1290.000".toBigDecimal(), sectionCode="1", quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(),exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(Tax(sum="138.210".toBigDecimal(), inTotalSum=true, layout= TaxLayout(type="НДС", rate=0.12))), name="Штрих мебельный (блистер), Белый, 002")),
					Item(itemType=1, commodity=Commodity(sum="1290.000".toBigDecimal(), sectionCode="1", quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(), exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(Tax(sum="138.210".toBigDecimal(), inTotalSum=true, layout=TaxLayout(type="НДС", rate=0.12))), name="Паста реставрационная (блистер), Белый, 002"))
				),
				payments= listOf(Payment(sum="2580.000".toBigDecimal(), paymentType="CARD")),
				taxes= listOf(), shiftDocumentNumber=null, requestPayload=null, payload=null),
			ticketDescription="transaction.ticket.sell, transaction.ticket.payment.type.card",
			orgTitle="ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"МЕГАСТРОЙ АСТАНА DIY\"", orgId="090840015598",
			retailPlaceAddress="р-он Байконыр, Альмухана Сембинова 19/1", kkmSerialNumber="SWK00100844",
			kkmFnsId="010100344201", foundDate=LocalDateTime.parse("2024-05-12T13:21:31.041"),
			taxesTotal= listOf(TaxTotal(sum="276.420".toBigDecimal(), rate=0.12, name="")), totalDiscount=0.0, totalMarkup=0.0,
			measureUnits= mapOf("796" to "шт "), isForAnon=true
		)


		val json = Json {
			ignoreUnknownKeys = true
		}

		val receipt: Receipt = json.decodeFromString(jsonReceipt)

		Assertions.assertEquals(expectedReceipt, receipt)

	}

	@Test
	fun receiptToDtoTest() {
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
				totalSum="2580.000".toBigDecimal(),
				operationType=2,
				operator= Operator(code=9, name="Касса 1у"),
				takenSum="0".toBigDecimal(),
				changeSum="0".toBigDecimal(),
				items= listOf(
					Item(itemType=1, commodity= Commodity(sum="1290.000".toBigDecimal(), sectionCode="1", quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(), exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(Tax(sum="138.210".toBigDecimal(), inTotalSum=true, layout= TaxLayout(type="НДС", rate=0.12))), name="Штрих мебельный (блистер), Белый, 002")),
					Item(itemType=1, commodity=Commodity(sum="1290.000".toBigDecimal(), sectionCode="1", quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(), exciseStamp="", physicalLabel="", productId="0", barcode=null, measureUnitCode="796", code=0, taxes=listOf(Tax(sum="138.210".toBigDecimal(), inTotalSum=true, layout=TaxLayout(type="НДС", rate=0.12))), name="Паста реставрационная (блистер), Белый, 002"))
				),
				payments= listOf(Payment(sum="2580.000".toBigDecimal(), paymentType="CARD")),
				taxes= listOf(), shiftDocumentNumber=null, requestPayload=null, payload=null),
			ticketDescription="transaction.ticket.sell, transaction.ticket.payment.type.card",
			orgTitle="ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"МЕГАСТРОЙ АСТАНА DIY\"", orgId="090840015598",
			retailPlaceAddress="р-он Байконыр, Альмухана Сембинова 19/1", kkmSerialNumber="SWK00100844",
			kkmFnsId="010100344201", foundDate=LocalDateTime.parse("2024-05-12T13:21:31.041"),
			taxesTotal= listOf(TaxTotal(sum="276.420".toBigDecimal(), rate=0.12, name="")), totalDiscount=0.0, totalMarkup=0.0,
			measureUnits= mapOf("796" to "шт "), isForAnon=true
		)

		val expectedDto = ReceiptDto(
			id= UUID.fromString("1e585134-9b06-4739-ac8d-caaa4c2a9343"), ticket=
			TicketDto(transactionDate= LocalDateTime.parse("2024-05-11T13:35:02"), fiscalId="2173254610", totalSum="2580.000".toBigDecimal(), operationType=2, items= listOf(ItemDto(itemType=1, commodity= CommodityDto(sum="1290.000".toBigDecimal(), quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(), name="Штрих мебельный (блистер), Белый, 002")), ItemDto(itemType=1, commodity=CommodityDto(sum="1290.000".toBigDecimal(), quantity="1.000".toBigDecimal(), price="1290.000".toBigDecimal(), name="Паста реставрационная (блистер), Белый, 002"))),
				payments= listOf(PaymentDto(sum="2580.000".toBigDecimal(), paymentType= PaymentTypeEnum.CARD))
			),
			orgTitle="ТОВАРИЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"МЕГАСТРОЙ АСТАНА DIY\"", retailPlaceAddress="р-он Байконыр, Альмухана Сембинова 19/1", foundDate= LocalDateTime.parse("2024-05-12T13:21:31.041"), kkmFnsId="010100344201"
		)

		Assertions.assertAll(
			{ Assertions.assertEquals(expectedDto.orgTitle, receipt.toDto().orgTitle) },
			{ Assertions.assertEquals(expectedDto.retailPlaceAddress, receipt.toDto().retailPlaceAddress) },
			{ Assertions.assertEquals(expectedDto.foundDate, receipt.toDto().foundDate) },
			{ Assertions.assertEquals(expectedDto.ticket, receipt.toDto().ticket) },
			{ Assertions.assertEquals(expectedDto.kkmFnsId, receipt.toDto().kkmFnsId) },
		)
	}
}
