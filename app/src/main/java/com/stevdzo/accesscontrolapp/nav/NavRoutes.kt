package com.stevdzo.accesscontrolapp.nav

sealed class NavRoutes(var title: String, var screenRoute: String) {
    object Scan : NavRoutes("Scan", "scan")
    object SignIn : NavRoutes("Sign in", "sign_in")
    object AdminOptions : NavRoutes("Admin options", "admin_options")
    object AddEmployee : NavRoutes("Add employee", "add_employee")
    object ReadEmployees : NavRoutes("Read employees", "read_employees")
    object EmployeeDetail : NavRoutes("Employee detail", "employee_detail")
    object ReadEvents : NavRoutes("Read events", "read_events")
    object EventDetail : NavRoutes("Event detail", "event_detail")
    object SendReport : NavRoutes("Send report", "send_report")
}
