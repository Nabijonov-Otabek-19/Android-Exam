package uz.gita.androidexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.androidexam.domain.repository.AppRepository
import uz.gita.androidexam.domain.repository.AuthRepository
import uz.gita.androidexam.domain.repository.impl.AppRepositoryImpl
import uz.gita.androidexam.domain.repository.impl.AuthRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @[Binds Singleton]
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}