package com.example.mvvmauthmodule.presentation.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmauthmodule.presentation.components.AuthButton
import com.example.mvvmauthmodule.presentation.components.AuthTextField
import com.example.mvvmauthmodule.presentation.components.HeaderBackground
import com.example.mvvmauthmodule.presentation.components.NavDestinationHelper
import com.example.mvvmauthmodule.presentation.state.register.RegisterViewModel
import com.example.mvvmauthmodule.ui.theme.Blue500
import com.example.mvvmauthmodule.ui.theme.Red500
import com.example.mvvmauthmodule.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterSuccessNavigation : ()->Unit,
    onNavigateToLoginScreen : ()->Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
){
    NavDestinationHelper(
        shouldNavigate = {
            registerViewModel.registerState.isSuccessfullyRegistered
        },
        destination = {
            onRegisterSuccessNavigation()
        }
    )

    Scaffold() {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ){
                HeaderBackground(
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    "Register",
                    style = MaterialTheme.typography.headlineMedium,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }

            RegisterContainer(
                emailValue = {registerViewModel.registerState.emailInput},
                passwordValue = {registerViewModel.registerState.passwordInput},
                confirmPasswordValue = {registerViewModel.registerState.confirmPasswordInput},
                onEmailChange = registerViewModel::onEmailInputChange,
                onPasswordChange = registerViewModel::onPasswordInputChange,
                onConfirmPasswordChange = registerViewModel::onConfirmPasswordInputChange,
                errorHint = {registerViewModel.registerState.errorMessageInput},
                isRegisterButtonEnabled = {registerViewModel.registerState.isInputValid},
                onLoginClick = onNavigateToLoginScreen,
                onRegisterButtonClick = registerViewModel::onRegisterClick,
                isPasswordShown = { registerViewModel.registerState.isPasswordShown },
                isConfirmPasswordShown = { registerViewModel.registerState.isConfirmPasswordShown },
                onPasswordVisibilityIconClick = registerViewModel::onTogglePasswordVisible,
                onConfirmPasswordVisibilityIconClick = registerViewModel::onToggleConfirmPasswordVisible,
                isLoading = { registerViewModel.registerState.isLoading},

                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 50.dp,
                        bottom = 20.dp
                    )
            )
        }

    }
}


@Composable
private fun RegisterContainer(
    emailValue : ()->String,
    passwordValue : ()->String,
    confirmPasswordValue : ()->String,
    onEmailChange : (String)->Unit,
    onPasswordChange : (String)->Unit,
    onConfirmPasswordChange : (String)->Unit,
    errorHint:()->String?,
    isRegisterButtonEnabled : ()->Boolean,
    onRegisterButtonClick : ()->Unit,
    onLoginClick : ()->Unit,
    isPasswordShown : ()->Boolean,
    isConfirmPasswordShown : ()->Boolean,
    onPasswordVisibilityIconClick : ()->Unit,
    onConfirmPasswordVisibilityIconClick : ()->Unit,
    isLoading : ()->Boolean,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ){
        AuthTextField(
            value = emailValue(),
            onValueChange = onEmailChange,
            hintText = "xyz@gmail.com",
            leadingIcon = Icons.Default.Email
        )
        AuthTextField(
            value = passwordValue(),
            onValueChange = onPasswordChange,
            hintText = "Enter password",
            leadingIcon = Icons.Default.Key,
            trailingIcon = if(isPasswordShown())
                Icons.Default.VisibilityOff
            else Icons.Default.Visibility,
            onTrailingIconClick = onPasswordVisibilityIconClick,
            visualTransformation = if(isPasswordShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        AuthTextField(
            value = confirmPasswordValue(),
            onValueChange = onConfirmPasswordChange,
            hintText = "Confirm password",
            leadingIcon = Icons.Default.Key,
            trailingIcon = if(isConfirmPasswordShown())
                Icons.Default.VisibilityOff
            else Icons.Default.Visibility,
            onTrailingIconClick = onConfirmPasswordVisibilityIconClick,
            visualTransformation = if(isConfirmPasswordShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        AuthButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Register",
            onClick = onRegisterButtonClick,
            isLoading = isLoading(),
            enabled = isRegisterButtonEnabled()
        )
        Text(
            text =  errorHint()?:"",
            style = MaterialTheme.typography.labelLarge,
            color = Red500
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text =  "Already have an account? ",
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text =  "Login",
                style = MaterialTheme.typography.labelLarge,
                color = Blue500,
                modifier = Modifier.clickable {
                    onLoginClick()
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginContainerPreview(){
    RegisterContainer(
        emailValue = { ""},
        passwordValue = { "" },
        confirmPasswordValue = { "" },
        onEmailChange = {},
        onPasswordChange ={},
        onConfirmPasswordChange ={},
        errorHint = {"Error"},
        isRegisterButtonEnabled = {true},
        onRegisterButtonClick = {},
        onLoginClick = {},
        isPasswordShown = { true },
        isConfirmPasswordShown = { true },
        onPasswordVisibilityIconClick = { },
        onConfirmPasswordVisibilityIconClick = { },
        isLoading = { false},
        modifier = Modifier
            .padding(horizontal = 12.dp)
    )
}