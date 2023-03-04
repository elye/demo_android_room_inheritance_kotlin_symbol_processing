package com.example.scalecaching.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.scalecaching.MyApplication
import com.example.scalecaching.ui.theme.ScaleCachingTheme

class TodoActivity : ComponentActivity() {
    private val viewModel by lazy {
        TodoViewModel((application as MyApplication).db.todoDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaleCachingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TodoMainView(viewModel)
                }
            }
        }
    }
}
