package uz.gita.androidexam.presentation.screen.accountScreen

import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.presentation.screen.signin.SignInScreen
import uz.gita.androidexam.presentation.screen.signup.SignUpScreen
import javax.inject.Inject

class AccountDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : AccountContract.Direction {

    override suspend fun navigateToSignUpScreen() {
        appNavigator.navigateTo(SignUpScreen())
    }

    override suspend fun navigateToSignInScreen() {
        appNavigator.navigateTo(SignInScreen())
    }

    override suspend fun back() {
        appNavigator.back()
    }
}