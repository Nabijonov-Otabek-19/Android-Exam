package uz.gita.androidexam.presentation.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeDirection
) : HomeContract.ViewModel, ViewModel() {

    override val container =
        container<HomeContract.UIState, HomeContract.SideEffect>(HomeContract.UIState.InitState)

    override fun onEventDispatcher(intent: HomeContract.Intent) {

    }
}