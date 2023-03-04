package com.example.scalecaching

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.scalecaching.contact.ContactActivity
import com.example.scalecaching.todo.TodoActivity
import com.example.scalecaching.ui.theme.ScaleCachingTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaleCachingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button (onClick = {
                            startActivity(
                                Intent(this@MainActivity, TodoActivity::class.java)
                            )
                        }) {
                            Text("Database Todo")
                        }
                        Button (onClick = {
                            startActivity(
                                Intent(this@MainActivity, ContactActivity::class.java)
                            )
                        }) {
                            Text("Database Contact")
                        }
                    }
                }
            }
        }
    }
}

