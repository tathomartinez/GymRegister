package com.tatho.menu_domain.di

import com.tatho.menu_domain.repository.IMenuRepository
import com.tatho.menu_domain.usercase.GetChildrenListMenuItemsByParentIdUseCase
import com.tatho.menu_domain.usercase.GetListMenuItemsByIdUseCase
import com.tatho.menu_domain.usercase.GetListMenuItemsByRolUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MenuDomainModule {

    @Provides
    fun provideGetListMenuItemsByRolUseCase(repository: IMenuRepository): GetListMenuItemsByRolUseCase {
        return GetListMenuItemsByRolUseCase(repository)
    }

    //Create provider usercase by id
    @Provides
    fun provideGetListMenuItemsByIdUseCase(repository: IMenuRepository): GetListMenuItemsByIdUseCase {
        return GetListMenuItemsByIdUseCase(repository)
    }

    //Create provider usercase by parent
    @Provides
    fun provideGetChildrenListMenuItemsByParentIdUseCase(repository: IMenuRepository): GetChildrenListMenuItemsByParentIdUseCase {
        return GetChildrenListMenuItemsByParentIdUseCase(repository)
    }
}