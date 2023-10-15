package com.stevdzo.accesscontrolapp.presentation.read_events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository

class ReadEventsViewModel(
    eventRepository: EventRepository
): ViewModel() {

    lateinit var navHostController: NavHostController

    val events = eventRepository.getEvents()

    val employee = MutableLiveData<Employee>()

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}