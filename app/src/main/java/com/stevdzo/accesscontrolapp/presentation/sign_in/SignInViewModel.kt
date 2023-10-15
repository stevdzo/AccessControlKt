package com.stevdzo.accesscontrolapp.presentation.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.domain.model.Administrator
import com.stevdzo.accesscontrolapp.domain.repository.AdministratorRepository
import com.stevdzo.accesscontrolapp.nav.NavRoutes
import kotlinx.coroutines.launch

class SignInViewModel(
    private val administratorRepository: AdministratorRepository
) : ViewModel() {

    lateinit var navHostController: NavHostController

    private val administrator = MutableLiveData<Administrator>()

    fun upsertAdministrator(administrator: Administrator) {
        viewModelScope.launch {
            administratorRepository.upsertAdministrator(administrator)
        }
    }

    private fun getAdministratorByUsername(username: String, password: String): LiveData<Administrator> {
        viewModelScope.launch {
            administratorRepository.getAdministratorByCredentials(username, password).let {
                administrator.value = it
            }
        }
        return administrator
    }

    fun signIn(adminUser: AdminUser, onError: (Boolean) -> Unit): Boolean {
        return when {
            getAdministratorByUsername(adminUser.username, adminUser.password).value != null -> {
                SessionManager.signIn(adminUser)
                onError(true)
                true
            }
            else -> {
                onError(false)
                false
            }
        }
    }

    fun navigateToOptionsScreen() {
        navHostController.navigate(NavRoutes.AdminOptions.screenRoute)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}