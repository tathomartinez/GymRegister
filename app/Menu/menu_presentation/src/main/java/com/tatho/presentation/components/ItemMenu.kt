package com.tatho.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ItemMenu(title: String, subtitle: String, directionIcon: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,

        ) {
        ItemMenuContent(title, subtitle, directionIcon)
    }
}
