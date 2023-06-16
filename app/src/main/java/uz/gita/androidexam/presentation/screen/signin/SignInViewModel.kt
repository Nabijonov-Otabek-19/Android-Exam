package uz.gita.androidexam.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val direction: SignInDirection
) : ViewModel(), SignInContract.ViewModel {

    override val container =
        container<SignInContract.UIState, SignInContract.SideEffect>(SignInContract.UIState.InitState)

    override fun onEventDispatcher(intent: SignInContract.Intent) {
        when (intent) {
            SignInContract.Intent.OpenShopScreen -> {
                viewModelScope.launch {
                    direction.navigateToShopScreen()
                }
            }

            SignInContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}