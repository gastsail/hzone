package com.gastonsaillen.hzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
                    color = Color.Black
                ) {
                    var averageBpm by remember { mutableStateOf(0) }

                    LaunchedEffect(null) {
                        val bpmValues = listOf(
                            0,
                            60,
                            140,
                            150,
                            160,
                            170,
                            160,
                            180,
                            180,
                            195,
                            180,
                            180,
                            195,
                            180,
                            180,
                            195,
                            180,
                            180,
                            195,
                            180,
                            180,
                            195,
                            150,
                            155,
                            145,
                            148,
                            149,
                            120,
                            110,
                            100,
                            110,
                            100,
                            110,
                            100
                        )
                        for (bpm in bpmValues) {
                            averageBpm = calculateAverageBpm(bpm, averageBpm)
                            delay(1000)
                        }
                    }

                    Column(Modifier.padding(16.dp)) {
                        HZone(
                            modifier = Modifier.padding(16.dp),
                            averageBpm = averageBpm,
                            onZoneClick = {})
                        Text(text = "LALA", color = Color.White)
                    }

                }
            }
        }
    }
}