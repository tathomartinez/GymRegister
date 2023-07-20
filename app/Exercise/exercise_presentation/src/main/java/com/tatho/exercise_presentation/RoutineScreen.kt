package com.tatho.exercise_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.WHITEAPP


@Composable
fun RoutineScreen() {
    ConstraintLayout(
        modifier = Modifier
            .background(WHITEAPP)
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        val (header, calendar, progressBarAnchor, buttonRutine, body) = createRefs()
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
        RutineBotton(
            modifier = Modifier.constrainAs(buttonRutine) {
                top.linkTo(progressBarAnchor.bottom, 16.dp)
                centerHorizontallyTo(parent)
            }
        )
        Column(modifier = Modifier.constrainAs(body) {
            top.linkTo(buttonRutine.bottom, 16.dp)
        }) {
            ItemExercise()
        }
    }
}
