package uz.gita.androidexam.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.androidexam.data.common.Products

@Composable
fun ProductsComponent(
    products: Products,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = products.category,
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        LazyRow {
            items(products.productList.size) {
                ItemComponent(
                    product = products.productList[it],
                    Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}