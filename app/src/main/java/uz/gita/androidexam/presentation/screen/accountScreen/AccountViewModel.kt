package uz.gita.androidexam.presentation.screen.accountScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val direction: AccountDirection
) : AccountContract.ViewModel, ViewModel() {

    override val container =
        container<AccountContract.UIState, AccountContract.SideEffect>(AccountContract.UIState.InitState)

    override fun onEventDispatcher(intent: AccountContract.Intent) {
        when (intent) {
            AccountContract.Intent.OpenSignInScreen -> {
                viewModelScope.launch {
                    direction.navigateToSignInScreen()
                }
            }

            AccountContract.Intent.OpenSignUpScreen -> {
                viewModelScope.launch {
                    direction.navigateToSignUpScreen()
                }
            }

            AccountContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}