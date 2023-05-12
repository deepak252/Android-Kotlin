package com.example.mvvmauthmodule.presentation.screens.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mvvmauthmodule.presentation.state.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccessNavigation : ()->Unit,
    onNavigateToRegisterScreen : ()->Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
){
    Scaffold() {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

        }

    }
}