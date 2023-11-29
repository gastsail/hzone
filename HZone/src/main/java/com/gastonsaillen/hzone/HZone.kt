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

@Composable
fun HZone(zoneList : List<Zone>, onZoneClick: (Zone) -> Unit) {
    var testListM by remember { mutableStateOf(zoneList) }

    LaunchedEffect(null) {
        delay(2000)
        testListM = testListM.mapIndexed { index, zone ->
            if (index == 1) {
                zone.copy(zoneEnabled = true, text = "Zone 2")
            } else {
                zone.copy(zoneEnabled = false)
            }
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(testListM) {
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
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun ZoneItemPreview() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ZoneItem(zone = Zone(color = Color.Red, text = "Zone 3", zoneEnabled = false), {})
        ZoneItem(zone = Zone(color = Color.Blue, text = "Zone 3", zoneEnabled = true), {})
        ZoneItem(zone = Zone(color = Color.Green, text = "Zone 3", zoneEnabled = false), {})
    }
}


data class Zone(
    val color: Color,
    val text: String,
    val zoneEnabled: Boolean
)