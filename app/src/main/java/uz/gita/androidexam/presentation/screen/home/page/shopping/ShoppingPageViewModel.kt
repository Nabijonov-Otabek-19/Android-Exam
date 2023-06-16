package uz.gita.androidexam.presentation.screen.home.page.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.androidexam.domain.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class ShoppingPageViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val direction: ShoppingPageDirection
) : ViewModel(), ShoppingPageContract.ViewModel {

    override val container =
        container<ShoppingPageContract.UIState, ShoppingPageContract.SideEffect>(
            ShoppingPageContract.UIState.Loading
        )

    override fun onEventDispatcher(intent: ShoppingPageContract.Intent) {
        when (intent) {
            is ShoppingPageContract.Intent.LoadData -> {
                appRepository.fetchProductbyUserId(intent.userId).onEach { result ->
                    intent { reduce { ShoppingPageContract.UIState.Loading } }
                    result.onSuccess {
                        intent { reduce { ShoppingPageContract.UIState.PrepareData(it) } }
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                ShoppingPageContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }

            ShoppingPageContract.Intent.OpenAddProductScreen -> {
                viewModelScope.launch {
                    direction.navigateToAddProductScreen()
                }
            }
        }
    }
}