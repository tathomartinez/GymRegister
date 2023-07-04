package com.tatho.menu_data.repository

import com.tatho.menu_data.room.MenuItemDao
import com.tatho.menu_domain.repository.IMenuRepository
import com.tatho.menu_domain.entities.MenuItem

class MenuRepositoryImpl(
    private val menuItemDao: MenuItemDao
): IMenuRepository {
    override suspend fun getListMenuItemsByRol(role: String): List<MenuItem> {
//        val cursor = menuItemDao.getListMenuItemsByRol(role)
//        val menuItems = mutableListOf<MenuItem>()

//        cursor.use {
//            if (it.moveToFirst()) {
//                val idColumnIndex = it.getColumnIndexOrThrow("id")
//                val titleColumnIndex = it.getColumnIndexOrThrow("title")
//                val subtitleColumnIndex = it.getColumnIndexOrThrow("subtitle")
//
//                do {
//                    val id = it.getLong(idColumnIndex)
//                    val title = it.getString(titleColumnIndex)
//                    val subtitle = it.getString(subtitleColumnIndex)
//
//                    val menuItem = MenuItem(id, title, subtitle)
//                    menuItems.add(menuItem)
//                } while (it.moveToNext())
//            }
//        }
        return menuItemDao.getListMenuItemsByRol(role)
    }
}