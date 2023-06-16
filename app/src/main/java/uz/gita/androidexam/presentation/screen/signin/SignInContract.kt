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
        data class HasError(val message: String) : SideEffect
    }

    sealed interface Intent {
        data class LogInUser(val email: String, val password: String) : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun navigateToHomeScreen()
        suspend fun back()
    }
}
