package com.tatho.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.common.Resource
import com.tatho.menu_domain.usercase.GetListMenuItemsByRolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val getListMenuItemsByRolUseCase: GetListMenuItemsByRolUseCase) :
    ViewModel() {

    private val _menuItems = MutableStateFlow(MenuState())
    val menuItems: StateFlow<MenuState> = _menuItems

    init {
        getListMenuItemsByRol("0")
        Log.e("llego", "llego por aca")
    }

    private fun getListMenuItemsByRol(rol: String) {
        getListMenuItemsByRolUseCase(rol).onEach {
            when (it) {
                is Resource.Loading -> {
                    _menuItems.value = MenuState(isLoading = true)
                }
                is Resource.Error -> {

                }
                is Resource.Success -> {
                    Log.e("llego", "llego aca")
                    _menuItems.value = MenuState(data = it.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}