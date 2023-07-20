package com.tatho.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.BASECOLOR
import com.tatho.menu_domain.entities.MenuItem

@Composable
fun BodyMenu(modifier: Modifier, data: List<MenuItem>, onClick: (MenuItem) -> Unit) {
    ConstraintLayout(modifier = modifier) {
        LazyColumn {
            items(data) {
                ItemMenuNew(tittle = it.title,
                    onClick = {
                        onClick(it)
                    })
            }
        }
    }
}

@Composable
fun TemperatureText(modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val temp = 35
        val textTemp = createRef()
        Text(
            text = "$temp °c", style = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = BASECOLOR
            ), modifier = Modifier.constrainAs(textTemp) {
                centerHorizontallyTo(parent)
            }
        )
    }
}

@Composable
fun Header(modifier: Modifier) {
    ConstraintLayout(modifier = modifier) {
        val (holatextAnchor, nameAnchor, submenuAnchor, linea) = createRefs()
        val name = "Tatho"
        Text(
            text = "Hola",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = BASECOLOR,
                fontSize = 32.sp
            ),
            modifier = Modifier.constrainAs(holatextAnchor) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                centerHorizontallyTo(parent)
            })
        Text(text = "$name,",
            style = TextStyle(
                textAlign = TextAlign.Center,
                color = BASECOLOR,
                fontSize = 40.sp
            ),
            modifier = Modifier.constrainAs(nameAnchor) {
                top.linkTo(holatextAnchor.bottom)
                start.linkTo(parent.start)
                centerHorizontallyTo(holatextAnchor)
            })
        Text(text = "Que quieres hacer?",
            modifier = Modifier.constrainAs(submenuAnchor) {
                top.linkTo(nameAnchor.bottom)
                start.linkTo(parent.start)
                centerHorizontallyTo(parent)
            })
        Box(
            modifier = Modifier
                .width(100.dp)
                .constrainAs(linea) {
                    top.linkTo(submenuAnchor.bottom, 4.dp)
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(submenuAnchor)
                }
        ) {
            Divider(color = BASECOLOR, thickness = 1.dp)
        }

    }
}
