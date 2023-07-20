package com.tatho.login_presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tatho.common.ButtonComponent
import com.tatho.common.EmailInputCustom
import com.tatho.common.GymEmailFieldComponent
import com.tatho.common.GymPasswordTextFieldComponent
import com.tatho.common.HeaderTextComponent
import com.tatho.common.LoginButton
import com.tatho.common.NormalTextComponent
import com.tatho.common.PasswordInputCustom
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.BackGroundColor
import com.tatho.common.theme.LIGTHGRAYAPP
import com.tatho.common.theme.StringApp
import com.tatho.common.theme.darkTheme
import com.tatho.common.theme.WHITEAPP

@Composable
fun LoginScreen(navNext: (String) -> Unit, viewModel: LoginViewModel) {

    val loginResult by viewModel.loginResult.collectAsState(null)

    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(darkTheme),
    ) {

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp
        val marginH = (screenWidth * 0.1f).value.toInt().dp
        val marginV = (screenHeight * 0.2f).value.toInt().dp
        val emailVM by viewModel.emailFieldState
        val passwordVM by viewModel.passwordFieldState
        val (header, titulo, subtitulo, email, password, textLink, btnRegister, btnLogin, textRegis) = createRefs()
        LaunchedEffect(loginResult) {
            loginResult?.let { result ->
                when (result) {
                    is LoginResult.Success -> {
                        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                        navNext("main")
                    }

                    is LoginResult.Failure -> {
                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        HeaderRegister(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top, marginV)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        SubtitleText(modifier = Modifier.constrainAs(subtitulo) {
            top.linkTo(header.bottom, margin = 24.dp)
            start.linkTo(parent.start, margin = 40.dp)
            end.linkTo(parent.end, margin = 40.dp)
            width = Dimension.fillToConstraints
        })

        EmailInputCustom(modifier = Modifier.constrainAs(email) {
            top.linkTo(subtitulo.bottom, margin = 24.dp)
        },
            value = emailVM,
            onValueChange = { viewModel.onEmailFieldChanged(it) })

        PasswordInputCustom(
            modifier = Modifier.constrainAs(password) {
                top.linkTo(email.bottom, margin = 24.dp)
            },
            value = passwordVM,
            onValueChange = { viewModel.onPasswordFieldChanged(it) }
        )
        Text(text = "Ovide la contraseña",
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.constrainAs(textLink) {
                top.linkTo(password.bottom, margin = 24.dp)
                start.linkTo(parent.start, margin = 40.dp)
                end.linkTo(parent.end, margin = 40.dp)
                width = Dimension.fillToConstraints
            })

        LoginButton(modifier = Modifier.constrainAs(btnLogin) {
            bottom.linkTo(textRegis.top, margin = 1.dp)
            centerHorizontallyTo(parent)
        }, isEnabled = true, onButtonClicked = { viewModel.login() })

        Text(text = "Registrarse",
            style = TextStyle(
                textAlign = TextAlign.Center,
            ),
            color = BASECOLOR,
            modifier = Modifier
                .constrainAs(textRegis) {
                    centerHorizontallyTo(parent)
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                }
                .clickable {
                    navNext("singup")
                })
    }
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
            color = WHITEAPP
        )
    }
}

@Composable
private fun SubtitleText(modifier: Modifier = Modifier) {
    Text(
        style = TextStyle(
            textAlign = TextAlign.Center,
        ),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        fontSize = 16.sp,
        color = LIGTHGRAYAPP,
        modifier = modifier
    )
}

@Composable
fun LoginScreenOld(navNext: (String) -> Unit, viewModel: LoginViewModel) {

    // ...

    val loginResult by viewModel.loginResult.collectAsState(null)

    val context = LocalContext.current

    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            when (result) {
                is LoginResult.Success -> {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    navNext("main")
                }

                is LoginResult.Failure -> {
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(8.dp)
    ) {
        val (screen, btnLogin, btnRegister) = createRefs()

        ConstraintLayout(
            Modifier
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    bottom.linkTo(btnRegister.top, margin = 8.dp)
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                NormalTextComponent(value = "lorem ipsum")
                HeaderTextComponent(value = "LOGIN @.@")
                Spacer(modifier = Modifier.height(24.dp))
                val email by viewModel.emailFieldState
                val password by viewModel.passwordFieldState
                GymEmailFieldComponent(
                    label = "Email",
                    icon = Icons.Default.AccountCircle,
                    contentDescription = "any",
                    value = email,
                    onValueChange = { viewModel.onEmailFieldChanged(it) }
                )
                GymPasswordTextFieldComponent(
                    label = "Password",
                    value = password,
                    onValueChange = { viewModel.onPasswordFieldChanged(it) }
                )
            }
        }
        ButtonComponent(
            value = stringResource(id = StringApp.singUP),
            onButtonClicked = {
                navNext("singup")
            },
            isEnabled = true,
            modifier = Modifier
                .constrainAs(btnRegister) {
                    bottom.linkTo(parent.bottom, margin = 24.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )

        ButtonComponent(
            value = stringResource(id = StringApp.login),
            onButtonClicked = {
                viewModel.login()
            },
            isEnabled = true,
            modifier = Modifier
                .constrainAs(btnLogin) {
                    bottom.linkTo(btnRegister.top, margin = 8.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )
    }
}
