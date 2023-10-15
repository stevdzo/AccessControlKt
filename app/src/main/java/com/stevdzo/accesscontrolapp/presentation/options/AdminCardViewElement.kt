package com.stevdzo.accesscontrolapp.presentation.options

import com.stevdzo.accesscontrolapp.R
import com.stevdzo.accesscontrolapp.nav.NavRoutes

data class AdminCardViewElement(
    val name: String,
    val image: Int,
    val route: String
)

val adminOptions = listOf(
    AdminCardViewElement("ADD EMPLOYEE", R.drawable.employee, NavRoutes.AddEmployee.screenRoute),
    AdminCardViewElement("READ EMPLOYEES", R.drawable.group, NavRoutes.ReadEmployees.screenRoute),
    AdminCardViewElement("READ EVENTS", R.drawable.events, NavRoutes.ReadEvents.screenRoute),
    AdminCardViewElement("SEND REPORT", R.drawable.report, NavRoutes.SendReport.screenRoute)
)