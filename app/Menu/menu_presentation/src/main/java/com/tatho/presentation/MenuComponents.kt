package com.tatho.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tatho.common.theme.BackGroundLightColor
import com.tatho.presentation.components.ItemMenu
import com.tatho.presentation.components.TextTitleMenu
import com.tatho.presentation.exception.MenuParentException


@Composable
fun MenuScreen(navegateTo: (String) -> Unit, viewModel: MenuViewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(BackGroundLightColor)
            .fillMaxSize() // Ocupa todo el espacio disponible
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

        TextTitleMenu(back = {
            try {
                viewModel.getLastListMenu()
            } catch (e: MenuParentException) {
                //navegateTo("register")
            }
        },
            logOut = {
            viewModel.logOut{navegateTo("login")}
        })

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
    }
}