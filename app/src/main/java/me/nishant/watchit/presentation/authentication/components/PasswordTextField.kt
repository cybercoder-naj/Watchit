package me.nishant.watchit.presentation.authentication.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import me.nishant.watchit.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    pwdVisibility: Boolean,
    onTogglePwdVisibility: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        visualTransformation = if (pwdVisibility)
            VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = onTogglePwdVisibility) {
                Icon(
                    painter = if (!pwdVisibility)
                        painterResource(id = R.drawable.ic_eye_open)
                    else painterResource(id = R.drawable.ic_eye_closed),
                    contentDescription = "See password"
                )
            }
        },
        modifier = modifier
    )
}