package com.tatho.gymregis.presentations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.tatho.common.*
import com.tatho.common.theme.BackGroundColor
import com.tatho.gymregis.R


@Preview
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    SingUpScreen {
        _navController()
    }
}

fun _navController() {
}

@Composable
fun SingUpScreen(navNext: () -> Unit) {

    ConstraintLayout(
        modifier =
        Modifier
            .background(BackGroundColor)
    ) {

        val (screen, btnRegister) = createRefs()

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .background(BackGroundColor)
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, margin = 28.dp)
                    end.linkTo(parent.end, margin = 28.dp)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackGroundColor)
            ) {
                NormalTextComponent(value = stringResource(id = R.string.greeting))
                HeaderTextComponent(value = stringResource(id = R.string.createAccountText))
                Spacer(modifier = Modifier.height(24.dp))
                GymEmailFieldComponent(
                    label = stringResource(id = R.string.email), icon =
                    Icons.Default.AccountCircle,
                    contentDescription = "any"
                )
                GymPasswordTextFieldComponent(
                    label = stringResource(id = R.string.password)
                )
                GymPasswordTextFieldComponent(
                    label = stringResource(id = R.string.repetPassword)
                )
                CheckboxComponent("Hola", onTextSelected = {
                    //TODO evento del viewmodel
                }, onCheckedChange = {
                    //TODO evento del viewmodel
                    //TODO Cuando este cambie debe pasarse al componente button para activar o desactivar el boton
                })
                Spacer(modifier = Modifier.height(48.dp))
            }
        }

        ButtonComponent(
            value = stringResource(id = R.string.singUp),
            onButtonClicked = {
                navNext()
            },
            isEnabled = true,
            modifier = Modifier
//                .fillMaxWidth()
                .constrainAs(btnRegister) {
//                    top.linkTo(screen.bottom)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)

                }
        )
    }

}