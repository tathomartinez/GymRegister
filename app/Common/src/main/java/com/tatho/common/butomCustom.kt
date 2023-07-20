package com.tatho.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.whiteApp

@Composable
fun CreateAccount(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
) {
    CustomButton(
        modifier = modifier,
        text = "Crear cuenta",
        backgroundColor = BASECOLOR,
        textColor = whiteApp,
        onClick = onButtonClicked,
        isEnabled = isEnabled
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
) {
    CustomButton(
        modifier = modifier,
        text = "Log in",
        backgroundColor = whiteApp,
        textColor = BASECOLOR,
        onClick = onButtonClicked,
        isEnabled = isEnabled,
    )
}

@Composable
fun CustomButton(
    modifier: Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    isEnabled: Boolean = false,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}