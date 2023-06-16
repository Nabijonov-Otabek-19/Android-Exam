package uz.gita.androidexam.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.androidexam.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val direction: SignInDirection,
    private val authRepository: AuthRepository
) : ViewModel(), SignInContract.ViewModel {

    override val container =
        container<SignInContract.UIState, SignInContract.SideEffect>(SignInContract.UIState.InitState)

    override fun onEventDispatcher(intent: SignInContract.Intent) {
        when (intent) {
            is SignInContract.Intent.LogInUser -> {
                authRepository.signIn(intent.email, intent.password).onEach { result ->
                    result.onSuccess {
                        direction.navigateToHomeScreen()
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                SignInContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }

            SignInContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}