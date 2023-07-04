package com.tatho.presentation

import com.tatho.menu_data.model.MenuItemDto
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tatho.common.theme.BackGroundLightColor


@Composable
fun ItemMenu(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White
    ) {
        ItemMenuContent(title, subtitle)
    }
}

@Composable
fun ItemMenuContent(title: String, subtitle: String) {
    Row(
        Modifier
            .offset(x = 8.dp, y = 8.dp)
            .width(296.dp)
            .height(68.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemMenuIcon()
        ItemMenuText(title, subtitle)
    }
}

@Composable
fun ItemMenuIcon() {
    Icon(
        imageVector = Icons.Default.FitnessCenter,
        contentDescription = "Icono prueba",
        modifier = Modifier
            .padding(0.dp)
            .width(20.dp)
            .height(20.dp)
    )
}

@Composable
fun ItemMenuText(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .offset(8.dp, 0.dp)
            .width(272.dp)
            .height(60.dp)
            .padding(top = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
    ) {
        ItemMenuTitle(title)
        ItemMenuSubtitle(subtitle)
    }
}

@Composable
fun ItemMenuTitle(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF2D3338),
        letterSpacing = 0.25.sp,
    )
}

@Composable
fun ItemMenuSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        fontSize = 12.sp,
        color = Color(0xFF707382),
        letterSpacing = 0.25.sp
    )
}


@Composable
fun MenuScreen(navNext: () -> Unit, itemMenuList: List<MenuItemDto>) {
    ConstraintLayout(
        modifier =
        Modifier
            .background(BackGroundLightColor)
            .fillMaxSize()
    ) {

        LazyColumn {
            items(itemMenuList) { menuItem ->
                ItemMenu(menuItem.title, menuItem.subtitle)
            }
        }
    }
}
