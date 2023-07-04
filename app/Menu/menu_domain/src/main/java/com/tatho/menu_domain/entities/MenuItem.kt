package com.tatho.menu_domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subtitle") val subtitle: String,
    @ColumnInfo(name = "role") val role: String? = "0"
)
