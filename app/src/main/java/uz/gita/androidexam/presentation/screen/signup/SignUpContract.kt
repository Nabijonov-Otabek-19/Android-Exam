package uz.gita.androidexam.presentation.screen.signup

import org.orbitmvi.orbit.ContainerHost

interface SignUpContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {
        data class HasError(val message: String): SideEffect
    }

    sealed interface Intent {
        data class CreateUser(val email: String, val password: String): Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun navigateToHomeScreen()
        suspend fun back()
    }
}