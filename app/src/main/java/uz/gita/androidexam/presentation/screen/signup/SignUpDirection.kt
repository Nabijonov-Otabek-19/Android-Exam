package uz.gita.androidexam.presentation.screen.signup

import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.presentation.screen.home.HomeScreen
import javax.inject.Inject

class SignUpDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : SignUpContract.Direction {

    override suspend fun navigateToShopScreen() {
        appNavigator.navigateTo(HomeScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}