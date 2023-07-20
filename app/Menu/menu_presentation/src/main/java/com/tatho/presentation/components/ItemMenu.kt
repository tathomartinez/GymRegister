package com.tatho.presentation.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tatho.common.theme.BASECOLOR
import com.tatho.common.theme.LIGTHGRAYAPP
import com.tatho.common.theme.WHITEAPP

//@Preview
//@Composable
//fun ItemMenuPreview() {
//    ItemMenu(title = "title", subtitle = "subtitle", directionIcon = "directionIcon"){}
//}


@Composable
fun ItemMenu(title: String, subtitle: String, directionIcon: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,

        ) {
        ItemMenuContent(title, subtitle, directionIcon)
    }
}
@Preview
@Composable
fun ItemMenuPreview() {
    ItemMenuNew(tittle = "Orochimaru", onClick = {})
}
@Composable
fun ItemMenuNew(tittle: String, onClick: () -> Unit) {

    val now = false
    val colorBg = if (now) BASECOLOR else WHITEAPP
    val colorText = if (now) WHITEAPP else LIGTHGRAYAPP

    Card(
        modifier = Modifier.padding(8.dp).clickable { onClick() },
        backgroundColor = colorBg,
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (icon, subtitle, directionIcon) = createRefs()
            Icon(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Logout Icon",
                tint = BASECOLOR,
            )
            Text(
                modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end, 8.dp)
                    centerVerticallyTo(parent)
                },
                text = tittle,
                style = TextStyle(color = BASECOLOR, fontSize = 24.sp)
            )

        }
    }

//    ItemMenu(title = "title", subtitle = "subtitle", directionIcon = "directionIcon"){}
}
