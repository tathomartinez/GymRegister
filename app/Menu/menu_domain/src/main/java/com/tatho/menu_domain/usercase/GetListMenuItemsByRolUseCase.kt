package com.tatho.menu_domain.usercase

import android.util.Log
import com.tatho.common.Resource
import com.tatho.menu_domain.entities.MenuItem
import com.tatho.menu_domain.repository.IMenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetListMenuItemsByRolUseCase(
    private val repository: IMenuRepository
) {
    operator fun invoke(role: String):Flow<Resource<List<MenuItem>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getListMenuItemsByRol(role)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.message ?: "Error"))
        }
    }

}