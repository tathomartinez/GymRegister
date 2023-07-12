package com.tatho.menu_data.repository

import com.tatho.menu_data.room.MenuItemDao
import com.tatho.menu_domain.entities.MenuItem
import com.tatho.menu_domain.repository.IMenuRepository

class MenuRepositoryImpl(
    private val menuItemDao: MenuItemDao
) : IMenuRepository {

    override suspend fun getListMenuItemsByRolWithoutParent(role: String): List<MenuItem> {
        return menuItemDao.getItemsByRoleWithoutParent(role)
    }

    override suspend fun getListMenuItemsById(id: Long): List<MenuItem> {
        return menuItemDao.getListMenuItemsById(id)
    }

    override suspend fun getChildrenListMenuItemsByParentId(id: Long): List<MenuItem> {
        return menuItemDao.getChildrenByParentId(id)
    }
}