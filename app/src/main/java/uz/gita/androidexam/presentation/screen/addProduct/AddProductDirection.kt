package uz.gita.androidexam.presentation.screen.addProduct

import uz.gita.androidexam.navigation.AppNavigator
import javax.inject.Inject

class AddProductDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : AddProductContract.Direction {

    override suspend fun back() {
        appNavigator.back()
    }
}