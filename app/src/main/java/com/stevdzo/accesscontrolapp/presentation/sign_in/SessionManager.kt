package com.stevdzo.accesscontrolapp.presentation.sign_in

object SessionManager {

    private var currentAdminUser: AdminUser? = null

    fun isLoggedIn(): Boolean {
        return currentAdminUser != null
    }

    fun signIn(adminUser: AdminUser) {
        currentAdminUser = adminUser
    }

    fun signOut() {
        currentAdminUser = null
    }

    fun getUser(): AdminUser? {
        return currentAdminUser
    }
}