package com.tatho.menu_data.room

import androidx.room.Dao
import androidx.room.Query
import com.tatho.menu_domain.entities.MenuItem

@Dao
interface MenuItemDao {

    @Query("SELECT * FROM menu WHERE role = :role")
    suspend fun getListMenuItemsByRol(role: String): List<MenuItem>
}