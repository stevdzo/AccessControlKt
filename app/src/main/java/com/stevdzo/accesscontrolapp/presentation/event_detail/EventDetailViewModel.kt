package com.stevdzo.accesscontrolapp.presentation.event_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val eventRepository: EventRepository
): ViewModel() {

    lateinit var navHostController: NavHostController

    private val event = MutableLiveData<Event>()
    private val employee = MutableLiveData<Employee>()

    fun getEventById(eventId: Long): LiveData<Event> {
        viewModelScope.launch {
            eventRepository.getEventById(eventId).let {
                event.value = it
            }
        }
        return event
    }

    fun getEmployeeByEventId(eventId: Long): LiveData<Employee> {
        viewModelScope.launch {
            eventRepository.getEmployeeByEventId(eventId).let {
                employee.value = it
            }
        }
        return employee
    }

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}