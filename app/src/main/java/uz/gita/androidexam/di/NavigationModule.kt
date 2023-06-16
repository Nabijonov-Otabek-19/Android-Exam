package uz.gita.androidexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.navigation.NavigationDispatcher
import uz.gita.androidexam.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler
}