package com.example.kekka.presentation.screen.state

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kekka.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
