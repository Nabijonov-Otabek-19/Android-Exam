package uz.gita.androidexam.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.androidexam.ui.theme.Gray

@Composable
fun CategoryComponent(
    category: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = CardDefaults.elevatedShape,
        modifier = modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Gray),
    ) {
        Text(text = category, modifier = Modifier.padding(8.dp), fontSize = 16.sp)
    }
}