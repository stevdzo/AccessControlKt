package com.stevdzo.accesscontrolapp.presentation.employee_detail

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.common.util.QRCodeBitmap
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import kotlinx.coroutines.launch

class EmployeeDetailViewModel(
    private val employeeRepository: EmployeeRepository
): ViewModel() {

    lateinit var navHostController: NavHostController

    private val employee = MutableLiveData<Employee>()

    fun getEmployeeById(employeeId: Long): LiveData<Employee> {
        viewModelScope.launch {
            employeeRepository.getEmployeeById(employeeId).let {
                employee.value = it
            }
        }
        return employee
    }

    fun getBitmapFromCode(code: String) : Bitmap {
        return QRCodeBitmap(code).getBitmapFromCode()
    }

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }
}