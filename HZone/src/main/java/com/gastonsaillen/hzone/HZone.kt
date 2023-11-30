package com.gastonsaillen.hzone

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val hZones = listOf<Zone>(
    Zone(color = Color.Blue, text = "ZONE 1", zoneEnabled = false, zoneType = ZoneType.ZONE_1),
    Zone(color = Color.Green, text = "ZONE 2", zoneEnabled = false, zoneType = ZoneType.ZONE_2),
    Zone(color = Color.Yellow, text = "ZONE 3", zoneEnabled = false, zoneType = ZoneType.ZONE_3),
    Zone(color = Color.Cyan, text = "ZONE 4", zoneEnabled = false, zoneType = ZoneType.ZONE_4),
    Zone(color = Color.Red, text = "ZONE 5", zoneEnabled = false, zoneType = ZoneType.ZONE_5)
)

/**
 * Composable function that displays a row of zones based on the average BPM value.
 *
 * @param averageBpm The average BPM value to determine the enabled zone.
 * @param onZoneClick Callback function for zone click events.
 */
@Composable
fun HZone(averageBpm: Int, onZoneClick: (Zone) -> Unit) {
    Log.d("AvgBPM", "Avg: $averageBpm")
    val calculatedZoneType = calculateZone(averageBpm)

    val modifiedZones = hZones.mapIndexed { index, zone ->
        if (zone.zoneType == calculatedZoneType) {
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

/**
 * Composable function that represents an individual zone item.
 *
 * @param zone The zone to display.
 * @param onZoneClick Callback function for zone click events.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ZoneItem(zone: Zone, onZoneClick: (Zone) -> Unit) {
    val transition = updateTransition(targetState = zone.zoneEnabled, label = "zoneTransition")
    val size by transition.animateDp(label = "size") { enabled ->
        if (enabled) 30.dp else 30.dp
    }

    Box {
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
                    modifier = Modifier.padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp,
                        end = 8.dp
                    ),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    text = zone.text,
                    color = Color.Black
                )
            }
        }
        if (zone.zoneEnabled) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = 8.dp)
                    .padding(bottom = 4.dp) // Adjust the padding as needed
            )
        }
    }
}


/**
 * Calculates the updated average BPM using a moving average formula.
 *
 * @param newBpm The new BPM value.
 * @param currentAverage The current average BPM.
 * @return The updated average BPM.
 */
fun calculateAverageBpm(newBpm: Int, currentAverage: Int): Int {
    val weight = 0.2
    return ((1 - weight) * currentAverage + weight * newBpm).toInt()
}

/**
 * Calculates the zone type based on the average BPM value.
 *
 * @param averageBpm The average BPM value.
 * @return The corresponding zone type.
 */
private fun calculateZone(averageBpm: Int): ZoneType {
    return when {
        averageBpm in 95..114 -> ZoneType.ZONE_1
        averageBpm in 115..133 -> ZoneType.ZONE_2
        averageBpm in 134..152 -> ZoneType.ZONE_3
        averageBpm in 153..171 -> ZoneType.ZONE_4
        averageBpm >= 172 -> ZoneType.ZONE_5
        else -> ZoneType.ZONE_1 // Default to Zone 1
    }
}


/**
 * Composable function for previewing an individual zone item.
 */
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

/**
 * Data class representing a zone with color, text, type, and enabled status.
 *
 * @property color The color of the zone.
 * @property text The text to display for the zone.
 * @property zoneType The type of the zone.
 * @property zoneEnabled Whether the zone is enabled or not.
 */
data class Zone(
    val color: Color,
    val text: String,
    val zoneType: ZoneType,
    val zoneEnabled: Boolean
)

/**
 * Enumeration representing different zone types.
 */
enum class ZoneType {
    ZONE_1, ZONE_2, ZONE_3, ZONE_4, ZONE_5
}