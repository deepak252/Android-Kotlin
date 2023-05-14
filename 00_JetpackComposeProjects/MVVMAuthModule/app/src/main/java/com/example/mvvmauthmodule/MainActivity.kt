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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmauthmodule.nav_graph.RootNavGraph
import com.example.mvvmauthmodule.ui.theme.Gray500
import com.example.mvvmauthmodule.ui.theme.MVVMAuthModuleTheme
import com.example.mvvmauthmodule.ui.theme.NavyBlue700
import com.example.mvvmauthmodule.ui.theme.NavyBlue900
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = NavyBlue900.toArgb()
        window.navigationBarColor = NavyBlue900.toArgb()

        setContent {
            MVVMAuthModuleTheme(
//                darkTheme = true
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
//                    contentColor = Gray500
                ) {
                    RootNavGraph()
                }
            }
        }
    }
}
