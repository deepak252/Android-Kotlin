package com.example.mvvmauthmodule.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmauthmodule.ui.theme.Gray900
import com.example.mvvmauthmodule.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    modifier : Modifier = Modifier,
    value : String,
    hintText : String = "",
    backgroundColor : Color = White,
    textColor : Color = Gray900,
    cursorColor : Color =  MaterialTheme.colorScheme.primary,
    enabled : Boolean = true,
    onValueChange : (String)->Unit,
    leadingIcon : ImageVector?=null,
    trailingIcon : ImageVector?=null,
    onTrailingIconClick : (()->Unit)?=null,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .border(0.5.dp, textColor, RoundedCornerShape(8.dp))
            .shadow(4.dp, RoundedCornerShape(8.dp)),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = backgroundColor,
            cursorColor = cursorColor,
//            unfocusedLabelColor = Gray400,
//            placeholderColor = Gray400,
//            focusedLabelColor = Blue500,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        leadingIcon = {
            if(leadingIcon!=null){
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = cursorColor,
                    modifier = Modifier.clickable {
                        if(onTrailingIconClick!=null){
                            onTrailingIconClick()
                        }
                    }
                )
            }
        },
        trailingIcon = {
            if(trailingIcon!=null){
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = cursorColor
                )
            }
        },
        placeholder = {
            Text(
                hintText,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
private fun AuthTextFieldPreview(){
    AuthTextField(
        modifier = Modifier.fillMaxWidth(),
        value = "1234",
        hintText = "Enter Password",
        onValueChange = {

        },
        leadingIcon = Icons.Filled.Lock,
        trailingIcon = Icons.Filled.Lock,
    )

}