package com.tatho.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ItemMenuSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        fontSize = 12.sp,
        color = Color(0xFF707382),
        letterSpacing = 0.25.sp
    )
}