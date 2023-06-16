package uz.gita.androidexam.presentation.screen.signup

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
class SignUpViewModel @Inject constructor(
    private val direction: SignUpDirection,
    private val authRepository: AuthRepository
) : ViewModel(), SignUpContract.ViewModel {

    override val container =
        container<SignUpContract.UIState, SignUpContract.SideEffect>(SignUpContract.UIState.InitState)

    override fun onEventDispatcher(intent: SignUpContract.Intent) {
        when (intent) {
            is SignUpContract.Intent.CreateUser -> {
                authRepository.signUp(intent.email, intent.password).onEach { result ->
                    result.onSuccess {
                        direction.navigateToHomeScreen()
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                SignUpContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }

            SignUpContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}