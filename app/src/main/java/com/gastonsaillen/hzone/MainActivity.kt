package com.gastonsaillen.hzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gastonsaillen.hzone.ui.theme.HZoneTheme


val testList = listOf<Zone>(
    Zone(color = Color.Red, text = "Zone 1", zoneEnabled = true),
    Zone(color = Color.Blue, text = "Zone 2", zoneEnabled = false),
    Zone(color = Color.Yellow, text = "Zone 3", zoneEnabled = false)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HZoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val testList = listOf<Zone>(
                        Zone(color = Color.Red, text = "Zone 1", zoneEnabled = true),
                        Zone(color = Color.Blue, text = "Zone 2", zoneEnabled = false),
                        Zone(color = Color.Yellow, text = "Zone 3", zoneEnabled = false)
                    )
                    HZone(zoneList = testList, onZoneClick = {})
                }
            }
        }
    }
}