package com.gastonsaillen.hzone

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

val hZones = listOf<Zone>(
    Zone(color = Color.Blue, text = "ZONE 1", zoneEnabled = false, zoneType = ZoneType.ZONE_1),
    Zone(color = Color.Green, text = "ZONE 2", zoneEnabled = false, zoneType = ZoneType.ZONE_2),
    Zone(color = Color.Yellow, text = "ZONE 3", zoneEnabled = false, zoneType = ZoneType.ZONE_3),
    Zone(color = Color.Cyan, text = "ZONE 4", zoneEnabled = false, zoneType = ZoneType.ZONE_4),
    Zone(color = Color.Red, text = "ZONE 5", zoneEnabled = false, zoneType = ZoneType.ZONE_5)
)

@Composable
fun HZone(bpm: Int, onZoneClick: (Zone) -> Unit) {

    val calculatedZoneType = calculateZone(bpm)

    val modifiedZones = hZones.mapIndexed { index, zone ->
        if (zone.zoneType == calculatedZoneType.zoneType) {
            zone.copy(zoneEnabled = true)
        } else {
            zone.copy(zoneEnabled = false)
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(modifiedZones) {
            ZoneItem(zone = it, onZoneClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ZoneItem(zone: Zone, onZoneClick: (Zone) -> Unit) {
    val transition = updateTransition(targetState = zone.zoneEnabled, label = "zoneTransition")
    val size by transition.animateDp(label = "size") { enabled ->
        if (enabled) 30.dp else 30.dp
    }

    Card(
        modifier = Modifier
            .animateContentSize()
            .then(
                if (zone.zoneEnabled) Modifier.wrapContentSize()
                else Modifier.size(size)
            ),
        shape = RoundedCornerShape(30),
        colors = CardDefaults.cardColors(
            containerColor = zone.color,
            disabledContainerColor = zone.color.copy(alpha = .6f)
        ),
        enabled = zone.zoneEnabled,
        onClick = { onZoneClick.invoke(zone) },
    ) {
        if (zone.zoneEnabled) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = zone.text,
                color = Color.Black
            )
        }
    }
}

private fun calculateZone(bpm: Int): Zone {
    return when {
        bpm in 95..114 -> hZones[0].copy(zoneEnabled = true)
        bpm in 115..133 -> hZones[1].copy(zoneEnabled = true)
        bpm in 134..152 -> hZones[2].copy(zoneEnabled = true)
        bpm in 153..171 -> hZones[3].copy(zoneEnabled = true)
        bpm >= 172 -> hZones[4].copy(zoneEnabled = true)
        else -> hZones[0].copy(zoneEnabled = true) // Default to Zone 1
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ZoneItemPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ZoneItem(
            zone = Zone(
                color = Color.Red,
                text = "Zone 1",
                zoneEnabled = false,
                zoneType = ZoneType.ZONE_1
            ), {})
        ZoneItem(
            zone = Zone(
                color = Color.Yellow,
                text = "Zone 2",
                zoneEnabled = false,
                zoneType = ZoneType.ZONE_2
            ), {})
        ZoneItem(
            zone = Zone(
                color = Color.Blue,
                text = "Zone 3",
                zoneEnabled = true,
                zoneType = ZoneType.ZONE_3
            ), {})
    }
}


data class Zone(
    val color: Color,
    val text: String,
    val zoneType: ZoneType,
    val zoneEnabled: Boolean
)

enum class ZoneType {
    ZONE_1, ZONE_2, ZONE_3, ZONE_4, ZONE_5
}