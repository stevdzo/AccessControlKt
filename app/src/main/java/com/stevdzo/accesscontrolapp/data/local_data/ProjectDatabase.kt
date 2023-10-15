package com.stevdzo.accesscontrolapp.data.local_data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stevdzo.accesscontrolapp.App
import com.stevdzo.accesscontrolapp.common.logic.Converters
import com.stevdzo.accesscontrolapp.constants.Constants
import com.stevdzo.accesscontrolapp.domain.model.Administrator
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [
        Administrator::class,
        Employee::class,
        Event::class
    ],
    version = 4
)
@TypeConverters(Converters::class)
abstract class ProjectDatabase : RoomDatabase() {

    abstract fun administratorDao(): AdministratorDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun eventDao(): EventDao

    companion object {
        const val DATABASE_NAME = "project_db"
    }
}

class InitCallback : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadScheduledExecutor().execute {
            initializeAdmin()
        }
    }

    private fun initializeAdmin() = runBlocking {
        App.appModule.administratorRepository.upsertAdministrator(
            Administrator(
                username = Constants.ADMIN_USERNAME,
                password = Constants.ADMIN_PASSWORD
            )
        )
    }
}