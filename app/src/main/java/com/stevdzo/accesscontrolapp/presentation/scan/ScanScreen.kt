package com.stevdzo.accesscontrolapp.presentation.scan

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stevdzo.accesscontrolapp.R
import com.stevdzo.accesscontrolapp.common.ui.SwitchFuncButton
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.nav.NavRoutes
import com.stevdzo.accesscontrolapp.presentation.add_employee.AddEmployeeViewModel
import com.stevdzo.accesscontrolapp.presentation.add_employee.BitmapImage
import kotlinx.coroutines.delay

@Composable
fun ScanScreen(
    vm: ScanViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val hasReadCode = vm.hasReadCode.observeAsState()

    val showDialog = remember { mutableStateOf(false) }

    val employee = vm.employee.observeAsState()

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(hasReadCode.value) {
        if (hasReadCode.value == true) {
            vm.clearAnalyzer()
            showDialog.value = true
        }
    }

    LaunchedEffect(showDialog.value) {
        if (showDialog.value) {
            delay(3000L)
            showDialog.value = false
            vm.navigateTo(NavRoutes.Scan.screenRoute)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        AndroidView(
            factory = { context ->
                vm.setupCameraFactory(context, lifecycleOwner)
            },
            modifier = Modifier
                .fillMaxSize()
            //.border(10.dp, color = Color(0xFFFFFFFF))
        )

        SwitchFuncButton(
            Modifier
                .align(Alignment.TopEnd),
            Icons.Filled.Settings
        ) {
            vm.navigateTo(NavRoutes.SignIn.screenRoute)
        }

        Box(
            modifier = Modifier
                .size(300.dp)
                .border(10.dp, Color.White),
            contentAlignment = Alignment.Center
        ) {

        }
    }

    if (showDialog.value) {
        ShowDialog(employee.value!!)
    }
}

@Composable
fun ShowDialog(
    employee: Employee
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Scan successful",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Welcome, ${employee.name} ${employee.surname}!",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painterResource(R.drawable.checkmark),
                    contentDescription = "checkmark",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(200.dp, 200.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}