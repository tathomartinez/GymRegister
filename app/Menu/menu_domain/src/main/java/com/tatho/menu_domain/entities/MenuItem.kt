package com.tatho.menu_domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subtitle") var subtitle: String? = null,
    @ColumnInfo(name = "parent_menu_id") val parentMenuId: Long? = null,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "route") val route: String? = null,
    @ColumnInfo(name = "icon_route") val iconRoute: String? = null
)
