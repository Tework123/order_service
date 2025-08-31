package com.ex.order_service.dto

import com.ex.order_service.enums.RouteEventStatus
import com.ex.order_service.enums.WeatherStatus
import lombok.*
import java.time.LocalDateTime
import java.util.*


data class SendRouteEventsRequestDto(
    val courierId: UUID?,
    val orderId: UUID?,
    var routeEvents: List<RouteEventDto>?
) {

    data class RouteEventDto(
        val routeEventId: UUID?,
        val locationPoint: LocationPointDto?,
        val routeEventStatus: RouteEventStatus?,
        val weatherStatus: WeatherStatus?,
        val timestamp: LocalDateTime?,
        val timeCreate: LocalDateTime?,
        val message: String?
    )

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class LocationPointDto {
        private val locationPointId: UUID? = null
        private val longitude = 0.0
        private val latitude = 0.0
        private val timestamp: LocalDateTime? = null
        private val timeCreate: LocalDateTime? = null
    }
}
