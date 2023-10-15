package com.stevdzo.accesscontrolapp.presentation.scan

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.common.util.CameraFactory
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository
import kotlinx.coroutines.launch

class ScanViewModel(
    private val employeeRepository: EmployeeRepository,
    private val eventRepository: EventRepository
) : ViewModel() {

    lateinit var navHostController: NavHostController
    lateinit var cameraFactory: CameraFactory

    private val _employee = MutableLiveData<Employee>()
    val employee: LiveData<Employee> = _employee

    var hasReadCode = MutableLiveData(false)

    private fun getEmployeeByCode(code: String): LiveData<Employee> {
        viewModelScope.launch {
            employeeRepository.getEmployeeByCode(code).let {
                _employee.value = it
            }
        }
        return _employee
    }

    private fun upsertEvent(event: Event) {
        viewModelScope.launch {
            eventRepository.upsertEvent(event)
        }
    }

    fun setupCameraFactory(context: Context, owner: LifecycleOwner): PreviewView {
        cameraFactory = CameraFactory(context, owner)
        return cameraFactory.setupCameraFactory {
            val employee = getEmployeeByCode(it.code)
            upsertEvent(
                Event(
                    employeeId = employee.value!!.employeeId
                )
            )
            hasReadCode.postValue(it.hasReadCode)
        }
    }

    fun clearAnalyzer() {
        cameraFactory.clearAnalyzer()
    }

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }
}