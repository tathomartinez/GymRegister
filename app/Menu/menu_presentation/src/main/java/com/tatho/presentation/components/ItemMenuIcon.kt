package com.tatho.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tatho.menu_presentation.R

@Composable
fun ItemMenuIcon(directionIcon: String) {
    Image(
        modifier = Modifier
            .background(color = Color(0xFF00C4B4), shape = RoundedCornerShape(size = 4.dp)),
        painter = painterResource(id = getResId("ic_${directionIcon}", R.drawable::class.java)),
        contentDescription = "Icono menu",
    )
}

private fun getResId(
    resourceName: String,
    cls: Class<*>,
): Int {
    return try {
        val field = cls.getDeclaredField(resourceName)
        field.getInt(null)
    } catch (e: Exception) {
        0
    }
}
