package uz.gita.androidexam.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.gita.androidexam.ui.theme.Light_Blue


@Composable
fun SignUpScreenComponent(
    text: String,
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    keyboardOption: KeyboardOptions,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 18.sp
        )

        BasicTextField(
            keyboardOptions = keyboardOption,
            value = value,
            onValueChange = { newText ->
                onValueChange.invoke(newText)
            },
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
                            text = placeholder,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SignUpScreenComponentPreview() {
    var value by remember { mutableStateOf("") }

    SignUpScreenComponent(
        "Email",
        modifier = Modifier, placeholder = "example.user@gmail.com", value = value,
        keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
    ) {
        value = it
    }
}