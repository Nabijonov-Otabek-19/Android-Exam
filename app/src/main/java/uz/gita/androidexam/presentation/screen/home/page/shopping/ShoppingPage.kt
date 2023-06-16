package uz.gita.androidexam.presentation.screen.home.page.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import uz.gita.androidexam.R

object ShoppingPage : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.shopping_tab)
            val icon = rememberVectorPainter(Icons.Default.ShoppingCart)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red))
    }
}