package com.tatho.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.ButtonComponent
import com.tatho.common.GymTextFieldComponent
import com.tatho.common.HeaderTextComponent
import com.tatho.common.NormalTextComponent
import com.tatho.common.theme.BackGroundColor
import com.tatho.presentation.constans.UiConstants.BACK
import com.tatho.presentation.constans.UiConstants.BICEP
import com.tatho.presentation.constans.UiConstants.CHEST
import com.tatho.presentation.constans.UiConstants.GLUTEUS
import com.tatho.presentation.constans.UiConstants.REGISTRAMEDIDAS
import com.tatho.presentation.constans.UiConstants.SAVE
import com.tatho.presentation.constans.UiConstants.WAIST

//@Preview
//@Composable
//fun DefaultPreview() {
//    val navController = rememberNavController()
//    BodyMeasurementScreen {
//        _navController()
//    }
//}
//
//fun _navController() {
//    TODO("Not yet implemented")
//}

@Composable
fun BodyMeasurementScreen(navNext: () -> Unit) {
    ConstraintLayout(
        modifier =
        Modifier
            .background(BackGroundColor)
    ) {

        val (screen, btnRegister) = createRefs()

        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 28.dp)
                    end.linkTo(parent.end, margin = 28.dp)
                    bottom.linkTo(parent.bottom)
                }) {

            Column() {
                NormalTextComponent(value = "dd/mm/yyyy")
                HeaderTextComponent(value = REGISTRAMEDIDAS)
                GymTextFieldComponent(
                    label = CHEST,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = CHEST
                )
                GymTextFieldComponent(
                    label = WAIST,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = WAIST
                )
                GymTextFieldComponent(
                    label = BICEP,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = BICEP
                )
                GymTextFieldComponent(
                    label = GLUTEUS,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = GLUTEUS
                )
                GymTextFieldComponent(
                    label = BACK,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = BACK
                )
            }
        }


        ButtonComponent(
            value = SAVE,
            onButtonClicked = {
                navNext()
            },
            isEnabled = true,
            modifier = Modifier
                .constrainAs(btnRegister) {
                    top.linkTo(screen.bottom)
                    start.linkTo(screen.start, margin = 24.dp)
                    end.linkTo(screen.end, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)

                }
        )
    }

}