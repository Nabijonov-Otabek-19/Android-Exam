package uz.gita.androidexam.presentation.screen.onboarding

import org.orbitmvi.orbit.ContainerHost

interface OnBoardingContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {
        object OpenAccountScreen : Intent
    }

    interface Direction {
        suspend fun navigateToAccountScreen()
    }
}