package kz.mukha.wallet.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializer(forClass = BigDecimal::class)
object BigDecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.DOUBLE)

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toPlainString())
    }

    override fun deserialize(decoder: Decoder): BigDecimal {
        return try {
            val value = decoder.decodeDouble()
            BigDecimal.valueOf(value).setScale(3)
        } catch (e: Exception) {
            println("Failed to deserialize BigDecimal: ${e.message}")
            e.printStackTrace()
            BigDecimal.ZERO
        }
    }
}