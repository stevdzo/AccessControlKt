package com.stevdzo.accesscontrolapp.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.stevdzo.accesscontrolapp.domain.model.Employee

@Dao
interface EmployeeDao {

    @Upsert
    suspend fun upsertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun getEmployees(): LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE employeeId = :employeeId")
    suspend fun getEmployeeById(employeeId: Long): Employee

    @Query("SELECT * FROM employee WHERE code = :code")
    suspend fun getEmployeeByCode(code: String): Employee

    @Query("SELECT EXISTS (SELECT 1 FROM employee WHERE email = :email)")
    suspend fun doesEmailExist(email: String): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM employee WHERE code = :code)")
    suspend fun doesCodeExist(code: String): Boolean
}