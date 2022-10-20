package com.randev.movieapp_kmm.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.randev.movieapp_kmm.android.presentation.main.MainScreen
import com.randev.movieapp_kmm.android.presentation.main.MainViewModel
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
