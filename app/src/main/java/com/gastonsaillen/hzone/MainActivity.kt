package com.gastonsaillen.hzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gastonsaillen.hzone.ui.theme.HZoneTheme
import kotlinx.coroutines.delay


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

                    var bpmV by remember { mutableStateOf(0) }
                    LaunchedEffect(null) {
                        val bpmValues =
                            listOf(80, 100, 120, 140, 160, 172, 160)
                        for (bpm in bpmValues) {
                            delay(2000)
                            bpmV = bpm
                        }

                    }
                    HZone(bpm = bpmV, onZoneClick = {})
                }
            }
        }
    }
}