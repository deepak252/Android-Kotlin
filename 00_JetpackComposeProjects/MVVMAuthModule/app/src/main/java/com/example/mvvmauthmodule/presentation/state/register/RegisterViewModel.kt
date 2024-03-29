package com.example.mvvmauthmodule.presentation.state.register

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmauthmodule.domain.model.RegisterInputValidationType
import com.example.mvvmauthmodule.domain.repository.AuthRepository
import com.example.mvvmauthmodule.domain.use_case.ValidateRegisterInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase,
    private val authRepository : AuthRepository
) :ViewModel()  {
    var registerState by mutableStateOf(
        RegisterUiState()
    )

    fun onEmailInputChange(newValue : String){
        registerState = registerState.copy(emailInput = newValue)
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue : String){
        registerState = registerState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onConfirmPasswordInputChange(newValue : String){
        registerState = registerState.copy(confirmPasswordInput = newValue)
        checkInputValidation()
    }

    fun onTogglePasswordVisible(){
        registerState = registerState.copy(isPasswordShown = !registerState.isPasswordShown)
    }

    fun onToggleConfirmPasswordVisible(){
        registerState = registerState.copy(isConfirmPasswordShown = !registerState.isConfirmPasswordShown)
    }

    fun onRegisterClick(){
        registerState = registerState.copy(isLoading = true)
        viewModelScope.launch {
            registerState = try {
                val registerResult = authRepository.login(registerState.emailInput, registerState.passwordInput)
                registerState.copy(isSuccessfullyRegistered = registerResult)
            }catch (e : Exception){
                registerState.copy(isSuccessfullyRegistered = false, errorMessageRegister = e.message)
            }finally {
                registerState = registerState.copy(isLoading = false)
            }
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            registerState.emailInput,
            registerState.passwordInput,
            registerState.confirmPasswordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType( type : RegisterInputValidationType){
        registerState = when(type){
            RegisterInputValidationType.EmptyField -> {
                registerState.copy(errorMessageInput = "All fields are required!", isInputValid = false)
            }
            RegisterInputValidationType.NoEmail->{
                registerState.copy(errorMessageInput = "Invalid Email", isInputValid = false)
            }
            RegisterInputValidationType.PasswordTooShort->{
                registerState.copy(errorMessageInput = "Password must contain at least 4 characters", isInputValid = false)
            }
            RegisterInputValidationType.PasswordNumberMissing->{
                registerState.copy(errorMessageInput = "Password must container number", isInputValid = false)
            }
            RegisterInputValidationType.PasswordCharMissing->{
                registerState.copy(errorMessageInput = "Password must container alphabet", isInputValid = false)
            }
            RegisterInputValidationType.PasswordNotMatch->{
                registerState.copy(errorMessageInput = "Password not match", isInputValid = false)
            }
            RegisterInputValidationType.Valid->{
                registerState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }
}