package com.tatho.menu_data.room

import androidx.room.Dao
import androidx.room.Query
import com.tatho.menu_domain.entities.MenuItem

@Dao
interface MenuItemDao {

    @Query("SELECT * FROM menu WHERE role = :role")
    suspend fun getListMenuItemsByRol(role: String): List<MenuItem>

    @Query(
        "SELECT m1.*, GROUP_CONCAT(m2.title, ', ') AS subtitle " +
                "FROM menu AS m1 " +
                "LEFT JOIN menu AS m2 ON m1.id = m2.parent_menu_id " +
                "WHERE m1.role = :role AND m1.parent_menu_id IS NULL " +
                "GROUP BY m1.id"
    )
    suspend fun getItemsByRoleWithoutParent(role: String): List<MenuItem>

    @Query(
        "SELECT m1.*, GROUP_CONCAT(m2.title, ', ') AS subtitle " +
                "FROM menu AS m1 " +
                "LEFT JOIN menu AS m2 ON m1.id = m2.parent_menu_id " +
                "WHERE m1.id = :menuId " +
                "GROUP BY m1.id"
    )
    suspend fun getListMenuItemsById(menuId: Long): List<MenuItem>

    @Query(
        "SELECT * FROM menu WHERE parent_menu_id = :parentId"
    )
    suspend fun getChildrenByParentId(parentId: Long): List<MenuItem>

}