package com.tatho.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.ButtonComponent
import com.tatho.common.HeaderTextComponent
import com.tatho.common.NormalTextComponent
import com.tatho.common.SizeTextFieldComponent
import com.tatho.common.theme.BackGroundColor
import com.tatho.common.theme.fontApp
import com.tatho.presentation.constans.UiConstants.BACK
import com.tatho.presentation.constans.UiConstants.BICEP
import com.tatho.presentation.constans.UiConstants.CHEST
import com.tatho.presentation.constans.UiConstants.GLUTEUS
import com.tatho.presentation.constans.UiConstants.REGISTRAMEDIDAS
import com.tatho.presentation.constans.UiConstants.SAVE
import com.tatho.presentation.constans.UiConstants.WAIST
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BodyMeasurementScreen(navegateTo: () -> Unit, viewModel: BodyMeasurementViewModel) {
    val chestFocusRequester = remember { FocusRequester() }
    val waistFocusRequester = remember { FocusRequester() }
    val bicepFocusRequester = remember { FocusRequester() }
    val gluteusFocusRequester = remember { FocusRequester() }
    val backFocusRequester = remember { FocusRequester() }

    val currentDate = remember {
        val now = viewModel.getCurrentDate()
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(now)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(8.dp)
    ) {

        val (screen, btnRegister) = createRefs()

        ConstraintLayout(
            Modifier
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(parent.bottom)
                }) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                if (viewModel.showSuccess.value) {
                    AlertDialogCustom("Insertion successful")
                }

                if (viewModel.showError.value) {
                    AlertDialogCustom("Medidas ya registradas el dia de hoy vuelve pronto")
                }

                if (!(viewModel.showSuccess.value || viewModel.showError.value)) {
                    NormalTextComponent(value = currentDate)
                    HeaderTextComponent(value = REGISTRAMEDIDAS)
                    Column() {
                        SizeTextFieldComponent(
                            label = CHEST,
                            icon = Icons.Default.FitnessCenter,
                            contentDescription = CHEST,
                            focusRequester = chestFocusRequester,
                            nextFocusRequester = waistFocusRequester,
                            value = if (viewModel.chestValue.value == 0) "" else viewModel.chestValue.value.toString(),
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
                            value = if (viewModel.waistValue.value == 0) "" else viewModel.waistValue.value.toString(),
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
                            value = if (viewModel.bicepValue.value == 0) "" else viewModel.bicepValue.value.toString(),
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
                            value = if (viewModel.gluteusValue.value == 0) "" else viewModel.gluteusValue.value.toString(),
                            onValueChange = { newValue ->
                                viewModel.gluteusValue.value = newValue.toIntOrNull() ?: 0
                            }
                        )
                        SizeTextFieldComponent(
                            label = BACK,
                            icon = Icons.Default.FitnessCenter,
                            contentDescription = BACK,
                            focusRequester = backFocusRequester,
                            value = if (viewModel.backValue.value == 0) "" else viewModel.backValue.value.toString(),
                            onValueChange = { newValue ->
                                viewModel.backValue.value = newValue.toIntOrNull() ?: 0
                            }
                        )
                    }
                }
            }
        }
        if (!(viewModel.showSuccess.value || viewModel.showError.value)) {
            ButtonComponent(
                value = SAVE,
                onButtonClicked = {
                    viewModel.save()
                },
                isEnabled = true,
                modifier = Modifier
                    .constrainAs(btnRegister) {
                        start.linkTo(screen.start)
                        end.linkTo(screen.end)
                        bottom.linkTo(parent.bottom, margin = 24.dp)
                    }
            )
        }
    }
}

@Composable
fun AlertDialogCustom(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
            backgroundColor = Color.White,
            elevation = 4.dp
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp),
                    fontFamily = FontFamily(Font(fontApp)),
                    fontSize = 18.sp
                )
            }
        }
    }
}
