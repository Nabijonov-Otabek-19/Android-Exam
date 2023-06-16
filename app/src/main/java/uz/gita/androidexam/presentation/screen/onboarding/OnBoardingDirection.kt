package uz.gita.androidexam.presentation.screen.onboarding

import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.presentation.screen.accountScreen.AccountScreen
import javax.inject.Inject

class OnBoardingDirection @Inject constructor(
    private val navigator: AppNavigator
) : OnBoardingContract.Direction {

    override suspend fun navigateToAccountScreen() {
        navigator.navigateTo(AccountScreen())
    }
}