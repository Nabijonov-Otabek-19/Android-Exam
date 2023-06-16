package uz.gita.androidexam.presentation.screen.home.page.shopping

import org.orbitmvi.orbit.ContainerHost
import uz.gita.androidexam.data.common.Products

interface ShoppingPageContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object Loading : UIState
        data class PrepareData(val productsData: List<Products>) : UIState
    }

    sealed interface SideEffect {
        data class HasError(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class LoadData(val userId: String) : Intent
        object OpenAddProductScreen : Intent
    }

    interface Direction {
        suspend fun navigateToAddProductScreen()
    }
}