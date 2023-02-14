package me.nishant.watchit.presentation.authentication

data class AuthScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPwd: String = "",
    val returningUser: Boolean = false
)
