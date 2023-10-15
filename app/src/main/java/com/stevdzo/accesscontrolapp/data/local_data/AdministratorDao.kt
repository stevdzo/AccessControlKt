package com.stevdzo.accesscontrolapp.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.stevdzo.accesscontrolapp.domain.model.Administrator
import com.stevdzo.accesscontrolapp.presentation.sign_in.AdminUser

@Dao
interface AdministratorDao {

    @Upsert
    suspend fun upsertAdministrator(administrator: Administrator)

    @Delete
    suspend fun deleteAdministrator(administrator: Administrator)

    @Query("SELECT * FROM administrator")
    fun getAdministrators(): LiveData<List<Administrator>>

    @Query("SELECT * FROM administrator WHERE administratorId = :administratorId")
    suspend fun getAdministratorById(administratorId: Long): Administrator

    @Query("SELECT * FROM administrator WHERE username = :username AND password = :password")
    suspend fun getAdministratorByCredentials(username: String, password: String): Administrator
}