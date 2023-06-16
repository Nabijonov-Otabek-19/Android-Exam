package uz.gita.androidexam.presentation.screen.home.page.shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.androidexam.R
import uz.gita.androidexam.ui.component.LoadingComponent
import uz.gita.androidexam.ui.component.MyProductComponent
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray
import uz.gita.androidexam.utils.Constants
import uz.gita.androidexam.utils.logger

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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: ShoppingPageContract.ViewModel = getViewModel<ShoppingPageViewModel>()
        val uiState = viewModel.collectAsState()

        AndroidExamTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(
                    topBar = { TopBar(viewModel::onEventDispatcher) }
                ) {
                    ShoppingPageContent(
                        uiState = uiState,
                        onEventDispatcher = viewModel::onEventDispatcher,
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }

        viewModel.collectSideEffect {
            when (it) {
                is ShoppingPageContract.SideEffect.HasError -> {
                    logger("HomePageScreen Error = " + it.message)
                }
            }
        }
    }
}

@Composable
fun TopBar(
    onEventDispatcher: (ShoppingPageContract.Intent) -> Unit,
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
                .padding(horizontal = 16.dp),
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = null
        )

        Text(text = "My Products", color = Gray, textAlign = TextAlign.Center)

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
fun ShoppingPageContent(
    uiState: State<ShoppingPageContract.UIState>,
    onEventDispatcher: (ShoppingPageContract.Intent) -> Unit,
    modifier: Modifier
) {

    Box(modifier = modifier.fillMaxSize()) {
        when (uiState.value) {
            ShoppingPageContract.UIState.Loading -> {
                LoadingComponent()
                onEventDispatcher.invoke(ShoppingPageContract.Intent.LoadData(Constants.user.toString()))
            }

            is ShoppingPageContract.UIState.PrepareData -> {
                val data = (uiState.value as ShoppingPageContract.UIState.PrepareData).productsData
                if (data[0].productList.isEmpty()) {
                    Image(
                        modifier = Modifier
                            .size(150.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_empty),
                        contentDescription = null
                    )
                } else {
                    LazyColumn {
                        data.forEach { products ->
                            items(products.productList.size) {
                                MyProductComponent(
                                    product = products.productList[it],
                                    Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Button(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 8.dp)
                            .width(250.dp)
                            .height(65.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Gray),
                        onClick = {
                            onEventDispatcher.invoke(ShoppingPageContract.Intent.OpenAddProductScreen)
                        }) {
                        Text(text = "ADD PRODUCT")
                    }
                }
            }
        }
    }
}