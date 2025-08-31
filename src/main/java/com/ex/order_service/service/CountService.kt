package com.ex.order_service.service

import com.ex.order_service.dto.SendRouteEventsRequestDto
import com.ex.order_service.entity.Count
import com.ex.order_service.enums.CountStatus
import com.ex.order_service.repository.CountRepository

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.util.UUID

private val logger = KotlinLogging.logger {}

@Service
class CountService(
    val countRepository: CountRepository
) {

    fun save(dto: SendRouteEventsRequestDto.RouteEventDto) {

        val newCount = Count(count = 1, routeEventId = dto.routeEventId, countStatus = CountStatus.NEW)

        countRepository.save(newCount);
        logger.info { "OrderService добавил count в бд ${newCount.createDt}" }

    }

    fun cancel(routeEventId: UUID) {
        val count: Count? = countRepository.findByRouteEventId(routeEventId);

        if (count != null) {
            count.countStatus = CountStatus.CANCEL;
            countRepository.save(count);
            logger.info { "OrderService отменил count в бд ${routeEventId}" }
        } else {
            logger.info { "OrderService не нашел count в бд чтобы его отменить ${routeEventId}" }

        }
    }
}
