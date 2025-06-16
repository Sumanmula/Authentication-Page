package com.example.firstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.firstapplication.ui.theme.FirstApplicationTheme
import com.example.firstapplication.view.FirstScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    FirstScreen()
                }
            }
        }
    }
}