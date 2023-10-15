package com.stevdzo.accesscontrolapp.presentation.send_report

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository

class SendReportViewModel(
    private val employeeRepository: EmployeeRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    lateinit var navHostController: NavHostController
}