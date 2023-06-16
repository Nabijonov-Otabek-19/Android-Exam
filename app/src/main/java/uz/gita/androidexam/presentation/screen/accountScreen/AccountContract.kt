package uz.gita.androidexam.presentation.screen.accountScreen

import org.orbitmvi.orbit.ContainerHost

interface AccountContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object OpenSignInScreen : Intent
        object OpenSignUpScreen : Intent
        object Back : Intent
    }

    interface Direction {
        suspend fun navigateToSignUpScreen()
        suspend fun navigateToSignInScreen()
        suspend fun back()
    }
}