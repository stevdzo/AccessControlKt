package com.stevdzo.accesscontrolapp.domain.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event

interface EventRepository {

    suspend fun upsertEvent(event: Event)

    suspend fun deleteEvent(event: Event)

    fun getEvents(): LiveData<List<Event>>

    suspend fun getEventById(eventId: Long): Event

    suspend fun getEmployeeByEventId(eventId: Long): Employee?
}