package uz.gita.androidexam.presentation.screen.signin

import org.orbitmvi.orbit.ContainerHost

interface SignInContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object OpenShopScreen : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun navigateToShopScreen()
        suspend fun back()
    }
}
