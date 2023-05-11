package com.example.mvvmauthmodule.presentation.state.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.mvvmauthmodule.domain.model.LoginInputValidationType
import com.example.mvvmauthmodule.domain.use_case.ValidateLoginInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase
) : ViewModel(){
    var loginState by mutableStateOf(LoginUiState())

    fun onEmailInputChange(newValue : String){
        loginState = loginState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue : String){
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onTogglePasswordVisible(){
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType( type : LoginInputValidationType){
        loginState = when(type){
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "All fields are required!", isInputValid = false)
            }
            LoginInputValidationType.NoEmail->{
                loginState.copy(errorMessageInput = "Invalid Email", isInputValid = false)
            }
            LoginInputValidationType.Valid->{
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}