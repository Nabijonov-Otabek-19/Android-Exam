package uz.gita.androidexam.ui.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import uz.gita.androidexam.data.common.Products

@Composable
fun ProductsComponent(
    products: Products
) {
    Text(text = products.category)
    LazyRow {
        items(products.productList.size) {
            ItemComponent(product = products.productList[it])
        }
    }
}