package com.ex.order_service.entity

import com.ex.order_service.enums.CountStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "count")
class Count(
    @Id
    @GeneratedValue
    val id: UUID? = null,

    var count: Int,

    @Column(name = "route_event_id")
    var routeEventId: UUID,

    @Column(name = "count_status")
    var countStatus: CountStatus,

    @CreationTimestamp
    var createDt: LocalDateTime? = null,
)

