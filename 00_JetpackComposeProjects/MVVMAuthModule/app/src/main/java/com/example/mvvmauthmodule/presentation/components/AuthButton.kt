package com.example.mvvmauthmodule.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmauthmodule.ui.theme.Gray900
import com.example.mvvmauthmodule.ui.theme.White

@Composable
fun AuthButton(
    modifier : Modifier = Modifier,
    text : String,
    backgroundColor : Color = MaterialTheme.colorScheme.primary,
    contentColor : Color = White,
    enabled : Boolean = true,
    isLoading : Boolean = false,
    onClick : ()->Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        enabled = enabled
    ) {
        if(isLoading){
            CircularProgressIndicator(
                color = White,
                modifier = Modifier.padding(8.dp)
            )
        }else{
            Text(
                text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthButtonPreview(){
    AuthButton(
        text = "Login",
        onClick = {},
        isLoading = true
    )

}