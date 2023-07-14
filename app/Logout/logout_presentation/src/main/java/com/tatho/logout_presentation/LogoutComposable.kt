package com.tatho.logout_presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tatho.common.theme.PrimaryColor

@Composable
fun LogoutIcon(onLogoutClick: () -> Unit) {
    Icon(
        imageVector = Icons.Default.Logout,
        contentDescription = "Logout Icon",
        tint = PrimaryColor,
        modifier = Modifier
            .size(24.dp)
            .clickable { onLogoutClick() }
    )
}
