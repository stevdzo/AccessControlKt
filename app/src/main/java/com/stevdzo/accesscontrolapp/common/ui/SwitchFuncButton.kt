package com.stevdzo.accesscontrolapp.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SwitchFuncButton(modifier: Modifier, imageVector: ImageVector, onSwitchFunButton: () -> Unit) {
    Box(
        modifier = modifier
            .scale(0.8f)
            .padding(0.dp, 40.dp, 0.dp, 0.dp)
            .background(
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(50)
            )
    ) {
        IconButton(
            modifier = Modifier
                .background(Color.Transparent)
                .scale(1f)
                .clip(CircleShape),
            colors = IconButtonDefaults.filledIconButtonColors(
                contentColor = Color.White
            ),
            onClick = {
                onSwitchFunButton()
            }
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "Switch",
                tint = Color.White
            )
        }
    }
}