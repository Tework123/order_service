package com.ex.order_service.dto

import com.ex.order_service.enums.RouteEventStatus
import com.ex.order_service.enums.WeatherStatus
import lombok.*
import java.time.LocalDateTime
import java.util.*


data class SendRouteEventsRequestDto(
    val courierId: UUID,
    val orderId: UUID,
    var routeEvents: List<RouteEventDto>
) {

    data class RouteEventDto(
        val routeEventId: UUID,
        val locationPoint: LocationPointDto?,
        val routeEventStatus: RouteEventStatus?,
        val weatherStatus: WeatherStatus?,
        val timestamp: LocalDateTime?,
        val timeCreate: LocalDateTime?,
        val message: String?
    )


    data class LocationPointDto(
        val locationPointId: UUID,
        val longitude: Double = 0.0,
        val latitude: Double = 0.0,
        val timestamp: LocalDateTime? = null,
        val timeCreate: LocalDateTime? = null
    )
}
