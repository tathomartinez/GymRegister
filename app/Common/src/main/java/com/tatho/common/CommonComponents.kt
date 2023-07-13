package com.tatho.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatho.common.theme.*

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
fun GymEmailFieldComponent(
    label: String,
    icon: ImageVector,
    contentDescription: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(text = label, color = PrimaryColor) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor,
            textColor = PrimaryColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .background(BackGroundColor)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                icon,
                tint = TextColor,
                contentDescription = contentDescription
            )
        }
    )
}

@Composable
fun GymTextFieldComponent(label: String, icon: ImageVector, contentDescription: String) {
    val textValue = remember { mutableStateOf("") }
    OutlinedTextField(
        label = { Text(text = label, color = PrimaryColor) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor,
            textColor = PrimaryColor
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
fun GymPasswordTextFieldComponent(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        label = { Text(text = label, color = PrimaryColor) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor,
            textColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .background(BackGroundColor)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Filled.Password,
                tint = TextColor,
                contentDescription = "passwordicon"
            )
        },
        trailingIcon = {
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
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}

@Composable
fun CheckboxComponent(
    value: String,
    onTextSelected: (String) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }

        //TODO encontrar si se puede cambiar el color
        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = !checkedState.value
                onCheckedChange.invoke(it)
            })

        ClickableTextComponent(value = value, onTextSelected)
    }
}

@Composable
fun ClickableTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = BackGroundLightColor)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = PrimaryColor)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(style = SpanStyle(color = BackGroundLightColor)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = PrimaryColor)) {
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->

        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if ((span.item == termsAndConditionsText) || (span.item == privacyPolicyText)) {
                    onTextSelected(span.item)
                }
            }

    })
}

@Composable
fun ButtonComponent(
    value: String,
    onButtonClicked: () -> Unit,
    isEnabled: Boolean = false,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
//        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(PrimaryColor, PrimaryAntonColor)),
//                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }

    }
}

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

@Composable
fun SizeTextFieldComponent(
    label: String, icon: ImageVector, contentDescription: String,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester? = null,
    value: String,
    onValueChange: (String) -> Unit
) {
    val currentFocus = LocalFocusManager.current
    OutlinedTextField(
        label = { Text(text = label, color = PrimaryColor) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryColor,
            cursorColor = PrimaryColor,
            backgroundColor = BackGroundLightColor,
            textColor = PrimaryColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = if (nextFocusRequester != null) ImeAction.Next else ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (nextFocusRequester != null) {
                    nextFocusRequester.requestFocus()
                } else {
                    currentFocus.clearFocus()
                }
            }
        ),
        value = value,
        onValueChange = onValueChange,
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
            Text(
                text = "cm",
                color = PrimaryColor,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        },
        textStyle = TextStyle(fontSize = 20.sp)
    )
}
