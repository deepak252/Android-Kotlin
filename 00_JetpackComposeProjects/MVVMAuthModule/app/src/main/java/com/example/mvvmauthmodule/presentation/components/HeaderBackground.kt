package com.example.mvvmauthmodule.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmauthmodule.ui.theme.Orange700

@Composable
fun HeaderBackground(
    leftColor : Color = MaterialTheme.colorScheme.primary,
    rightColor : Color =  MaterialTheme.colorScheme.tertiary,
    modifier : Modifier= Modifier
) {
    val colorList= remember{
        listOf(leftColor, rightColor)
    }
    Canvas(
        modifier = modifier
    ){
        drawCircle(
            radius = size.width,
            center = Offset(center.x, -size.width/1.5f),
            brush = Brush.linearGradient(
                colorList,
                end = Offset(center.x+500f,0f)
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeaderBackgroundPreview(){
    HeaderBackground(
        leftColor = Orange700,
    )
}