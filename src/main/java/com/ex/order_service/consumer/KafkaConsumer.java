package com.ex.order_service.consumer;

import com.ex.order_service.dto.SendRouteEventsRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    public KafkaConsumer(ObjectMapper jacksonObjectMapper) {
        this.objectMapper = jacksonObjectMapper;
    }

    //    проверял группы консумеров
    @KafkaListener(topics = "my-topic", groupId = "my-json-group-2")
    public void listen(String message) {
        try {
            SendRouteEventsRequestDto.RouteEventDto dto = objectMapper.readValue(message, SendRouteEventsRequestDto.RouteEventDto.class);
            System.out.println("Kafka consumer received message: " + dto.getRouteEventId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}