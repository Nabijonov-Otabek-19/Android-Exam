package uz.gita.androidexam.presentation.screen.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.androidexam.R
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray

class OnBoardingScreen : AppScreen() {
    @Composable
    override fun Content() {

        val viewModel: OnBoardingContract.ViewModel = getViewModel<OnBoardingViewModel>()
        val uiState = viewModel.collectAsState()

        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                OnBoardingContent(uiState, viewModel::onEventDispatcher)
            }
        }
    }
}

@Composable
private fun OnBoardingContent(
    uiState: State<OnBoardingContract.UIState>,
    onEventDispatcher: (OnBoardingContract.Intent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter)
        ) {

            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .size(280.dp),
                painter = painterResource(id = R.drawable.ic_shop),
                contentDescription = null
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 28.sp,
                color = Color.Black,
                text = stringResource(id = R.string.title),
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 18.sp,
                color = Color.Gray,
                text = stringResource(id = R.string.title2),
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier
                    .padding(vertical = 34.dp)
                    .width(250.dp)
                    .height(65.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Gray),
                onClick = {
                    onEventDispatcher.invoke(OnBoardingContract.Intent.OpenAccountScreen)
                }) {
                Text(text = "Start", fontSize = 22.sp)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showSystemUi = true)
fun ScreenPreview() {
    OnBoardingContent(mutableStateOf(OnBoardingContract.UIState.InitState)) {}
}