package com.tatho.sing_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
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
@Composable
fun SingUpScreen(navNext: () -> Unit, viewModel: SignUpViewModel) {
    ConstraintLayout(
        modifier = Modifier.background(BackGroundColor)
    ) {
        val (screen, btnRegister) = createRefs()

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
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
                val email by viewModel.emailFieldState
                val password by viewModel.passwordFieldState
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
                    value = password,
                    onValueChange = { viewModel.onPasswordFieldChanged(it) }
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
                Spacer(modifier = Modifier.height(48.dp))
            }
        }

        ButtonComponent(
            value = stringResource(id = R.string.singUp),
            onButtonClicked = {
                viewModel.register()
            },
            isEnabled = true,
            modifier = Modifier.constrainAs(btnRegister) {
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                bottom.linkTo(parent.bottom, margin = 24.dp)
            }
        )
    }
}