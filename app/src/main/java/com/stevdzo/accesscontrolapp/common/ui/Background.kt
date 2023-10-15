package com.stevdzo.accesscontrolapp.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.stevdzo.accesscontrolapp.R

@Composable
fun Background(topPadding: Double) {
    Image(
        painter = painterResource(id = R.drawable.bgr),
        contentDescription = "Background",
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),
        contentScale = ContentScale.FillBounds
    )
}