package uz.gita.androidexam.presentation.screen.home.page.shopping

import uz.gita.androidexam.navigation.AppNavigator
import uz.gita.androidexam.presentation.screen.addProduct.AddProductScreen
import javax.inject.Inject

class ShoppingPageDirection @Inject constructor(
    private val appNavigator: AppNavigator
) : ShoppingPageContract.Direction {

    override suspend fun navigateToAddProductScreen() {
        appNavigator.navigateTo(AddProductScreen())
    }
}