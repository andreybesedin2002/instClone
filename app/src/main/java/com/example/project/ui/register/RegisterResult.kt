package com.example.project.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: Boolean = false,
    val error: Int? = null
)