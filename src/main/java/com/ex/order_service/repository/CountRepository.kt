package com.ex.order_service.repository

import com.ex.order_service.entity.Count
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CountRepository : JpaRepository<Count, UUID> {
    @Query(value = "select * from count order by count limit 1", nativeQuery = true)
    fun findLastByCount(): Count?

    @Query(value = "select c from Count c where c.routeEventId = :routeEventId ")
    fun findByRouteEventId(routeEventId: UUID): Count?

}