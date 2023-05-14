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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmauthmodule.R
import com.example.mvvmauthmodule.presentation.components.AuthButton
import com.example.mvvmauthmodule.presentation.components.AuthTextField
import com.example.mvvmauthmodule.presentation.components.HeaderBackground
import com.example.mvvmauthmodule.presentation.components.NavDestinationHelper
import com.example.mvvmauthmodule.presentation.state.login.LoginViewModel
import com.example.mvvmauthmodule.ui.theme.Blue500
import com.example.mvvmauthmodule.ui.theme.Gray800
import com.example.mvvmauthmodule.ui.theme.Gray900
import com.example.mvvmauthmodule.ui.theme.Red500
import com.example.mvvmauthmodule.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccessNavigation : ()->Unit,
    onNavigateToRegisterScreen : ()->Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    NavDestinationHelper(
        shouldNavigate = {
            loginViewModel.loginState.isSuccessfullyLoggedIn
        },
        destination = {
            onLoginSuccessNavigation()
        }
    )

    Column(
        modifier = Modifier
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
                "Login",
                style = MaterialTheme.typography.headlineMedium,
                color = White,
                fontWeight = FontWeight.Bold
            )
        }

        LoginContainer(
            emailValue = {loginViewModel.loginState.emailInput},
            passwordValue = {loginViewModel.loginState.passwordInput},
            onEmailChange = loginViewModel::onEmailInputChange,
            onPasswordChange = loginViewModel::onPasswordInputChange,
            errorHint = {loginViewModel.loginState.errorMessageInput},
            isLoginButtonEnabled = {loginViewModel.loginState.isInputValid},
            onRegisterClick = onNavigateToRegisterScreen,
            onLoginButtonClick = loginViewModel::onLoginClick,
            isPasswordShown = { loginViewModel.loginState.isPasswordShown },
            onPasswordVisibilityIconClick = loginViewModel::onTogglePasswordVisible,
            isLoading = { loginViewModel.loginState.isLoading},

            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 50.dp,
                    bottom = 20.dp
                )
        )
    }
//    Scaffold() {paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp),
//                contentAlignment = Alignment.Center
//            ){
//                HeaderBackground(
//                    modifier = Modifier.fillMaxSize()
//                )
//                Text(
//                    "Login",
//                    style = MaterialTheme.typography.headlineMedium,
//                    color = White,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            LoginContainer(
//                emailValue = {loginViewModel.loginState.emailInput},
//                passwordValue = {loginViewModel.loginState.passwordInput},
//                onEmailChange = loginViewModel::onEmailInputChange,
//                onPasswordChange = loginViewModel::onPasswordInputChange,
//                errorHint = {loginViewModel.loginState.errorMessageInput},
//                isLoginButtonEnabled = {loginViewModel.loginState.isInputValid},
//                onRegisterClick = onNavigateToRegisterScreen,
//                onLoginButtonClick = loginViewModel::onLoginClick,
//                isPasswordShown = { loginViewModel.loginState.isPasswordShown },
//                onPasswordVisibilityIconClick = loginViewModel::onTogglePasswordVisible,
//                isLoading = { loginViewModel.loginState.isLoading},
//
//                modifier = Modifier
//                    .padding(
//                        start = 12.dp,
//                        end = 12.dp,
//                        top = 50.dp,
//                        bottom = 20.dp
//                    )
//            )
//        }
//
//    }
}

@Composable
private fun LoginContainer(
    emailValue : ()->String,
    passwordValue : ()->String,
    onEmailChange : (String)->Unit,
    onPasswordChange : (String)->Unit,
    errorHint:()->String?,
    isLoginButtonEnabled : ()->Boolean,
    onLoginButtonClick : ()->Unit,
    onRegisterClick : ()->Unit,
    isPasswordShown : ()->Boolean,
    onPasswordVisibilityIconClick : ()->Unit,
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
            leadingIcon = ImageVector.vectorResource(id = R.drawable.baseline_key_24),
            trailingIcon = if(isPasswordShown())
                ImageVector.vectorResource(id = R.drawable.baseline_visibility_off_24)
            else ImageVector.vectorResource(id = R.drawable.baseline_visibility_24),
            onTrailingIconClick = onPasswordVisibilityIconClick,
            visualTransformation = if(isPasswordShown()){
                VisualTransformation.None
            } else PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password
        )
        AuthButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            onClick = onLoginButtonClick,
            isLoading = isLoading(),
            enabled = isLoginButtonEnabled()
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
                text =  "Don't have an account? ",
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text =  "Register",
                style = MaterialTheme.typography.labelLarge,
                color = Blue500,
                modifier = Modifier.clickable {
                    onRegisterClick()
                }
            )
        }



    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginContainerPreview(){
    LoginContainer(
        emailValue = { ""},
        passwordValue = { "" },
        onEmailChange = {},
        onPasswordChange ={},
        errorHint = {"Error"},
        isLoginButtonEnabled = {true},
        onLoginButtonClick = {},
        onRegisterClick = {},
        isPasswordShown = { true },
        onPasswordVisibilityIconClick = { },
        isLoading = { false},
        modifier = Modifier
            .padding(horizontal = 12.dp)
    )
}