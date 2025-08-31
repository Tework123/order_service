package com.ex.order_service.producer

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

private val logger = KotlinLogging.logger {}

@Service
class KafkaSagaProducer(
    @Qualifier("kafkaSagaSuccessTemplate") val kafkaSagaSuccessTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
) {

    fun sendMessage(topic: String, routeEventId: UUID) {
        val json: String = objectMapper.writeValueAsString(routeEventId);

        kafkaSagaSuccessTemplate.send(topic, json);

        logger.info {
            "OrderService producer отправил подтверждение " +
                    "успешной транзакции в saga-success-topic: $routeEventId"
        }
    }


}
