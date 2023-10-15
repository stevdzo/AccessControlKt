package com.stevdzo.accesscontrolapp.presentation.add_employee

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.stevdzo.accesscontrolapp.common.util.EmailManager
import com.stevdzo.accesscontrolapp.common.util.QRCodeBitmap
import com.stevdzo.accesscontrolapp.constants.Constants
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.repository.EmployeeRepository
import com.stevdzo.accesscontrolapp.nav.NavRoutes
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random

class AddEmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    lateinit var navHostController: NavHostController
    private lateinit var qrCodeBitmap: QRCodeBitmap

    private val emailExists = MutableLiveData(false)
    private val codeExists = MutableLiveData(false)

    fun upsertEmployee(employee: Employee) {
        viewModelScope.launch {
            employeeRepository.upsertEmployee(employee)
        }
    }

    fun doesEmailExist(email: String): LiveData<Boolean> {
        runBlocking {
            employeeRepository.doesEmailExist(email.lowercase().trim()).let {
                emailExists.value = it
                Log.i("email_exists", it.toString())
            }
        }
        return emailExists
    }

    fun doesCodeExist(code: String): LiveData<Boolean> {
        runBlocking {
            employeeRepository.doesCodeExist(code.trim()).let {
                emailExists.value = it
            }
        }
        return emailExists
    }

    fun generateCode(): String {
        val characters = Constants.STRING_SEQUENCE
        val random = Random(System.currentTimeMillis())

        return (1..Constants.QR_CODE_LENGTH)
            .map { characters[random.nextInt(characters.length)] }
            .joinToString("")
    }

    fun composeEmail(context: Context, employee: Employee) {
        EmailManager(context).composeEmail(
            arrayOf(employee.email),
            Constants.EMAIL_SUBJECT,
            employee.name + Constants.EMAIL_BODY,
            saveBitmapToFileAndGetUri(context)
        )
    }

    fun getBitmapFromCode(code: String): Bitmap {
        qrCodeBitmap = QRCodeBitmap(code)
        return qrCodeBitmap.getBitmapFromCode()
    }

    private fun saveBitmapToFileAndGetUri(context: Context): Uri {
        return qrCodeBitmap.saveBitmapToFileAndGetUri(context, qrCodeBitmap.getBitmapFromCode())
    }

    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun goBack() {
        navHostController.popBackStack()
    }
}