package uz.gita.androidexam.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.gita.androidexam.R
import uz.gita.androidexam.data.common.Product

@Composable
fun ItemComponent(
    product: Product
) {
    Column {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.ic_product),
            contentDescription = null
        )

        Text(text = product.price.toString(), color = Color.Black)
        Text(text = product.name, color = Color.Gray)
    }
}