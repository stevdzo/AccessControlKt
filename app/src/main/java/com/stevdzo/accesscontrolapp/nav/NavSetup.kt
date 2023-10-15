package com.stevdzo.accesscontrolapp.nav

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stevdzo.accesscontrolapp.App
import com.stevdzo.accesscontrolapp.presentation.add_employee.AddEmployeeScreen
import com.stevdzo.accesscontrolapp.presentation.add_employee.AddEmployeeViewModel
import com.stevdzo.accesscontrolapp.presentation.employee_detail.EmployeeDetailScreen
import com.stevdzo.accesscontrolapp.presentation.employee_detail.EmployeeDetailViewModel
import com.stevdzo.accesscontrolapp.presentation.event_detail.EventDetailScreen
import com.stevdzo.accesscontrolapp.presentation.event_detail.EventDetailViewModel
import com.stevdzo.accesscontrolapp.presentation.options.AdminOptionsScreen
import com.stevdzo.accesscontrolapp.presentation.options.AdminOptionsViewModel
import com.stevdzo.accesscontrolapp.presentation.read_employees.ReadEmployeesScreen
import com.stevdzo.accesscontrolapp.presentation.read_employees.ReadEmployeesViewModel
import com.stevdzo.accesscontrolapp.presentation.read_events.ReadEventsScreen
import com.stevdzo.accesscontrolapp.presentation.read_events.ReadEventsViewModel
import com.stevdzo.accesscontrolapp.presentation.scan.ScanScreen
import com.stevdzo.accesscontrolapp.presentation.scan.ScanViewModel
import com.stevdzo.accesscontrolapp.presentation.send_report.SendReportScreen
import com.stevdzo.accesscontrolapp.presentation.send_report.SendReportViewModel
import com.stevdzo.accesscontrolapp.presentation.sign_in.SignInScreen
import com.stevdzo.accesscontrolapp.presentation.sign_in.SignInViewModel
import com.stevdzo.accesscontrolapp.presentation.viewModelFactory

@Composable
fun NavSetup() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Scan.screenRoute) {
        composable(NavRoutes.Scan.screenRoute) {
            val viewModel = viewModel<ScanViewModel>(
                factory = viewModelFactory {
                    ScanViewModel(App.appModule.employeeRepository, App.appModule.eventRepository)
                }
            )
            viewModel.navHostController = navController
            ScanScreen(viewModel)
        }
        composable(NavRoutes.SignIn.screenRoute) {
            BackHandler {}
            val viewModel = viewModel<SignInViewModel>(
                factory = viewModelFactory {
                    SignInViewModel(App.appModule.administratorRepository)
                }
            )
            viewModel.navHostController = navController
            SignInScreen(viewModel)
        }
        composable(NavRoutes.AdminOptions.screenRoute) {
            BackHandler {}
            val viewModel = viewModel<AdminOptionsViewModel>(
                factory = viewModelFactory {
                    AdminOptionsViewModel()
                }
            )
            viewModel.navHostController = navController
            AdminOptionsScreen(viewModel)
        }
        composable(NavRoutes.AddEmployee.screenRoute) {
            val viewModel = viewModel<AddEmployeeViewModel>(
                factory = viewModelFactory {
                    AddEmployeeViewModel(App.appModule.employeeRepository)
                }
            )
            viewModel.navHostController = navController
            AddEmployeeScreen(viewModel)
        }
        composable(NavRoutes.ReadEmployees.screenRoute) {
            val viewModel = viewModel<ReadEmployeesViewModel>(
                factory = viewModelFactory {
                    ReadEmployeesViewModel(App.appModule.employeeRepository)
                }
            )
            viewModel.navHostController = navController
            ReadEmployeesScreen(viewModel)
        }
        composable(NavRoutes.EmployeeDetail.screenRoute + "/{employeeId}") {
            val viewModel = viewModel<EmployeeDetailViewModel>(
                factory = viewModelFactory {
                    EmployeeDetailViewModel(App.appModule.employeeRepository)
                }
            )
            viewModel.navHostController = navController
            EmployeeDetailScreen(viewModel, it.arguments?.getString("employeeId")!!.toLong())
        }
        composable(NavRoutes.ReadEvents.screenRoute) {
            val viewModel = viewModel<ReadEventsViewModel>(
                factory = viewModelFactory {
                    ReadEventsViewModel(App.appModule.eventRepository)
                }
            )
            viewModel.navHostController = navController
            ReadEventsScreen(viewModel)
        }
        composable(NavRoutes.EventDetail.screenRoute + "/{eventId}") {
            val viewModel = viewModel<EventDetailViewModel>(
                factory = viewModelFactory {
                    EventDetailViewModel(App.appModule.eventRepository)
                }
            )
            viewModel.navHostController = navController
            EventDetailScreen(viewModel, it.arguments?.getString("eventId")!!.toLong())
        }
        composable(NavRoutes.SendReport.screenRoute) {
            val viewModel = viewModel<SendReportViewModel>(
                factory = viewModelFactory {
                    SendReportViewModel(App.appModule.employeeRepository, App.appModule.eventRepository)
                }
            )
            viewModel.navHostController = navController
            SendReportScreen(viewModel)
        }
    }
}