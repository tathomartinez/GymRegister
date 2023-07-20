package com.tatho.exercise_presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.LIGTHGRAYAPP
import com.tatho.common.theme.REDSTOPAPP
import com.tatho.common.theme.VANISHTEXT
import com.tatho.common.theme.WHITEAPP
import com.tatho.exercise_domain.model.ExerciseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun RoutineButton(modifier: Modifier, onValueChange: (Boolean) -> Unit) {
    var isRoutineActive by remember { mutableStateOf(false) }

    val goColors = ButtonDefaults.buttonColors(
        backgroundColor = BASECOLOR,
        contentColor = WHITEAPP,
        disabledContentColor = Color.Gray,
        disabledBackgroundColor = Color.Transparent,
    )

    val stopColors = ButtonDefaults.buttonColors(
        backgroundColor = REDSTOPAPP,
        contentColor = WHITEAPP,
        disabledContentColor = Color.Gray,
        disabledBackgroundColor = Color.Transparent,
    )

    Button(
        onClick = {
            isRoutineActive = !isRoutineActive
            onValueChange(isRoutineActive)
        },
        colors = if (!isRoutineActive) goColors else stopColors,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .wrapContentSize()
            .padding(16.dp),
    ) {
        Text(
            text = if (!isRoutineActive) "Activar Rutina" else "Parar Rutina",
        )
    }
}

@Composable
fun HeaderProgress(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = "Actividad Semanal",
        style = TextStyle(
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            color = BASECOLOR
        )
    )
}

