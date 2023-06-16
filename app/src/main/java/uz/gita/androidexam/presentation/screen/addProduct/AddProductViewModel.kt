package uz.gita.androidexam.presentation.screen.addProduct

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
class AddProductViewModel @Inject constructor(
    private val direction: AddProductDirection,
    private val appRepository: AppRepository
) : ViewModel(), AddProductContract.ViewModel {

    override val container =
        container<AddProductContract.UIState, AddProductContract.SideEffect>(
            AddProductContract.UIState.LoadCategories(
                arrayListOf()
            )
        )

    init {
        appRepository.fetchCategories().onEach { result ->
            result.onSuccess {
                intent { reduce { AddProductContract.UIState.LoadCategories(it) } }
            }
            result.onFailure {
                intent {
                    postSideEffect(
                        AddProductContract.SideEffect.HasError(
                            it.message ?: "Exception occured!"
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onEventDispatcher(intent: AddProductContract.Intent) {
        when (intent) {
            is AddProductContract.Intent.AddProduct -> {
                appRepository.addProduct(intent.product).onEach { result ->
                    result.onSuccess {
                        intent {
                            postSideEffect(AddProductContract.SideEffect.Toast("Product saved"))
                            direction.back()
                        }
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                AddProductContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }

            AddProductContract.Intent.Back -> {
                viewModelScope.launch {
                    direction.back()
                }
            }
        }
    }
}