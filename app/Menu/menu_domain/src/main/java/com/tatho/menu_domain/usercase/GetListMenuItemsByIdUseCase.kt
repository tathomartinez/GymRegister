package com.tatho.menu_domain.usercase

import android.util.Log
import com.tatho.common.Resource
import com.tatho.menu_domain.entities.MenuItem
import com.tatho.menu_domain.repository.IMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetListMenuItemsByIdUseCase(
    private val repository: IMenuRepository
) {
    operator fun invoke(id: Long): Flow<Resource<List<MenuItem>>> = flow {
        emit(Resource.Loading())
        try {
            val menuItems = repository.getListMenuItemsById(id)
            Log.e("menuItems", menuItems.toString())
            emit(Resource.Success(menuItems))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message ?: "Error"))
        }
    }
}