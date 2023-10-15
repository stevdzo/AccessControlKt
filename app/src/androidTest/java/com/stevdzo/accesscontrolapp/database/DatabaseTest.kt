package com.stevdzo.accesscontrolapp.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stevdzo.accesscontrolapp.data.local_data.EmployeeDao
import com.stevdzo.accesscontrolapp.data.local_data.ProjectDatabase
import com.stevdzo.accesscontrolapp.domain.model.Employee
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: ProjectDatabase
    private lateinit var employeeDao: EmployeeDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            ProjectDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        employeeDao = db.employeeDao()
    }

    @After
    @Throws(Exception::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEmployee() = runBlocking {
        val employee = Employee(
            employeeId=1,
            name="Name",
            surname="Surname",
            email="name.surname@gmail.com",
            phone="123456789",
            code="5dKF83hfJF"
        )
        employeeDao.upsertEmployee(employee)

        TestCase.assertEquals(employeeDao.getEmployeeById(1).employeeId, 1)
    }
}