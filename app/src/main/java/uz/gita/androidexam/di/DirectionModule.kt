package uz.gita.androidexam.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.androidexam.presentation.screen.accountScreen.AccountContract
import uz.gita.androidexam.presentation.screen.accountScreen.AccountDirection
import uz.gita.androidexam.presentation.screen.home.HomeContract
import uz.gita.androidexam.presentation.screen.home.HomeDirection
import uz.gita.androidexam.presentation.screen.home.page.shopping.ShoppingPageContract
import uz.gita.androidexam.presentation.screen.home.page.shopping.ShoppingPageDirection
import uz.gita.androidexam.presentation.screen.onboarding.OnBoardingContract
import uz.gita.androidexam.presentation.screen.onboarding.OnBoardingDirection
import uz.gita.androidexam.presentation.screen.signin.SignInContract
import uz.gita.androidexam.presentation.screen.signin.SignInDirection
import uz.gita.androidexam.presentation.screen.signup.SignUpContract
import uz.gita.androidexam.presentation.screen.signup.SignUpDirection
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindOnBoardingScreenDirection(impl: OnBoardingDirection): OnBoardingContract.Direction

    @[Binds Singleton]
    fun bindAccountScreenDirection(impl: AccountDirection): AccountContract.Direction

    @[Binds Singleton]
    fun bindSignUpScreenDirection(impl: SignUpDirection): SignUpContract.Direction

    @[Binds Singleton]
    fun bindSignInScreenDirection(impl: SignInDirection): SignInContract.Direction

    @[Binds Singleton]
    fun bindHomeScreenDirection(impl: HomeDirection): HomeContract.Direction

    @[Binds Singleton]
    fun bindShoppingPageDirection(impl: ShoppingPageDirection): ShoppingPageContract.Direction
}