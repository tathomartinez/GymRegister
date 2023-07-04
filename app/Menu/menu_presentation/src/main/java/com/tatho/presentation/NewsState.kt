package com.tatho.presentation

import com.tatho.menu_domain.entities.MenuItem

data class MenuState(
    val isLoading:Boolean=false,
    val error:String="",
    val data:List<MenuItem>?=null
)