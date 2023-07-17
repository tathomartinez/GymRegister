package com.tatho.showsize_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.tatho.showsize_domain.model.Datos

@Composable
fun MagnitudeChart(toDatosList: List<Datos>) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Barras(datos = toDatosList)
    }
}

@Composable
fun Barras(datos: List<Datos>) {
//    val datos: List<Datos> = listOf(
//        Datos("Chest", 12f),
//        Datos("Waist", 12f),
//        Datos("Bicep", 12f),
//    )

    var barras = ArrayList<BarChartData.Bar>()
    datos.forEach { datos ->
        barras.add(
            BarChartData.Bar(
                label = datos.label,
                value = datos.value,
                color = Color.Red
            )
        )
    }

    BarChart(
        barChartData = BarChartData(
            bars = barras
        ),
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 80.dp)
            .height(300.dp),
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.XAxis
        )
    )

}
