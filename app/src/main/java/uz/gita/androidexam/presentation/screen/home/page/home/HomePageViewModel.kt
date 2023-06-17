package uz.gita.androidexam.presentation.screen.home.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.androidexam.domain.repository.impl.AppRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val appRepository: AppRepositoryImpl
) : HomePageContract.ViewModel, ViewModel() {

    override val container =
        container<HomePageContract.UIState, HomePageContract.SideEffect>(
            HomePageContract.UIState.Loading
        )

    override fun onEventDispatcher(intent: HomePageContract.Intent) {
        when (intent) {
            HomePageContract.Intent.LoadData -> {
                appRepository.fetchAllProducts().onEach { result ->
                    result.onSuccess {
                        intent { reduce { HomePageContract.UIState.PrepareData(it) } }
                    }
                    result.onFailure {
                        intent {
                            postSideEffect(
                                HomePageContract.SideEffect.HasError(
                                    it.message ?: "Exception occured!"
                                )
                            )
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}