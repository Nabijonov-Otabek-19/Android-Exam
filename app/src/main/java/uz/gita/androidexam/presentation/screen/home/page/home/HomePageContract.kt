package uz.gita.androidexam.presentation.screen.home.page.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.androidexam.data.common.Products

interface HomePageContract {
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
        object LoadData : Intent
    }

    interface Direction {

    }
}