package com.tatho.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.*
import com.tatho.common.theme.BackGroundColor
import com.tatho.presentation.constans.UiConstants.BACK
import com.tatho.presentation.constans.UiConstants.BICEP
import com.tatho.presentation.constans.UiConstants.CHEST
import com.tatho.presentation.constans.UiConstants.GLUTEUS
import com.tatho.presentation.constans.UiConstants.REGISTRAMEDIDAS
import com.tatho.presentation.constans.UiConstants.SAVE
import com.tatho.presentation.constans.UiConstants.WAIST

@Composable
fun BodyMeasurementScreen(navegateTo: () -> Unit, viewModel: BodyMeasurementViewModel) {
    val chestFocusRequester = remember { FocusRequester() }
    val waistFocusRequester = remember { FocusRequester() }
    val bicepFocusRequester = remember { FocusRequester() }
    val gluteusFocusRequester = remember { FocusRequester() }
    val backFocusRequester = remember { FocusRequester() }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(8.dp)
    ) {

        val (screen, btnRegister) = createRefs()

        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }) {

            Column() {
                NormalTextComponent(value = "dd/mm/yyyy")
                HeaderTextComponent(value = REGISTRAMEDIDAS)
                SizeTextFieldComponent(
                    label = CHEST,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = CHEST,
                    focusRequester = chestFocusRequester,
                    nextFocusRequester = waistFocusRequester,
                    value = viewModel.chestValue.value.toString(),
                    onValueChange = { newValue ->
                        viewModel.chestValue.value = newValue.toIntOrNull() ?: 0
                    }
                )
                SizeTextFieldComponent(
                    label = WAIST,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = WAIST,
                    focusRequester = waistFocusRequester,
                    nextFocusRequester = bicepFocusRequester,
                    value = viewModel.waistValue.value.toString(),
                    onValueChange = { newValue ->
                        viewModel.waistValue.value = newValue.toIntOrNull() ?: 0
                    }
                )
                SizeTextFieldComponent(
                    label = BICEP,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = BICEP,
                    focusRequester = bicepFocusRequester,
                    nextFocusRequester = gluteusFocusRequester,
                    value = viewModel.bicepValue.value.toString(),
                    onValueChange = { newValue ->
                        viewModel.bicepValue.value = newValue.toIntOrNull() ?: 0
                    }
                )
                SizeTextFieldComponent(
                    label = GLUTEUS,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = GLUTEUS,
                    focusRequester = gluteusFocusRequester,
                    nextFocusRequester = backFocusRequester,
                    value = viewModel.gluteusValue.value.toString(),
                    onValueChange = { newValue ->
                        viewModel.gluteusValue.value = newValue.toIntOrNull() ?: 0
                    }
                )
                SizeTextFieldComponent(
                    label = BACK,
                    icon = Icons.Default.FitnessCenter,
                    contentDescription = BACK,
                    focusRequester = backFocusRequester,
                    value = viewModel.backValue.value.toString(),
                    onValueChange = { newValue ->
                        viewModel.backValue.value = newValue.toIntOrNull() ?: 0
                    }
                )
            }
        }

        ButtonComponent(
            value = SAVE,
            onButtonClicked = {
                viewModel.guardar()
            },
            isEnabled = true,
            modifier = Modifier
                .constrainAs(btnRegister) {
                    top.linkTo(screen.bottom, margin = 8.dp)
                    start.linkTo(screen.start)
                    end.linkTo(screen.end)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                }
        )
    }
}
