package com.stevdzo.accesscontrolapp.data.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.data.local_data.EventDao
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository

class EventRepositoryImpl(
    private val eventDao: EventDao
) : EventRepository {

    override suspend fun upsertEvent(event: Event) {
        eventDao.upsertEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }

    override fun getEvents(): LiveData<List<Event>> = eventDao.getEvents()

    override suspend fun getEventById(eventId: Long): Event =
        eventDao.getEventById(eventId)

    override suspend fun getEmployeeByEventId(eventId: Long): Employee? =
        eventDao.getEmployeeByEventId(eventId)
}