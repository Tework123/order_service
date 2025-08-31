package com.ex.order_service.consumer

import com.ex.order_service.dto.SendRouteEventsRequestDto
import com.ex.order_service.dto.SendRouteEventsRequestDto.RouteEventDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*

@Service
class KafkaSagaConsumer(
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(topics = ["saga-main-topic"], containerFactory = "kafkaSagaMainListenerContainerFactory")
    @Throws(JsonProcessingException::class)
    fun listenSagaMainTopic(message: String?) {

        val dto: RouteEventDto = message!!.let { objectMapper.readValue(it) }
        println("financeService consumer принял сообщение из saga-main-topic: " + dto.routeEventId)
        // пишем в базу
//        отправляем в другой топик сообщение
//        если exception то в другой топик, а если при отправке туда тоже exception? gg
    }
}
