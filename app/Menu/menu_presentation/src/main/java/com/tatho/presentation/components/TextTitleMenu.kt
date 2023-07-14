package com.tatho.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatho.common.theme.PrimaryColor
import com.tatho.common.theme.fontApp
import com.tatho.logout_presentation.LogoutIcon

@Composable
fun TextTitleMenu(back: () -> Unit, logOut: ()-> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .offset(x = 0.dp, y = 31.dp)
            .width(360.dp)
            .height(146.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .offset(x = 16.dp, y = 0.dp)
                .width(48.dp)
                .height(48.dp)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "icon back",
                tint = PrimaryColor,
                modifier = Modifier.clickable { back() }
            )
        }
        Text(
            text = "Ajustes Tecnico",
            modifier = Modifier
                .offset(x = 76.dp, y = 10.dp)
                .width(176.dp)
                .height(30.dp),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily(Font(fontApp)),
                fontWeight = FontWeight(700),
                color = PrimaryColor,
                letterSpacing = 0.25.sp,
            )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.End),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "icon back",
                tint = PrimaryColor,
                modifier = Modifier
                    .offset(x = 296.dp, y = 16.dp)
                    .width(16.dp)
                    .height(16.dp)
            )
            LogoutIcon(onLogoutClick = { logOut() })
        }
    }
}

