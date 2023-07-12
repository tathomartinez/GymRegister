package com.tatho.menu_domain.repository

import com.tatho.menu_domain.entities.MenuItem

interface IMenuRepository {
    suspend fun getListMenuItemsByRolWithoutParent(role: String): List<MenuItem>
    suspend fun getListMenuItemsById(id: Long): List<MenuItem>?
    suspend fun getChildrenListMenuItemsByParentId(id: Long): List<MenuItem>
}