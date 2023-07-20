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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.BackGroundLightColor
import com.tatho.common.theme.WHITEAPP
import com.tatho.presentation.components.BodyMenu
import com.tatho.presentation.components.Header
import com.tatho.presentation.components.ItemMenu
import com.tatho.presentation.components.TemperatureText
import com.tatho.presentation.components.TextTitleMenu
import com.tatho.presentation.exception.MenuParentException

@Composable
fun MenuScreen(navegateTo: (String) -> Unit, viewModel: MenuViewModel) {
    val menuItemsState by viewModel.menuItems.collectAsState()
    ConstraintLayout(
        modifier = Modifier
            .background(WHITEAPP)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (header, temperatureAnchor, body, back, logout) = createRefs()
        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top, 48.dp)
            centerHorizontallyTo(parent)
        })
        TemperatureText(modifier = Modifier.constrainAs(temperatureAnchor) {
            top.linkTo(header.bottom, 8.dp)
            centerHorizontallyTo(parent)
        })
        menuItemsState.data?.let { data ->
            BodyMenu(
                modifier = Modifier.constrainAs(body) {
                    top.linkTo(temperatureAnchor.bottom, 32.dp)
                    centerHorizontallyTo(parent)
                }, data = data, onClick = { it ->
                    if (it.subtitle.isNullOrEmpty()) {
                        navegateTo(it.route!!)
                        return@BodyMenu
                    }
                    viewModel.getChildrenByParentId(it.id)
                }
            )
        }
        Button(onClick = {
             navegateTo("main")
        }, modifier = Modifier.constrainAs(back) {
            top.linkTo(body.bottom, 32.dp)
            centerHorizontallyTo(parent)
        }) {
            Text(text = "Back")
        }
        Button(onClick = {
            viewModel.logOut { navegateTo("login") }
        }, modifier = Modifier.constrainAs(logout) {
            top.linkTo(back.bottom, 32.dp)
            centerHorizontallyTo(parent)
        }) {
            Text(text = "Logout")
        }

    }
}

@Composable
fun MenuScreenOld(navegateTo: (String) -> Unit, viewModel: MenuViewModel) {
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
                viewModel.logOut { navegateTo("login") }
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