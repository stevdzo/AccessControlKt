package com.stevdzo.accesscontrolapp.presentation.options

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.stevdzo.accesscontrolapp.common.ui.Background
import com.stevdzo.accesscontrolapp.common.ui.SwitchFuncButton
import com.stevdzo.accesscontrolapp.nav.NavRoutes

@Composable
fun AdminOptionsScreen(
    vm: AdminOptionsViewModel
) {
    Background(0.0)

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        items(adminOptions) {
            OptionCardView(vm, it)
        }
    }

    SwitchFuncButton(
        modifier = Modifier,
        imageVector = Icons.Filled.Person
    ) {
        vm.navigateTo(NavRoutes.Scan.screenRoute)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionCardView(vm: AdminOptionsViewModel, element: AdminCardViewElement) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        border = BorderStroke(6.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            vm.navigateTo(element.route)
        }
    ) {
        Column(
            modifier = Modifier.padding(22.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(element.image),
                contentDescription = element.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = element.name,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
        }
    }
}