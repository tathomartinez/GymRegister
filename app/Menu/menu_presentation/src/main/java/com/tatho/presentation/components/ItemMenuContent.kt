package com.tatho.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemMenuContent(title: String, subtitle: String, directionIcon: String) {
    Row(
        Modifier
            .offset(x = 8.dp, y = 8.dp)
            .width(296.dp)
            .height(68.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemMenuIcon(directionIcon)
        ItemMenuText(title, subtitle)
    }
}
