package uz.gita.androidexam.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.androidexam.R
import uz.gita.androidexam.data.common.Product

@Composable
fun MyProductComponent(
    product: Product,
    modifier: Modifier = Modifier
) {

    Card(elevation = CardDefaults.cardElevation(), modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(4.dp)) {
            Image(
                modifier = Modifier.size(70.dp),
                painter = painterResource(id = R.drawable.ic_product),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = product.name, fontSize = 14.sp)
                Text(text = "${product.price}$", fontSize = 12.sp)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_minus),
                        contentDescription = null
                    )
                }

                Text(text = "1")

                IconButton(onClick = { }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }
    }
}