package com.tatho.sing_presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.*
import com.tatho.common.theme.BackGroundColor
import com.tatho.common.theme.StringApp
import com.tatho.sing_presentation.exception.SingUpViewException

@Composable
fun SingUpScreen(navNext: (String) -> Unit, viewModel: SignUpViewModel) {

    if (viewModel.navigateToNextScreen.value) {
        navNext("main")
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(8.dp)
    ) {

        val (screen, btnRegister, btnLogin) = createRefs()

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
                value = stringResource(id = StringApp.singUP),
                onButtonClicked = {
                    try {
                        viewModel.register()
                    } catch (e: SingUpViewException) {
                        Log.e("Error", "Muchos errores")
                    }
                },
                isEnabled = true,
                modifier = Modifier
                    .constrainAs(btnRegister) {
                        start.linkTo(screen.start, 16.dp)
                        end.linkTo(screen.end, 16.dp)
                        bottom.linkTo(parent.bottom, margin = 24.dp)
                    }
            )

            ButtonComponent(
                value = stringResource(id = StringApp.login),
                onButtonClicked = {
                    navNext("login")
                    // Acciones al hacer clic en el nuevo botón de logout
                },
                isEnabled = true,
                modifier = Modifier
                    .constrainAs(btnLogin) {
                        start.linkTo(screen.start, 80.dp)
                        end.linkTo(screen.end, 80.dp)
                        bottom.linkTo(btnRegister.top, margin = 8.dp)
                    }
            )
        }
    }
}