package com.ex.order_service.consumer

import com.ex.order_service.dto.SendRouteEventsRequestDto.RouteEventDto
import com.ex.order_service.producer.KafkaSagaProducer
import com.ex.order_service.service.CountService
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.*

@Service
class KafkaSagaConsumer(
    val objectMapper: ObjectMapper,
    val countService: CountService,
    val kafkaSagaProducer: KafkaSagaProducer
) {

    @KafkaListener(
        topics = ["saga-main-topic"], groupId = "my-saga-order-id",
        containerFactory = "kafkaSagaMainListenerContainerFactory"
    )
    @Throws(JsonProcessingException::class)
    fun listenSagaMainTopic(message: String?) {
        val dto: RouteEventDto = message!!.let { objectMapper.readValue(it) }
        println("OrderService consumer принял сообщение из saga-main-topic: " + dto.routeEventId)

        countService.save(dto)

        kafkaSagaProducer.sendMessage("saga-success-topic", dto.routeEventId)
        // пишем в базу
//        отправляем в другой топик сообщение
//        если exception то в другой топик, а если при отправке туда тоже exception? gg
    }
}
