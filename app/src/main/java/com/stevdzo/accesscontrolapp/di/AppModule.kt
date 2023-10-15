package com.stevdzo.accesscontrolapp.di

import com.stevdzo.accesscontrolapp.data.local_data.ProjectDatabase
import com.stevdzo.accesscontrolapp.domain.repository.AdministratorRepository
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository

interface AppModule {
    val projectDatabase: ProjectDatabase
    val administratorRepository: AdministratorRepository
    val employeeRepository: EmployeeRepository
    val eventRepository: EventRepository
}

