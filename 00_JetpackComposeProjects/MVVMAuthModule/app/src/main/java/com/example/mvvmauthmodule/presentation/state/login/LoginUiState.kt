package com.example.mvvmauthmodule.presentation.state.login

data class LoginUiState(
    val emailInput : String = "",
    val passwordInput : String = "",
    val isInputValid : Boolean = false,
    val isPasswordShown : Boolean = false,
    val errorMessageInput : String?=null,
    val isLoading : Boolean = false,
    val isSuccessfullyLoggedIn : Boolean = false,
    val errorMessageLogin : String?=null
)