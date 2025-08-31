package com.ex.order_service.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
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
    @CreationTimestamp
    var createDt: LocalDateTime?,
)

