package com.nano.ai.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nano.ai.ui.screens.LoggingScreen
import com.nano.ai.ui.theme.NanoAITheme

class LoggerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NanoAITheme {
                LoggingScreen {
                    finish()
                }
            }
        }
    }
}