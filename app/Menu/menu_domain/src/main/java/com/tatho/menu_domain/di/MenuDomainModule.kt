package com.tatho.menu_domain.di

import com.tatho.menu_domain.repository.IMenuRepository
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
}