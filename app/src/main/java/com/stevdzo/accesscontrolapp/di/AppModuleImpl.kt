package com.stevdzo.accesscontrolapp.di

import android.content.Context
import androidx.room.Room
import com.stevdzo.accesscontrolapp.data.local_data.InitCallback
import com.stevdzo.accesscontrolapp.data.local_data.ProjectDatabase
import com.stevdzo.accesscontrolapp.data.repository.AdministratorRepositoryImpl
import com.stevdzo.accesscontrolapp.data.repository.EmployeeRepositoryImpl
import com.stevdzo.accesscontrolapp.data.repository.EventRepositoryImpl
import com.stevdzo.accesscontrolapp.domain.repository.AdministratorRepository
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import com.stevdzo.accesscontrolapp.domain.repository.EventRepository

class AppModuleImpl(
    private val appContext: Context
) : AppModule {

    override val projectDatabase: ProjectDatabase by lazy {
        Room.databaseBuilder(
            appContext,
            ProjectDatabase::class.java,
            ProjectDatabase.DATABASE_NAME)
            .addCallback(InitCallback())
            .build()
    }

    override val administratorRepository: AdministratorRepository by lazy {
        AdministratorRepositoryImpl(projectDatabase.administratorDao())
    }

    override val employeeRepository: EmployeeRepository by lazy {
        EmployeeRepositoryImpl(projectDatabase.employeeDao())
    }

    override val eventRepository: EventRepository by lazy {
        EventRepositoryImpl(projectDatabase.eventDao())
    }
}