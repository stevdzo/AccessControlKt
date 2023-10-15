package com.stevdzo.accesscontrolapp.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event

@Dao
interface EventDao {

    @Upsert
    suspend fun upsertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM event")
    fun getEvents(): LiveData<List<Event>>

    @Query("SELECT * FROM event WHERE eventId = :eventId")
    suspend fun getEventById(eventId: Long): Event

    @Query("SELECT * FROM employee JOIN event ON employee.employeeId = event.employeeId WHERE event.eventId = :eventId")
    suspend fun getEmployeeByEventId(eventId: Long) : Employee?
}