package me.nishant.watchit.presentation.authentication

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(AuthScreenState())
    val state: State<AuthScreenState> get() = _state

    sealed class AuthScreenEvent {
        class EditFirstName(val firstName: String) : AuthScreenEvent()
        class EditLastName(val lastName: String) : AuthScreenEvent()
        class EditEmail(val email: String) : AuthScreenEvent()
        class EditPassword(val password: String) : AuthScreenEvent()
        class EditCfmPassword(val password: String) : AuthScreenEvent()
        object LoginUser : AuthScreenEvent()
        object RegisterUser : AuthScreenEvent()
        object SwapType : AuthScreenEvent()
    }

    fun onEvent(event: AuthScreenEvent) {
        when (event) {
            is AuthScreenEvent.EditFirstName -> _state.value = state.value.copy(email = event.firstName)
            is AuthScreenEvent.EditLastName -> _state.value = state.value.copy(email = event.lastName)
            is AuthScreenEvent.EditEmail -> _state.value = state.value.copy(email = event.email)
            is AuthScreenEvent.EditPassword -> _state.value = state.value.copy(password = event.password)
            is AuthScreenEvent.EditCfmPassword -> _state.value = state.value.copy(confirmPwd = event.password)
            AuthScreenEvent.LoginUser -> {
                // TODO
            }
            AuthScreenEvent.RegisterUser -> {
                // TODO
            }
            AuthScreenEvent.SwapType -> {
                _state.value = state.value.copy(returningUser = !state.value.returningUser)
            }
        }
    }
}