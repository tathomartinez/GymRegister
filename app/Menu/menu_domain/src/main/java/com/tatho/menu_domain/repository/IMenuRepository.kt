package com.tatho.menu_domain.repository

import com.tatho.menu_domain.entities.MenuItem

interface IMenuRepository {
    suspend fun getListMenuItemsByRol(role: String): List<MenuItem>
}