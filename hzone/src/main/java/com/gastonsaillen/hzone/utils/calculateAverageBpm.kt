package com.gastonsaillen.hzone.utils

import com.gastonsaillen.hzone.data.ZoneType

/**
 * Calculates the updated average BPM using a moving average formula.
 *
 * @param newBpm The new BPM value.
 * @param currentAverage The current average BPM.
 * @return The updated average BPM.
 */
fun calculateAverageBpm(newBpm: Int, currentAverage: Int = 0): Int {
    val weight = 0.2
    return ((1 - weight) * currentAverage + weight * newBpm).toInt()
}

/**
 * Calculates the zone type based on the average BPM value.
 *
 * @param averageBpm The average BPM value.
 * @return The corresponding zone type.
 */
internal fun calculateZone(averageBpm: Int): ZoneType {
    return when {
        averageBpm in 95..114 -> ZoneType.ZONE_1
        averageBpm in 115..133 -> ZoneType.ZONE_2
        averageBpm in 134..152 -> ZoneType.ZONE_3
        averageBpm in 153..171 -> ZoneType.ZONE_4
        averageBpm >= 172 -> ZoneType.ZONE_5
        else -> ZoneType.ZONE_1 // Default to Zone 1
    }
}