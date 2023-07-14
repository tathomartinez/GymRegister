package com.tatho.sing_presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.*
import com.tatho.common.theme.BackGroundColor
import com.tatho.sing_presentation.exception.SingUpViewException

@Composable
fun SingUpScreen(navNext: () -> Unit, viewModel: SignUpViewModel) {

    if (viewModel.navigateToNextScreen.value) {
        navNext()
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

//                if (viewModel.showSuccess.value) {
//                    AlertDialogCustom("Nuevo usuario creado")
//                }

                if (viewModel.showError.value) {
                    AlertDialogCustom("Error al Crear el usuario")
                }

                if (!viewModel.showError.value) {
                    NormalTextComponent(value = stringResource(id = R.string.greeting))
                    HeaderTextComponent(value = stringResource(id = R.string.createAccountText))
                    Spacer(modifier = Modifier.height(24.dp))
                    val email by viewModel.emailFieldState
                    val password by viewModel.passwordFieldState
                    val repeatPassword by viewModel.repeatPasswordFieldState
                    GymEmailFieldComponent(
                        label = stringResource(id = R.string.email),
                        icon = Icons.Default.AccountCircle,
                        contentDescription = "any",
                        value = email,
                        onValueChange = { viewModel.onEmailFieldChanged(it) }
                    )
                    GymPasswordTextFieldComponent(
                        label = stringResource(id = R.string.password),
                        value = password,
                        onValueChange = { viewModel.onPasswordFieldChanged(it) }
                    )
                    GymPasswordTextFieldComponent(
                        label = stringResource(id = R.string.repetPassword),
                        value = repeatPassword,
                        onValueChange = { viewModel.onRepeatPasswordFieldChanged(it) }
                    )
                    CheckboxComponent(
                        "Hola",
                        onTextSelected = {
                            viewModel.onCheckboxSelected()
                        },
                        onCheckedChange = {
                            viewModel.onCheckboxCheckedChange(it)
                        }
                    )
                    NormalTextComponent(value = "")
                    HeaderTextComponent(value = "")
                    Column() {
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp)
                        )
                    }
                }
            }
        }

        if (!viewModel.showError.value) {
            ButtonComponent(
                value = stringResource(id = R.string.singUp),
                onButtonClicked = {
                    try {
                        viewModel.register()
                    } catch (e: SingUpViewException) {
                        Log.e("Error", "Muchos errores")
                        // Manejar la excepción SingUpViewException
                    }
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