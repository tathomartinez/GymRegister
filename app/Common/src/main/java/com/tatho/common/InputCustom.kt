package com.tatho.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.VanishText
import com.tatho.common.theme.fontRegularApp
import com.tatho.common.theme.whiteApp

@Composable
fun PasswordInputCustom(
    value: String, modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    InputCustom(
        modifier = modifier,
        value = value,
        placeholder = "Password",
        type = KeyboardType.Password,
        trailingIcon = { TrailingIconPassword(passwordVisible) },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        onValueChange = onValueChange
    )
}

@Composable
private fun TrailingIconPassword(passwordVisible: MutableState<Boolean>) {
    val iconImage =
        if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

    val description =
        if (passwordVisible.value) stringResource(id = R.string.hidePasswoerd) else stringResource(
            id = R.string.showPasswoerd
        )
    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
        Icon(
            imageVector = iconImage,
            contentDescription = description
        )
    }
}

@Composable
fun EmailInputCustom(
    value: String, modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    InputCustom(
        modifier = modifier,
        value = value,
        placeholder = "Email",
        type = KeyboardType.Email,
        onValueChange = onValueChange
    )
}

@Composable
private fun InputCustom(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    type: KeyboardType = KeyboardType.Number,
    trailingIcon: @Composable () -> Unit = @Composable {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
//        label = {
//            PlaceHolderTextCustom(placeholder)
//        },
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.body1.copy(fontFamily = FontFamily(Font(fontRegularApp))),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BASECOLOR, // Color del borde al enfocar
            unfocusedBorderColor = BASECOLOR, // Color del borde al no estar enfocado
            focusedLabelColor = BASECOLOR, // Color del label al enfocar
            unfocusedLabelColor = BASECOLOR, // Color del label al no estar enfocado
            cursorColor = BASECOLOR, // Color del cursor,
            backgroundColor = whiteApp

        ),
        placeholder = { Text(text = placeholder, color = VanishText) },
        keyboardOptions = KeyboardOptions(
            keyboardType = type
        ),
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
    )

}

@Composable
fun PlaceHolderTextCustom(placeholder: String) {
    Box(
    ) {
        Text(text = placeholder)
    }
}
