package uz.gita.androidexam.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.gita.androidexam.ui.theme.Gray
import uz.gita.androidexam.ui.theme.Light_Blue

@Composable
fun DialogComponent(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    value: String,
    keyboardOption: KeyboardOptions,
    onValueChange: (String) -> Unit
) {

    var category by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp)
                .background(color = Gray),
            elevation = CardDefaults.outlinedCardElevation()
        ) {
            Column(Modifier.padding(16.dp)) {

                Text(
                    text = "CATEGORY NAME",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                BasicTextField(
                    modifier = Modifier.padding(vertical = 16.dp),
                    keyboardOptions = keyboardOption,
                    value = category,
                    onValueChange = { newText -> category = newText },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(size = 16.dp))
                                .background(color = Light_Blue)
                                .border(
                                    width = 2.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(size = 16.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.LightGray
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Button(
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    onClick = {
                        onValueChange.invoke(category)
                        onDismiss()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "SUBMIT")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DialogPreview() {
    DialogComponent(
        onDismiss = { },
        value = "",
        keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { }
    )
}