package com.nano.ai.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nano.ai.ui.screens.hub.PluginHubScreen
import com.nano.ai.ui.theme.NeuroVerseTheme

class PluginHubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuroVerseTheme {
                PluginHubScreen()
            }
        }
    }
}



