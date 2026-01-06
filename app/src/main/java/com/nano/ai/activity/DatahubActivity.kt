package com.nano.ai.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nano.ai.ui.screens.hub.DataHubScreen
import com.nano.ai.ui.theme.NanoAiTheme
import com.google.firebase.FirebaseApp

class DatahubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            NanoAiTheme {
                DataHubScreen()
            }
        }
    }
}
