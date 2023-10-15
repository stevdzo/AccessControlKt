package com.stevdzo.accesscontrolapp.domain.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.domain.model.Employee

interface EmployeeRepository {

    suspend fun upsertEmployee(employee: Employee)

    suspend fun deleteEmployee(employee: Employee)

    fun getEmployees(): LiveData<List<Employee>>

    suspend fun getEmployeeById(employeeId: Long): Employee

    suspend fun getEmployeeByCode(code: String): Employee

    suspend fun doesEmailExist(email: String): Boolean

    suspend fun doesCodeExist(code: String): Boolean
}