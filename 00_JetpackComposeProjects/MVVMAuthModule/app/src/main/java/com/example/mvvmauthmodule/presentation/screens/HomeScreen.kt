package com.example.mvvmauthmodule.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold() {paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Congratulations"
            )
        }
    }

}