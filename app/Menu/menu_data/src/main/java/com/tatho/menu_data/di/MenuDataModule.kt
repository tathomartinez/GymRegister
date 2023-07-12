package com.tatho.menu_data.di

import com.tatho.menu_data.repository.MenuRepositoryImpl
import com.tatho.menu_data.room.MenuItemDao
import com.tatho.menu_domain.repository.IMenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object MenuDataModule {

    @Provides
    fun provideMenuRepository(menuItemDao: MenuItemDao): IMenuRepository {
        return MenuRepositoryImpl(menuItemDao)
    }
}