package com.example.a24_09_sdv_m5a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.a24_09_sdv_m5a.model.MainViewModel
import com.example.a24_09_sdv_m5a.ui.screens.SearchScreen
import com.example.a24_09_sdv_m5a.ui.theme._24_09_sdv_m5ATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _24_09_sdv_m5ATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val mainViewModel = MainViewModel()
                    SearchScreen(modifier = Modifier.padding(innerPadding), mainViewModel)
                }
            }
        }
    }
}