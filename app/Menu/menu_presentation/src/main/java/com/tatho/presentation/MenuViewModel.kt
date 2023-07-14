package com.tatho.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatho.common.Resource
import com.tatho.menu_domain.entities.MenuItem
import com.tatho.menu_domain.usercase.GetChildrenListMenuItemsByParentIdUseCase
import com.tatho.menu_domain.usercase.GetListMenuItemsByIdUseCase
import com.tatho.menu_domain.usercase.GetListMenuItemsByRolUseCase
import com.tatho.presentation.exception.MenuParentException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getListMenuItemsByRolUseCase: GetListMenuItemsByRolUseCase,
    private val getListMenuItemsByIdUseCase: GetListMenuItemsByIdUseCase,
    private val getChildrenListMenuItemsByParentIdUseCase: GetChildrenListMenuItemsByParentIdUseCase
) :
    ViewModel() {

    private val _menuItems = MutableStateFlow(MenuState())
    val menuItems: StateFlow<MenuState> = _menuItems

    private var parent: Long? = null

    init {
        getListMenuItemsByRol("0")
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
                    _menuItems.value = MenuState(data = it.data)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getListMenuItemsByMenu(menuItem: MenuItem) {
        getListMenuItemsByIdUseCase(menuItem.id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _menuItems.value = MenuState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.e("llego", "llego aca algun error ${it.message}")
                }
                is Resource.Success -> {
                    _menuItems.value = MenuState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getChildrenByParentId(id: Long) {
        parent = id
        getChildrenListMenuItemsByParentIdUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    _menuItems.value = MenuState(isLoading = true)
                }
                is Resource.Error -> {
                    Log.e("llego", "llego aca algun error ${it.message}")
                }
                is Resource.Success -> {
                    _menuItems.value = MenuState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLastListMenu() {
        parent?.let { parent ->
            getListMenuItemsByIdUseCase(parent).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _menuItems.value = MenuState(isLoading = true)
                    }
                    is Resource.Error -> {
                        Log.e("llego", "llego aca algun error ${it.message}")
                    }
                    is Resource.Success -> {
                        _menuItems.value = MenuState(data = it.data)
                        if (_menuItems.value.data?.get(0)?.parentMenuId == null){
                            this.parent = null
                        }
                    }
                }
            }.launchIn(viewModelScope)

        } ?: throw MenuParentException("Parent is null")
    }
}