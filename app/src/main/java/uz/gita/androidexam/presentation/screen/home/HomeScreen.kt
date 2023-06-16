package uz.gita.androidexam.presentation.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.ui.theme.AndroidExamTheme

class HomeScreen : AppScreen() {
    @Composable
    override fun Content() {
        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                ShopScreenContent()
            }
        }
    }
}

@Composable
fun ShopScreenContent() {
    Box {
        Text(text = "Home", modifier = Modifier.align(Alignment.Center), fontSize = 24.sp)
    }
}

@Composable
@Preview(showSystemUi = true)
fun ShopScreenPreview() {
    ShopScreenContent()
}