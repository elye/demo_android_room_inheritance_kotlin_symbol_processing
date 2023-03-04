package com.example.scalecaching.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.scalecaching.MyApplication
import com.example.scalecaching.ui.theme.ScaleCachingTheme

class ContactActivity : ComponentActivity() {
    private val viewModel by lazy {
        ContactViewModel((application as MyApplication).db.contactDao())
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
                    ContactMainView(viewModel)
                }
            }
        }
    }
}
