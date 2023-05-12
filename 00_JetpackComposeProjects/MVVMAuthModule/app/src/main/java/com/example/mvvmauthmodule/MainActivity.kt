package com.example.mvvmauthmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmauthmodule.nav_graph.RootNavGraph
import com.example.mvvmauthmodule.ui.theme.MVVMAuthModuleTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMAuthModuleTheme {
                // A surface container using the 'background' color from the theme
                RootNavGraph()
            }
        }
    }
}
