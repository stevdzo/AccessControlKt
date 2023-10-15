package com.stevdzo.accesscontrolapp.presentation.read_employees

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository

class ReadEmployeesViewModel(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    lateinit var navHostController: NavHostController

    val employees = employeeRepository.getEmployees()

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}