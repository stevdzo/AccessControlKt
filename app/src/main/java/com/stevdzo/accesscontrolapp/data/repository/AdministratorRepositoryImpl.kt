package com.stevdzo.accesscontrolapp.data.repository

import androidx.lifecycle.LiveData
import com.stevdzo.accesscontrolapp.data.local_data.AdministratorDao
import com.stevdzo.accesscontrolapp.domain.model.Administrator
import com.stevdzo.accesscontrolapp.domain.repository.AdministratorRepository

class AdministratorRepositoryImpl(
    private val administratorDao: AdministratorDao
) : AdministratorRepository {

    override suspend fun upsertAdministrator(administrator: Administrator) {
        administratorDao.upsertAdministrator(administrator)
    }

    override suspend fun deleteAdministrator(administrator: Administrator) {
        administratorDao.deleteAdministrator(administrator)
    }

    override fun getAdministrators(): LiveData<List<Administrator>> =
        administratorDao.getAdministrators()

    override suspend fun getAdministratorById(administratorId: Long): Administrator =
        administratorDao.getAdministratorById(administratorId)

    override suspend fun getAdministratorByCredentials(username: String, password: String): Administrator =
        administratorDao.getAdministratorByCredentials(username, password)
}