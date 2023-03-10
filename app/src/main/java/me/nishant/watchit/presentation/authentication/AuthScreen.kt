package me.nishant.watchit.presentation.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.nishant.watchit.presentation.authentication.AuthViewModel.AuthScreenEvent.*
import me.nishant.watchit.presentation.authentication.components.LabelledTextField
import me.nishant.watchit.presentation.authentication.components.PasswordTextField
import me.nishant.watchit.presentation.ui.theme.WatchitTheme

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel()) {
    val state = viewModel.state.value
    var pwdVisibility by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.5f),
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to Watchit!",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h4,
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (!state.returningUser) {
            LabelledTextField(label = "First name: ") {
                OutlinedTextField(
                    value = state.firstName,
                    onValueChange = { viewModel.onEvent(EditFirstName(it)) },
                    modifier = Modifier.width(220.dp)
                )
            }
            LabelledTextField(label = "Last name: ") {
                OutlinedTextField(
                    value = state.lastName,
                    onValueChange = {
                        viewModel.onEvent(EditLastName(it))
                    },
                    modifier = Modifier.width(220.dp)
                )
            }
        }
        LabelledTextField(label = "Email: ") {
            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(EditEmail(it))
                },
                modifier = Modifier.width(220.dp)
            )
        }
        LabelledTextField(label = "Password: ") {
            PasswordTextField(
                value = state.password,
                onValueChanged = { viewModel.onEvent(EditPassword(it)) },
                pwdVisibility = pwdVisibility,
                onTogglePwdVisibility = { pwdVisibility = !pwdVisibility },
                modifier = Modifier.width(220.dp)
            )
        }
        if (!state.returningUser) {
            LabelledTextField(label = "Confirm: ") {
                PasswordTextField(
                    value = state.confirmPwd,
                    onValueChanged = { viewModel.onEvent(EditPassword(it)) },
                    pwdVisibility = pwdVisibility,
                    onTogglePwdVisibility = { pwdVisibility = !pwdVisibility },
                    modifier = Modifier.width(220.dp)
                )
            }
        }
        Button(
            onClick = { viewModel.onEvent(LoginUser) },
            modifier = Modifier.fillMaxWidth(.5f)
        ) {
            Text(text = "Login")
        }
        val registerString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append("Do not have an account? ")
            }
            pushStringAnnotation(
                tag = "Register",
                annotation = "Register"
            )

            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("Register")
            }
            pop()
        }
        ClickableText(
            text = registerString,
            onClick = { offset ->
                registerString.getStringAnnotations(
                    tag = "Register",
                    start = offset,
                    end = offset
                )[0].let { viewModel.onEvent(SwapType) }
            }
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AuthScreenPreview() {
    WatchitTheme {
        AuthScreen()
    }
}