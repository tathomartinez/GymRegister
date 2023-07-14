package com.tatho.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemMenuText(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .offset(8.dp, 0.dp)
            .width(272.dp)
            .height(60.dp)
            .padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        ItemMenuTitle(title)
        ItemMenuSubtitle(subtitle)
    }
}
