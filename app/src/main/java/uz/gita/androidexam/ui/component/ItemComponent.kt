package uz.gita.androidexam.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import uz.gita.androidexam.R
import uz.gita.androidexam.data.common.Product

@Composable
fun ItemComponent(
    product: Product,
    modifier: Modifier = Modifier
) {

    Card(elevation = CardDefaults.cardElevation(), modifier = modifier) {
        Column {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.ic_product),
                contentDescription = null
            )

            Text(
                text = "${product.price}$",
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = product.name,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}