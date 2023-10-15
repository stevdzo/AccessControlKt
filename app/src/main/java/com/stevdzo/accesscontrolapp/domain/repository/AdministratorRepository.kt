package com.stevdzo.accesscontrolapp.domain.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.domain.model.Administrator
import com.stevdzo.accesscontrolapp.presentation.sign_in.AdminUser

interface AdministratorRepository {

    suspend fun upsertAdministrator(administrator: Administrator)

    suspend fun deleteAdministrator(administrator: Administrator)

    fun getAdministrators(): LiveData<List<Administrator>>

    suspend fun getAdministratorById(administratorId: Long): Administrator

    suspend fun getAdministratorByCredentials(username: String, password: String): Administrator
}