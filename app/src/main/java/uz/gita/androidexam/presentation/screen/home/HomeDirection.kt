package uz.gita.androidexam.presentation.screen.home

import uz.gita.androidexam.navigation.AppNavigator
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigator: AppNavigator
): HomeContract.Direction {

}