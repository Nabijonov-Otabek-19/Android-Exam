package uz.gita.androidexam.presentation.screen.accountScreen

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.androidexam.R
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray

class AccountScreen : AppScreen() {
    @Composable
    override fun Content() {

        val viewModel: AccountContract.ViewModel = getViewModel<AccountViewModel>()
        val uiState = viewModel.collectAsState()

        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                AccountScreenContent(uiState, viewModel::onEventDispatcher)
            }
        }
    }
}

@Composable
fun TopBar(
    onEventDispatcher: (AccountContract.Intent) -> Unit,
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
                    onEventDispatcher.invoke(AccountContract.Intent.Back)
                }
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )

        Text(
            text = "Profile",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = Color.Black, fontSize = 18.sp
        )
    }
}

@Composable
fun AccountScreenContent(
    uiState: State<AccountContract.UIState>,
    onEventDispatcher: (AccountContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        TopBar(onEventDispatcher, modifier = Modifier.align(Alignment.TopCenter))

        Column(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )

            Button(
                modifier = Modifier
                    .padding(vertical = 34.dp)
                    .width(250.dp)
                    .height(65.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Gray),
                onClick = {
                    onEventDispatcher.invoke(AccountContract.Intent.OpenSignInScreen)
                }) {
                Text(text = "SIGN IN", fontSize = 22.sp)
            }

            Text(
                modifier = Modifier.clickable {
                    onEventDispatcher.invoke(AccountContract.Intent.OpenSignUpScreen)
                },
                text = "Create new account",
                fontSize = 20.sp
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview(showSystemUi = true)
fun AccountScreenPreview() {
    AccountScreenContent(mutableStateOf(AccountContract.UIState.InitState), {})
}