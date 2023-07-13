package com.tatho.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tatho.common.theme.BackGroundLightColor
import com.tatho.common.theme.PrimaryColor
import com.tatho.common.theme.fontApp
import com.tatho.menu_presentation.R
import com.tatho.presentation.exception.MenuParentException

@Composable
fun ItemMenu(title: String, subtitle: String, directionIcon: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,

        ) {
        ItemMenuContent(title, subtitle, directionIcon)
    }
}

@Composable
fun ItemMenuContent(title: String, subtitle: String, directionIcon: String) {
    Row(
        Modifier
            .offset(x = 8.dp, y = 8.dp)
            .width(296.dp)
            .height(68.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemMenuIcon(directionIcon)
        ItemMenuText(title, subtitle)
    }
}

@Composable
fun ItemMenuIcon(directionIcon: String ) {
    Image(
        modifier = Modifier
            .background(color = Color(0xFF00C4B4), shape = RoundedCornerShape(size = 4.dp)),
        painter = painterResource(id = getResId("ic_${directionIcon}", R.drawable::class.java)),
        contentDescription = "Icono menu",
    )
}

private fun getResId(
    resourceName: String,
    cls: Class<*>,
): Int {
    return try {
        val field = cls.getDeclaredField(resourceName)
        field.getInt(null)
    } catch (e: Exception) {
        0
    }
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

@Preview
@Composable
fun MenuScreenPreview2() {
    TextTitleMenu({})
}

@Composable
fun TextTitleMenu(back: () -> Unit) {
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
            Icon(imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "icon back",
                tint = PrimaryColor,
                modifier = Modifier.clickable { back() })
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
        }
    }
}

@Composable
fun MenuScreen(navegateTo: (String) -> Unit, viewModel: MenuViewModel) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(BackGroundLightColor)
    ) {
        val menuItemsState by viewModel.menuItems.collectAsState()


        val (snackbarVisibleState) = remember { mutableStateOf(false) }
        if (snackbarVisibleState) {
            Snackbar(

                action = {
                    Button(onClick = {}) {
                        Text("MyAction")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = "This is a snackbar!") }
        }
        TextTitleMenu {
            try {
                viewModel.getLastListMenu()
            } catch (e: MenuParentException) {
                navegateTo("register")
            }
        }
        menuItemsState.data?.let { data ->
            LazyColumn {
                items(data) { menuItem ->
                    ItemMenu(
                        menuItem.title,
                        menuItem.subtitle ?: "",
                        menuItem.iconRoute ?: "arrows",
                        onClick = {
                            if (menuItem.subtitle.isNullOrEmpty()) {
                                navegateTo(menuItem.route!!)
                                return@ItemMenu
                            }
                            viewModel.getChildrenByParentId(menuItem.id)
                        })
                }
            }
        }

//        if (snackbarVisibleState) {
//            Snackbar(
//                action = {
//                    Button(onClick = {}) {
//                        Text("MyAction")
//                    }
//                },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .constrainAs(btnRegister) {
//                        end.linkTo(parent.end)
//                    }
//            ) { Text(text = "This is a snackbar!") }
//        }
    }
}