package com.tatho.gymregis.presentations.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatho.gymregis.R
import com.tatho.gymregis.ui.theme.BackGroundColor
import com.tatho.gymregis.ui.theme.BackGroundLightColor
import com.tatho.gymregis.ui.theme.PrimaryColor
import com.tatho.gymregis.ui.theme.TextColor

@Composable
fun NormalTextComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .background(BackGroundColor),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun DefaultPreview() {
    SingUpScreen()
}

@Composable
fun SingUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackGroundColor)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackGroundColor)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.greeting))
            HeaderTextComponent(value = stringResource(id = R.string.createAccountText))
            Spacer(modifier = Modifier.height(20.dp))
            GymTextFieldComponent(
                label = stringResource(id = R.string.email), icon =
                Icons.Default.AccountCircle,
                contentDescription = "any"
            )
            GymTextFieldComponent(
                label = stringResource(id = R.string.password), icon =
                Icons.Default.Lock,
                contentDescription = "any"
            )
            GymTextFieldComponent(
                label = stringResource(id = R.string.repetPassword), icon =
                Icons.Default.Lock,
                contentDescription = "any"
            )
        }
    }
}

@Composable
fun HeaderTextComponent(value: String) {
    Text(
        text = value, modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(BackGroundColor),
        style = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = TextColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun GymTextFieldComponent(label: String, icon: ImageVector, contentDescription: String) {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        },
        modifier = Modifier
            .background(BackGroundColor)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                icon,
                tint = TextColor,
                contentDescription = contentDescription
            )
        },
    )
}

@Composable
fun GymPasswordTextFieldComponent(label: String, icon: ImageVector, contentDescription: String) {
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        label = { Text(text = label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        value = password.value,
        onValueChange = {
            password.value = it
        },
        modifier = Modifier
            .background(BackGroundColor)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                icon,
                tint = TextColor,
                contentDescription = contentDescription
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
        }
    )
}