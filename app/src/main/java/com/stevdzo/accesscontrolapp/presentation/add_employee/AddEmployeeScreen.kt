package com.stevdzo.accesscontrolapp.presentation.add_employee

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.MutableLiveData
import com.stevdzo.accesscontrolapp.common.logic.Validation
import com.stevdzo.accesscontrolapp.common.ui.Background
import com.stevdzo.accesscontrolapp.common.ui.GoBackButton
import com.stevdzo.accesscontrolapp.domain.model.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployeeScreen(vm: AddEmployeeViewModel) {

    val context = LocalContext.current

    val name = remember { mutableStateOf(TextFieldValue()) }
    val surname = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val phone = remember { mutableStateOf(TextFieldValue()) }

    val nameValidation = remember { mutableStateOf(false) }
    val surnameValidation = remember { mutableStateOf(false) }
    val emailValidation = remember { mutableStateOf(false) }
    val phoneValidation = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val doesEmailExist = remember { mutableStateOf(false) }

    val generatedCode = remember { mutableStateOf("") }

    val employee = remember { MutableLiveData<Employee>() }

    Background(0.0)

    GoBackButton {
        vm.goBack()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Add Employee",
            style = TextStyle(fontSize = 40.sp),
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            label = { Text(text = "Name") },
            value = name.value,
            isError = nameValidation.value,
            supportingText = {
                if (nameValidation.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Invalid name!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            onValueChange = { name.value = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Surname") },
            value = surname.value,
            isError = surnameValidation.value,
            supportingText = {
                if (surnameValidation.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Invalid surname!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            onValueChange = { surname.value = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Email") },
            value = email.value,
            isError = emailValidation.value,
            supportingText = {
                if (emailValidation.value || doesEmailExist.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Invalid email or email already exists!",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email.value = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Phone") },
            value = phone.value,
            isError = phoneValidation.value,
            supportingText = {
                if (phoneValidation.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Invalid phone!",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { phone.value = it }
        )
        ElevatedButton(
            onClick = {

                do {
                    generatedCode.value = vm.generateCode()
                } while (vm.doesCodeExist(generatedCode.value).value!!)

                doesEmailExist.value = vm.doesEmailExist(email.value.text).value!!

                if (Validation.isNameOrSurnameValid(name.value.text) {
                        nameValidation.value = it
                    } &&
                    Validation.isNameOrSurnameValid(surname.value.text) {
                        surnameValidation.value = it
                    } &&
                    (Validation.isEmailValid(email.value.text) {
                        emailValidation.value = it
                    } && !doesEmailExist.value) &&
                    Validation.isPhoneValid(phone.value.text) { phoneValidation.value = it }
                ) {
                    generatedCode.value = vm.generateCode()
                    employee.value = Employee(
                        name = name.value.text,
                        surname = surname.value.text,
                        email = email.value.text,
                        phone = phone.value.text,
                        code = generatedCode.value,
                    )
                    vm.upsertEmployee(employee.value!!)

                    showDialog.value = true
                }
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .padding(64.dp, 16.dp, 64.dp, 16.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Add employee",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        if (showDialog.value) {
            ShowDialog(
                context,
                vm,
                showDialog,
                employee.value!!
            )
        }
    }
}

@Composable
fun ShowDialog(
    context: Context,
    vm: AddEmployeeViewModel,
    showDialog: MutableState<Boolean>,
    employee: Employee
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Name: ${employee.name}",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )

                Text(
                    text = "Surname: ${employee.surname}",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )

                Text(
                    text = "Email: ${employee.email}",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )

                Text(
                    text = "Phone: ${employee.phone}",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )

                BitmapImage(vm.getBitmapFromCode(employee.code))

                ElevatedButton(
                    onClick = {

                        vm.composeEmail(context, employee)
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.White),
                    modifier = Modifier
                        .padding(64.dp, 16.dp, 64.dp, 16.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = "Save",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "QR Bitmap",
    )
}