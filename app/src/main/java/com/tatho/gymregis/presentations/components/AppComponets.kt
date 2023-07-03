package com.tatho.gymregis.presentations.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.tatho.gymregis.R
import com.tatho.gymregis.ui.theme.*

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
    val navController = rememberNavController()
    SingUpScreen {
        _navController()
    }
}

fun _navController() {
}

@Composable
fun SingUpScreen(navNext: () -> Unit) {

    ConstraintLayout(
        modifier =
        Modifier
//            .padding(28.dp)
            .background(BackGroundColor)
    ) {

        val (screen, btnRegister) = createRefs()

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
//                .background(BackGroundColor)
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
                GymEmailFieldComponent(
                    label = stringResource(id = R.string.email), icon =
                    Icons.Default.AccountCircle,
                    contentDescription = "any"
                )
                GymPasswordTextFieldComponent(
                    label = stringResource(id = R.string.password)
                )
                GymPasswordTextFieldComponent(
                    label = stringResource(id = R.string.repetPassword)
                )
                CheckboxComponent("Hola", onTextSelected = {
                    //TODO evento del viewmodel
                }, onCheckedChange = {
                    //TODO evento del viewmodel
                    //TODO Cuando este cambie debe pasarse al componente button para activar o desactivar el boton
                })
                Spacer(modifier = Modifier.height(48.dp))

            }


        }

        ButtonComponent(
            value = stringResource(id = R.string.singUp),
            onButtonClicked = {
                navNext()
            },
            isEnabled = true,
            modifier = Modifier
//                .fillMaxWidth()
                .constrainAs(btnRegister) {
//                    top.linkTo(screen.bottom)
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    bottom.linkTo(parent.bottom, margin = 24.dp)

                }
        )
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
fun GymEmailFieldComponent(label: String, icon: ImageVector, contentDescription: String) {
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
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
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
fun GymPasswordTextFieldComponent(label: String) {
    val password = remember { mutableStateOf("") }
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
        value = password.value,
        onValueChange = {
            password.value = it
        },
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
