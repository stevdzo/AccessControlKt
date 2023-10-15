package com.stevdzo.accesscontrolapp.presentation.options

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class AdminOptionsViewModel : ViewModel() {

    lateinit var navHostController: NavHostController

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}