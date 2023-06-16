package uz.gita.androidexam.presentation.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val direction: SignUpDirection
) : ViewModel(), SignUpContract.ViewModel {

    override val container =
        container<SignUpContract.UIState, SignUpContract.SideEffect>(SignUpContract.UIState.InitState)

    override fun onEventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            SignUpContract.Intent.OpenShopScreen -> {
                viewModelScope.launch {
                    direction.navigateToShopScreen()
                }
            }

            SignUpContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}