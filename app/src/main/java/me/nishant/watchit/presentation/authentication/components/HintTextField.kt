package me.nishant.watchit.presentation.authentication.components

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun HintTextField(value: String, onValueChanged: (String) -> Unit, hintText: String) {
    OutlinedTextField(
        value = value.ifBlank { hintText },
        onValueChange = onValueChanged,
        textStyle = TextStyle(
            color = if(value.isBlank()) Color.Gray else Color.Black,
            fontSize = 16.sp
        )
    )
}