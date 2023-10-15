package com.stevdzo.accesscontrolapp.data.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.data.local_data.EmployeeDao
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository

class EmployeeRepositoryImpl(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {

    override suspend fun upsertEmployee(employee: Employee) {
        employeeDao.upsertEmployee(employee)
    }

    override suspend fun deleteEmployee(employee: Employee) {
        employeeDao.deleteEmployee(employee)
    }

    override fun getEmployees(): LiveData<List<Employee>> =
        employeeDao.getEmployees()

    override suspend fun getEmployeeById(employeeId: Long): Employee =
        employeeDao.getEmployeeById(employeeId)

    override suspend fun getEmployeeByCode(code: String): Employee =
        employeeDao.getEmployeeByCode(code)

    override suspend fun doesEmailExist(email: String): Boolean =
        employeeDao.doesEmailExist(email)

    override suspend fun doesCodeExist(code: String): Boolean =
        employeeDao.doesCodeExist(code)
}