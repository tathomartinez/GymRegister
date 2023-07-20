package com.tatho.showsize_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatho.showsize_domain.model.BodyMeasurements

@Composable
fun ShowSizeScreen(navegarTo: (String) -> Unit, viewModel: ShowSizeViewModel) {
    val medidas by viewModel.medidas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getShowSize()
    }
//    MagnitudeChart(?: emptyList())
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(medidas) { medida ->
                ListItem(medida)
            }
        }
    }
}

@Composable
fun ListItem(medida: BodyMeasurements) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Chest: ${medida.chest}",
                fontSize = 16.sp
            )
            Text(
                text = "Waist: ${medida.waist}",
                fontSize = 16.sp
            )
            Text(
                text = "Bicep: ${medida.bicep}",
                fontSize = 16.sp
            )
            Text(
                text = "Gluteus: ${medida.gluteus}",
                fontSize = 16.sp
            )
            Text(
                text = "Back: ${medida.back}",
                fontSize = 16.sp
            )
            Text(
                text = "Date: ${medida.date}",
                fontSize = 16.sp
            )
        }
    }
}

