package uz.gita.androidexam.presentation.screen.home

import org.orbitmvi.orbit.ContainerHost

interface HomeContract {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface UIState {
        object InitState : UIState
    }

    sealed interface SideEffect {

    }

    sealed interface Intent {

    }

    interface Direction {

    }
}