package uz.gita.androidexam.presentation.screen.addProduct

import org.orbitmvi.orbit.ContainerHost
import uz.gita.androidexam.data.common.Product

interface AddProductContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {
        data class Toast(val message: String) : SideEffect
        data class HasError(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class AddProduct(val product: Product) : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun back()
    }
}