package com.stevdzo.accesscontrolapp.presentation.event_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdzo.accesscontrolapp.R
import com.stevdzo.accesscontrolapp.common.ui.Background
import com.stevdzo.accesscontrolapp.common.ui.GoBackButton
import com.stevdzo.accesscontrolapp.constants.Constants
import com.stevdzo.accesscontrolapp.domain.model.Employee
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.nav.NavRoutes
import com.stevdzo.accesscontrolapp.presentation.add_employee.BitmapImage
import com.stevdzo.accesscontrolapp.presentation.employee_detail.EmployeeDetailViewModel
import com.stevdzo.accesscontrolapp.presentation.read_employees.ReadEmployeesViewModel
import java.time.format.DateTimeFormatter

@Composable
fun EventDetailScreen(vm: EventDetailViewModel, eventId: Long) {

    Background(0.0)

    GoBackButton {
        vm.goBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        EventCardView(vm, eventId)
    }
}

@Composable
fun EventCardView(vm: EventDetailViewModel, eventId: Long) {

    val event = vm.getEventById(eventId).observeAsState()
    val employee = vm.getEmployeeByEventId(eventId).observeAsState()

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .padding(30.dp, 60.dp, 30.dp, 60.dp)
            .fillMaxWidth(),
        border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Column(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.events),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${employee.value?.name} ${employee.value?.surname}",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${
                    event.value?.date?.format(
                        DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)
                    )
                }",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
    }
}