package uz.gita.androidexam.presentation.screen.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.androidexam.R
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.presentation.screen.signin.SignInContract
import uz.gita.androidexam.ui.component.SignUpScreenComponent
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray

class SignUpScreen : AppScreen() {
    @Composable
    override fun Content() {

        val viewModel: SignUpContract.ViewModel = getViewModel<SignUpViewModel>()
        val uiState = viewModel.collectAsState()

        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                SignUpScreenContent(uiState, viewModel::onEventDispatcher)
            }
        }
    }
}

@Composable
fun TopBar(
    onEventDispatcher: (SignUpContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    onEventDispatcher.invoke(SignUpContract.Intent.Back)
                }
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(65.dp)
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = null
        )
    }
}

@Composable
fun SignUpScreenContent(
    uiState: State<SignUpContract.UIState>,
    onEventDispatcher: (SignUpContract.Intent) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box {
        TopBar(
            onEventDispatcher = onEventDispatcher,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(modifier = Modifier.align(Alignment.Center)) {
            SignUpScreenComponent(
                text = "Email",
                placeholder = "example.user@gmail.com",
                value = email,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { email = it }
            )

            SignUpScreenComponent(
                text = "Password",
                placeholder = "email password",
                value = password,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { password = it }
            )
        }

        Button(
            modifier = Modifier
                .width(350.dp)
                .height(65.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = Gray),
            onClick = {
                onEventDispatcher.invoke(SignUpContract.Intent.OpenShopScreen)
            }
        ) {
            Text(text = "CREATE ACCOUNT", fontSize = 16.sp)
        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showSystemUi = true)
fun SignUpScreenPreview() {
    SignUpScreenContent(mutableStateOf(SignUpContract.UIState.InitState), {})
}