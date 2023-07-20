package com.tatho.sing_presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tatho.common.AlertDialogCustom
import com.tatho.common.ButtonComponent
import com.tatho.common.CheckboxComponent
import com.tatho.common.CreateAccount
import com.tatho.common.EmailInputCustom
import com.tatho.common.GymEmailFieldComponent
import com.tatho.common.GymPasswordTextFieldComponent
import com.tatho.common.HeaderTextComponent
import com.tatho.common.NormalTextComponent
import com.tatho.common.PasswordInputCustom
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.BackGroundColor
import com.tatho.common.theme.LightGray
import com.tatho.common.theme.StringApp
import com.tatho.common.theme.fontApp
import com.tatho.common.theme.fontRegularApp
import com.tatho.sing_presentation.exception.SingUpViewException


//@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NewSignUpScreen(navNext: (String) -> Unit, viewModel: SignUpViewModel) {

    if (viewModel.navigateToNextScreen.value) {
        navNext("main")
        return
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val marginH = (screenWidth * 0.1f).value.toInt().dp
    val marginV = (screenHeight * 0.2f).value.toInt().dp

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (header, titulo, subtitulo, email, password, rPassword, btnRegister, btnLogin) = createRefs()
        if (viewModel.showError.value) {
            AlertDialogCustom("Error al Crear el usuario")
            return@ConstraintLayout
        }

        if (!viewModel.showError.value) {
            val emailValue by viewModel.emailFieldState
            val passwordValue by viewModel.passwordFieldState
            val repeatPassword by viewModel.repeatPasswordFieldState
            HeaderRegister(
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top, marginV)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            TitleText(modifier = Modifier.constrainAs(titulo) {
                top.linkTo(header.bottom, margin = 24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            SubtitleText(modifier = Modifier.constrainAs(subtitulo) {
                top.linkTo(titulo.bottom, margin = 24.dp)
                start.linkTo(parent.start, margin = 100.dp)
                end.linkTo(parent.end, margin = 100.dp)
            })

            EmailInputCustom(value = emailValue, modifier = Modifier
                .constrainAs(email) {
                    top.linkTo(subtitulo.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    width = Dimension.fillToConstraints
                }) { viewModel.onEmailFieldChanged(it) }

            PasswordInputCustom(passwordValue, modifier = Modifier
                .constrainAs(password) {
                    top.linkTo(email.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    width = Dimension.fillToConstraints
                }) { viewModel.onPasswordFieldChanged(it) }

            PasswordInputCustom(repeatPassword, modifier = Modifier.constrainAs(rPassword) {
                top.linkTo(password.bottom, margin = 4.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            }) { viewModel.onRepeatPasswordFieldChanged(it) }

            CreateAccount(modifier = Modifier.constrainAs(btnLogin) {
                bottom.linkTo(btnRegister.top, margin = 2.dp)
                centerHorizontallyTo(parent)
            }, isEnabled = true, onButtonClicked = { navNext("login") })

            CreateAccount(modifier = Modifier.constrainAs(btnRegister) {
                bottom.linkTo(parent.bottom, margin = 24.dp)
                centerHorizontallyTo(parent)
            }, isEnabled = true, onButtonClicked = {
                try {
                    viewModel.register()
                } catch (e: SingUpViewException) {
                    Log.e("Error", "Muchos errores")
                }
            })
        }
    }
}

@Composable
private fun TitleText(modifier: Modifier = Modifier) {
    Text(
        text = "Registrarse",
        fontSize = 16.sp,
        color = Color.Black,
        modifier = modifier,
        fontFamily = FontFamily(Font(fontApp))
    )
}

@Composable
private fun SubtitleText(modifier: Modifier = Modifier) {
    Text(
        text = "Define un usuario y password para continuar",
        fontSize = 16.sp,
        color = LightGray,
        modifier = modifier,
        fontFamily = FontFamily(Font(fontRegularApp))
    )
}

@Composable
fun HeaderRegister(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "Register",
            fontSize = 28.sp,
            color = BASECOLOR
        )
        Text(
            text = "GYM",
            fontSize = 48.sp,
        )
    }
}

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