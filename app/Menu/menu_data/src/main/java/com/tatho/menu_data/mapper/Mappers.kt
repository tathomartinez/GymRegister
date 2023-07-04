package com.tatho.menu_data.mapper

import com.tatho.menu_data.model.MenuItemDto
import com.tatho.menu_domain.entities.MenuItem

fun MenuItemDto.toEntity() = MenuItem(
    title = title,
    subtitle = subtitle
)

fun MenuItem.toDto() = MenuItemDto(
    title = title,
    subtitle = subtitle
)