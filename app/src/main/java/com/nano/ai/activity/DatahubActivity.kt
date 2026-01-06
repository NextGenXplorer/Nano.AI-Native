package com.nano.ai.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nano.ai.ui.screens.hub.DataHubScreen
import com.nano.ai.ui.theme.NeuroVerseTheme
import com.nano.ai.ui.theme.rDP

class DatahubActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuroVerseTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Data Hub",
                                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)
                                )
                            },
                            actions = {
                                Button(
                                    onClick = {
                                        val intent = Intent(this@DatahubActivity, MainActivity::class.java).apply {
                                            putExtra("nav", true)
                                        }
                                        startActivity(intent)
                                    },
                                    modifier = Modifier.padding(end = rDP(12.dp)),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                        contentColor = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Icon(Icons.Outlined.Home, "Home")
                                    Spacer(Modifier.width(rDP(3.dp)))
                                    Text("Home")
                                }
                            },
                            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
                        )
                    }) {
                    DataHubScreen(paddingValues = it)
                }
            }
        }
    }
}

