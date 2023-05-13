package com.example.mvvmauthmodule.presentation.state.register

data class RegisterUiState(
    val emailInput : String = "",
    val passwordInput : String = "",
    val confirmPasswordInput : String = "",
    val errorMessageInput : String?=null,
    val isInputValid : Boolean = false,
    val isPasswordShown : Boolean = false,
    val isConfirmPasswordShown : Boolean = false,
    val isLoading : Boolean = false,
    val isSuccessfullyRegistered : Boolean = false,
    val errorMessageRegister : String? = null
)
