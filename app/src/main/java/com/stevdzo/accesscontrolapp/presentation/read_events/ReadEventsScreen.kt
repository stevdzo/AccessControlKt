package com.stevdzo.accesscontrolapp.presentation.read_events

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stevdzo.accesscontrolapp.R
import com.stevdzo.accesscontrolapp.common.ui.Background
import com.stevdzo.accesscontrolapp.common.ui.GoBackButton
import com.stevdzo.accesscontrolapp.constants.Constants
import com.stevdzo.accesscontrolapp.domain.model.Event
import com.stevdzo.accesscontrolapp.nav.NavRoutes
import java.time.format.DateTimeFormatter

@Composable
fun ReadEventsScreen(vm: ReadEventsViewModel) {

    val events = vm.events.observeAsState()

    Background(0.0)

    GoBackButton {
        vm.goBack()
    }

    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        events.value?.let {
            items(it) {event ->
                EventCardView(vm, event)
            }
        }
    }
}

@Composable
fun EventCardView(vm: ReadEventsViewModel, event: Event) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 10.dp)
            .clickable { vm.navigateTo(NavRoutes.EventDetail.screenRoute + "/${event.eventId}") },
        border = BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.events),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = event.date.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}