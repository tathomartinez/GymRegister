package com.tatho.login_presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.*
import com.tatho.common.theme.BackGroundColor
import com.tatho.common.theme.StringApp

@Composable
fun LoginScreen(navNext: (String) -> Unit, viewModel: LoginViewModel) {

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
