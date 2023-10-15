package com.stevdzo.accesscontrolapp.presentation.sign_in

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdzo.accesscontrolapp.common.ui.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    vm: SignInViewModel
) {

    val areCredentialsCorrect = remember { mutableStateOf(true) }

    Background(0.0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(
            text = "Sign In",
            style = TextStyle(fontSize = 40.sp),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            label = { Text(text = "Password") },
            value = password.value,
            isError = !areCredentialsCorrect.value,
            supportingText = {
                if (!areCredentialsCorrect.value) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Username or password incorrect!",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it }
        )

        ElevatedButton(
            onClick = {
                //vm.upsertAdministrator(Administrator(username ="admin", password= "123123"))
                if (vm.signIn(
                        AdminUser(
                            username.value.text,
                            password.value.text
                        )
                    ) { areCredentialsCorrect.value = it }
                ) {
                    vm.navigateToOptionsScreen()
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
                text = "Sign in",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}