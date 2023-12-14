package com.gastonsaillen.hzone.data

import androidx.compose.ui.graphics.Color
import com.gastonsaillen.hzone.Zone

/**
 * Default Heart Rate Zones.
 *
 * This list contains default heart rate zones for a workout. Each zone is represented by a
 * [Zone] object, specifying its color, display text, initial enabled state, and the corresponding
 * [ZoneType].
 *
 * The default heart rate zones are as follows:
 * - ZONE 1: Blue
 * - ZONE 2: Green
 * - ZONE 3: Yellow
 * - ZONE 4: Cyan
 * - ZONE 5: Red
 *
 * Each zone is initialized with `zoneEnabled` set to `false` by default.
 *
 * @see Zone
 * @see ZoneType
 */
internal val hZones = listOf(
    Zone(color = Color.Blue, text = "ZONE 1", zoneEnabled = false, zoneType = ZoneType.ZONE_1),
    Zone(color = Color.Green, text = "ZONE 2", zoneEnabled = false, zoneType = ZoneType.ZONE_2),
    Zone(color = Color.Yellow, text = "ZONE 3", zoneEnabled = false, zoneType = ZoneType.ZONE_3),
    Zone(color = Color.Cyan, text = "ZONE 4", zoneEnabled = false, zoneType = ZoneType.ZONE_4),
    Zone(color = Color.Red, text = "ZONE 5", zoneEnabled = false, zoneType = ZoneType.ZONE_5)
)

/**
 * Enumeration representing different zone types.
 */
enum class ZoneType {
    ZONE_1, ZONE_2, ZONE_3, ZONE_4, ZONE_5
}