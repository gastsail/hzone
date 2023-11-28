package com.gastonsaillen.hzone

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val testList = listOf<Zone>(
    Zone(color = Color.Red, text = "Zone 1", zoneEnabled = false),
    Zone(color = Color.Blue, text = "Zone 2", zoneEnabled = false),
    Zone(color = Color.Yellow, text = "Zone 3", zoneEnabled = false)
)

@Composable
fun HZone() {


}


data class Zone(
    val color: Color,
    val text: String,
    val zoneEnabled: Boolean
)