package uz.gita.androidexam.presentation.screen.signin

import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.presentation.screen.home.HomeScreen
import javax.inject.Inject


class SignInDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : SignInContract.Direction {

    override suspend fun navigateToHomeScreen() {
        appNavigator.replace(HomeScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}