package uz.gita.androidexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.gita.androidexam.navigation.NavigationHandler
import uz.gita.androidexam.presentation.screen.home.HomeScreen
import uz.gita.androidexam.presentation.screen.onboarding.OnBoardingScreen
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.utils.Constants
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidExamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val screen = if (Constants.user != null) HomeScreen() else OnBoardingScreen()

                    Navigator(screen = screen) { navigator ->
                        LaunchedEffect(key1 = navigator) {
                            navigationHandler.navigationBuffer.onEach {
                                it(navigator)
                            }.collect()
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}