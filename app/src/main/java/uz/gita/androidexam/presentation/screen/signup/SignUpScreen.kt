package uz.gita.androidexam.presentation.screen.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.androidexam.R
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.ui.component.MyTextFieldComponent
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray
import uz.gita.androidexam.utils.logger

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

        viewModel.collectSideEffect {
            when (it) {
                is SignUpContract.SideEffect.HasError -> {
                    logger("SignUpScreen Error = " + it.message)
                    // SnackBar yoki toast
                }
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
            MyTextFieldComponent(
                text = "Email",
                placeholder = "example.user@gmail.com",
                value = email,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { email = it }
            )

            MyTextFieldComponent(
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
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    onEventDispatcher.invoke(SignUpContract.Intent.CreateUser(email, password))
                }
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