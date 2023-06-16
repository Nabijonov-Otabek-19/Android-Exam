package uz.gita.androidexam.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val direction: OnBoardingDirection
) :
    OnBoardingContract.ViewModel, ViewModel() {

    override val container =
        container<OnBoardingContract.UIState, OnBoardingContract.SideEffect>(OnBoardingContract.UIState.InitState)

    override fun onEventDispatcher(intent: OnBoardingContract.Intent) {
        when (intent) {
            OnBoardingContract.Intent.OpenAccountScreen -> {
                viewModelScope.launch {
                    direction.navigateToAccountScreen()
                }
            }
        }
    }
}