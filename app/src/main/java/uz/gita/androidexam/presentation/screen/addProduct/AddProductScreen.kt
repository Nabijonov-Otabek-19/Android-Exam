package uz.gita.androidexam.presentation.screen.addProduct

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.androidexam.R
import uz.gita.androidexam.data.common.Product
import uz.gita.androidexam.navigation.AppScreen
import uz.gita.androidexam.ui.component.CategoryComponent
import uz.gita.androidexam.ui.component.MyTextFieldComponent
import uz.gita.androidexam.ui.theme.AndroidExamTheme
import uz.gita.androidexam.ui.theme.Gray
import uz.gita.androidexam.utils.Constants
import uz.gita.androidexam.utils.logger

class AddProductScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val viewModel: AddProductContract.ViewModel = getViewModel<AddProductViewModel>()
        val uiState = viewModel.collectAsState()

        AndroidExamTheme {
            Scaffold(
                topBar = { TopBar(viewModel::onEventDispatcher) }
            ) {
                AddProductScreenContent(
                    uiState = uiState,
                    onEventDispatcher = viewModel::onEventDispatcher,
                    modifier = Modifier.padding(it)
                )
            }
        }

        viewModel.collectSideEffect {
            when (it) {
                is AddProductContract.SideEffect.HasError -> {
                    logger("SignInScreen Error = " + it.message)
                }

                is AddProductContract.SideEffect.Toast -> {
                    logger("AddProduct = $it")
                }
            }
        }
    }
}

@Composable
fun TopBar(
    onEventDispatcher: (AddProductContract.Intent) -> Unit,
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
                .padding(horizontal = 16.dp)
                .clickable { onEventDispatcher.invoke(AddProductContract.Intent.Back) },
            painter = painterResource(id = R.drawable.ic_back),
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
fun AddProductScreenContent(
    uiState: State<AddProductContract.UIState>,
    onEventDispatcher: (AddProductContract.Intent) -> Unit,
    modifier: Modifier = Modifier
) {

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("0") }
    var size by remember { mutableStateOf("0") }

    Box(modifier = modifier.fillMaxSize()) {

        Column {
            // Name
            MyTextFieldComponent(
                text = "Name",
                placeholder = "Nike",
                value = name,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { name = it }
            )
            // Price
            MyTextFieldComponent(
                text = "Price",
                placeholder = "Nike",
                value = price,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { price = it }
            )
            // Size
            MyTextFieldComponent(
                text = "Size",
                placeholder = "Nike",
                value = size,
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { size = it }
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "Category",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                IconButton(onClick = {

                }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }

            val data = (uiState.value as AddProductContract.UIState.LoadCategories).categoryList

            LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
                items(data.size) {
                    CategoryComponent(
                        category = data[it],
                        modifier = Modifier.padding(horizontal = 4.dp)
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
                if (name.isNotEmpty() && price.isNotEmpty() && size.isNotEmpty()) {
                    onEventDispatcher.invoke(
                        AddProductContract.Intent.AddProduct(
                            Product(
                                category = "Airmax",
                                price = price.toInt(),
                                name = name,
                                size = size.toLong(),
                                img = R.drawable.ic_product,
                                userId = Constants.user!!.email!!
                            )
                        )
                    )
                }
            }) {
            Text(text = "ADD TO BAG")
        }
    }
}