@Composable
fun CalendarCustom(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {

        CalendarioItemCustom(dia = "dom", date = "16", now = false, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "lun", date = "17", now = false, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "mar", date = "18", now = false, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "mie", date = "19", now = false, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "jue", date = "20", now = true, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "vie", date = "21", now = false, modifier = Modifier.weight(1f))
        CalendarioItemCustom(dia = "sab", date = "22", now = false, modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomLinearProgress(
    currentValue: Float,
    minValue: Float,
    maxValue: Float,
    color: Color = BASECOLOR,
    modifier: Modifier = Modifier
) {
    val progress = calculateProgress(minValue, maxValue, currentValue)
    ConstraintLayout(modifier = modifier) {
        val (text, minValueAnchor, maxValueAnchor, progressBar) = createRefs()
        Text(
            text = "Puntos Semanales",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = BASECOLOR,
                fontSize = 24.sp
            ),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        LinearProgressIndicator(
            progress = progress,
            color = color,
            backgroundColor = VANISHTEXT,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(progressBar) {
                    top.linkTo(text.bottom, 8.dp)
                    start.linkTo(parent.start)
                }
        )
        Text(
            text = "${currentValue.toInt()} pts",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = BASECOLOR,
                fontSize = 12.sp
            ),
            modifier = Modifier.constrainAs(minValueAnchor) {
                top.linkTo(progressBar.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "${maxValue.toInt()} pts",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = LIGTHGRAYAPP,
                fontSize = 12.sp
            ),
            modifier = Modifier.constrainAs(maxValueAnchor) {
                top.linkTo(progressBar.bottom)
                end.linkTo(parent.end)
            }
        )
    }

}

@Composable
fun calculateProgress(minValue: Float, maxValue: Float, currentValue: Float): Float {
    return (currentValue - minValue) / (maxValue - minValue)
}

@Composable
fun CalendarioItemCustom(
    modifier: Modifier = Modifier,
    dia: String = "mon",
    date: String = "00",
    now: Boolean = false
) {
    val colorBg = if (now) BASECOLOR else WHITEAPP
    val colorText = if (now) WHITEAPP else LIGTHGRAYAPP

    Card(
        modifier = modifier,
        backgroundColor = colorBg
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            val (label, number) = createRefs()
            Text(
                text = dia,
                modifier = Modifier.constrainAs(label) {
                    top.linkTo(parent.top, 2.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    centerHorizontallyTo(parent)
                },
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = colorText,
                    fontSize = 16.sp
                )
            )
            Text(
                text = date,
                modifier = Modifier.constrainAs(number) {
                    top.linkTo(label.bottom, 1.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end, 8.dp)
                    centerHorizontallyTo(parent)
                },
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    color = colorText,
                    fontSize = 24.sp,
                )
            )
        }

    }
}


@Composable
fun ItemExercise(
    exerciseModel: ExerciseModel,
    onItemChange: (ExerciseModel) -> Unit
) {
    var weight by remember { mutableStateOf(exerciseModel.peso) }
    var series by remember { mutableStateOf(exerciseModel.series) }
    var repeticiones by remember { mutableStateOf(exerciseModel.repeticiones) }

    fun onValueChange() {
        //Todo evaluar que los tres items sean diferentes a 0 y guardar los datos en un objeto ExerciseModel
//        Log.e("Hola mundo", weight.toString())
        if (weight != 0 && series != 0 && repeticiones != 0) {
//            Log.e("Hola mundo", exerciseModel.toString())
            exerciseModel.apply {
                this.peso = weight
                this.series = series
                this.repeticiones = repeticiones
            }
            Log.e("Hola mundo", exerciseModel.toString())
            onItemChange(exerciseModel)
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ConstraintLayout {
            val (leftIcon, text, repeticionesDropMenu, SeriesDropMenu, contador) = createRefs()
            Card(modifier = Modifier
                .size(64.dp)
                .constrainAs(leftIcon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Logout Icon",
                    tint = BASECOLOR,
                )
            }
            Text(text = exerciseModel.name, style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            ), modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top, 8.dp)
                start.linkTo(leftIcon.end, 8.dp)
            })
            DropMenuCustom(
                onItemChange = {
                    repeticiones = it.toInt()
                    onValueChange()
                },
                placeholder = "Repet.",
                modifier = Modifier.constrainAs(repeticionesDropMenu) {
                    top.linkTo(text.bottom, 8.dp)
                    start.linkTo(leftIcon.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                })
            DropMenuCustom(
                onItemChange = {
                    series = it.toInt()
                    onValueChange()
                },
                placeholder = "Series", modifier = Modifier.constrainAs(SeriesDropMenu) {
                    top.linkTo(text.bottom, 8.dp)
                    start.linkTo(repeticionesDropMenu.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                })
            ContadorPeso(
                initialWeight = exerciseModel.peso,
                onWeightChange = {
                    weight = it
                    onValueChange()
                },
                modifier = Modifier.constrainAs(contador) {
                    top.linkTo(text.bottom, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                },
            )
        }
    }
}

@Composable
fun DropMenuCustom(
    modifier: Modifier,
    placeholder: String,
    item: String = "",
    onItemChange: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedValue by remember { mutableStateOf(item) }

    val menuItems = listOf("5", "6", "7", "8", "9", "10", "11", "12")

    ConstraintLayout(modifier = modifier
        .shadow(1.dp)
        .clickable {
            expanded = !expanded
        }) {
        val (leftIcon, text, rightIcon) = createRefs()
        Text(
            text = if (selectedValue.isEmpty()) placeholder else "$selectedValue x", // Mostrar valor seleccionado o el placeholder
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            color = BASECOLOR
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Logout Icon",
            tint = BASECOLOR,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(leftIcon) {
                    start.linkTo(text.end, 2.dp)
                    centerVerticallyTo(parent)
                }
        )
        DropdownMenu(
            modifier = Modifier.constrainAs(rightIcon) {
                top.linkTo(leftIcon.top)
            },
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            menuItems.forEach { item ->
                Text(
                    text = "$item x",
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            expanded = false
                            selectedValue = item
                            onItemChange(item)
                        },
                    color = BASECOLOR
                )
            }
        }
    }


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContadorPeso(modifier: Modifier, initialWeight: Int, onWeightChange: (Int) -> Unit) {
    var weight by remember { mutableStateOf(initialWeight) }
    var job by remember { mutableStateOf<Job?>(null) } // Almacena la referencia a la corrutina

    ConstraintLayout(modifier = modifier) {
        val (leftIcon, text, rightIcon) = createRefs()
        Icon(
            imageVector = Icons.Default.RemoveCircle,
            contentDescription = "Remove weight Icon",
            tint = BASECOLOR,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(leftIcon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
                .combinedClickable(
                    onClick = {
                        if (weight >= 10) { // Verificar que el peso sea mayor o igual a 10 antes de disminuirlo
                            weight -= 10
                            onWeightChange(weight)
                        }
                    },
                    onLongClick = {
                        job = CoroutineScope(Dispatchers.Default).launch {
                            while (true) {
                                delay(100) // Ajusta el tiempo para el efecto deseado
                                weight -= 10
                                withContext(Dispatchers.Main) {
                                    onWeightChange(weight)
                                }
                            }
                        }
                    },

                )

        )
        Text(text = "$weight Kg", Modifier.constrainAs(text) {
            start.linkTo(leftIcon.end)
            centerVerticallyTo(parent)
        })
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = "Add weight Icon",
            tint = BASECOLOR,
            modifier = Modifier
                .size(24.dp)
                .constrainAs(rightIcon) {
                    start.linkTo(text.end)
                    centerVerticallyTo(parent)
                }
                .clickable {
                    weight += 10
                    onWeightChange(weight)
                }
        )
    }
}

@Preview
@Composable
fun ContadorPesoPreview() {
    ContadorPeso(modifier = Modifier, initialWeight = 100, onWeightChange = {})
}