package com.tatho.exercise_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.WHITEAPP
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tatho.common.theme.BASECOLOR

@Composable
fun RoutineScreen(
    navegateTo: (String) -> Unit,
    viewModel: RoutineViewModel = viewModel(),
) {
    ConstraintLayout(
        modifier = Modifier
            .background(WHITEAPP)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        var isRoutineActive by remember { mutableStateOf(false) }
        val (header, calendar, progressBarAnchor, buttonRoutine, body, errorText) = createRefs()
        val error by viewModel.error.collectAsState()
        val success by viewModel.success.collectAsState()
        HeaderProgress(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top, 24.dp)
        })
        CalendarCustom(modifier = Modifier
            .constrainAs(calendar) {
                top.linkTo(header.bottom, 8.dp)
            }
            .fillMaxWidth())
        CustomLinearProgress(
            modifier = Modifier
                .constrainAs(progressBarAnchor) {
                    top.linkTo(calendar.bottom, 24.dp)
                }
                .fillMaxWidth(),
            currentValue = 1750f, minValue = 1000f, maxValue = 2000f)
        RoutineButton(
            onValueChange = {
                isRoutineActive = it
            },
            modifier = Modifier.constrainAs(buttonRoutine) {
                top.linkTo(progressBarAnchor.bottom, 16.dp)
                centerHorizontallyTo(parent)
            }
        )
        Column(modifier = Modifier.constrainAs(body) {
            top.linkTo(buttonRoutine.bottom, 16.dp)
        }) {
            ItemExercise(
                repeticionesChange = { newValue -> viewModel.onRepeticionesChange(newValue) },
                seriesChange = { newValue -> viewModel.onSeriesChange(newValue) },
                onWeightChange = { newValue -> viewModel.onWeightChange(newValue) }
            )
        }
        LaunchedEffect(isRoutineActive) {
            if (!isRoutineActive) {
                // Llamada a la función en el ViewModel cuando isRoutineActive cambia a false
                viewModel.onIsRoutineActiveChanged()
            }
        }
        if (error.isNotEmpty()) {
            Text(
                text = error,
                modifier = Modifier.constrainAs(errorText) {
                    // Posiciona el Text en la parte inferior del composable
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
        if (success) {
            Text(
                text = "Rutina creada",
                style = TextStyle(
                    color = BASECOLOR,
                    fontSize = 32.sp
                ),
                modifier = Modifier.constrainAs(errorText) {
                    // Posiciona el Text en la parte inferior del composable
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
    // Efecto secundario para ejecutar una función cuando isRoutineActive cambie de true a false

}
