package com.tatho.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tatho.common.R

private val lightColorPalette = lightColors(
    primary = BASECOLOR,
    primaryVariant = Color.Blue,
    secondary = WHITEAPP,
    background = WHITEAPP,
    surface = WHITEAPP,
)

@Composable
fun GymTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
//        DarkColorPalette
        lightColorPalette
    } else {
        lightColorPalette
    }

    val customFontFamily = FontFamily(
        Font(R.font.montserrat_black),
        Font(R.font.montserrat_blackitalic, FontWeight.Black, FontStyle.Italic),
        Font(R.font.montserrat_bold, FontWeight.Bold),
        Font(R.font.montserrat_bolditalic, FontWeight.Bold, FontStyle.Italic),
        Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
        Font(R.font.montserrat_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
        Font(R.font.montserrat_extralight, FontWeight.ExtraLight),
        Font(R.font.montserrat_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
        Font(R.font.montserrat_italic),
        Font(R.font.montserrat_light),
        Font(R.font.montserrat_lightitalic),
        Font(R.font.montserrat_medium, FontWeight.Medium),
        Font(R.font.montserrat_mediumitalic, FontWeight.Medium, FontStyle.Italic),
        Font(R.font.montserrat_regular),
        Font(R.font.montserrat_semibold, FontWeight.SemiBold),
        Font(R.font.montserrat_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
        Font(R.font.montserrat_thin, FontWeight.Thin),
        Font(R.font.montserrat_thinitalic, FontWeight.Thin, FontStyle.Italic)
    )

    val typography = Typography(
        defaultFontFamily = customFontFamily, // Establece la fuente personalizada por defecto
        h1 = TextStyle(
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        ),
        h2 = TextStyle(
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        body1 = TextStyle(
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),

        // Define más estilos de fuente personalizada para otros niveles de encabezado y cuerpo si es necesario
    )
    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}

@Preview
@Composable
fun DefaultPreview() {
    GymTheme {
        Text(text = "Hello World!")
    }
}