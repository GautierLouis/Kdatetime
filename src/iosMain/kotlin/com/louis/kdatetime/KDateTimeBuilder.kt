package com.louis.kdatetime

import kotlinx.datetime.*
import platform.Foundation.*

/**
 * Builder of iOS
 * Because iOS can't create an instance of unknown type
 */

fun now(zone: NSTimeZone = NSTimeZone.defaultTimeZone()): KDateTime {
    val instant = Clock.System.now()
    return ofDate(instant.toNSDate(), zone)
}

fun nowZero(zone: NSTimeZone): KDateTime {
    return ofDate(Instant.fromEpochSeconds(0L).toNSDate(), zone)
}

fun of(
    year: Int, month: Int, dayOfMonth: Int,
    hour: Int, minute: Int, second: Int,
    nanoOfSecond: Int, zone: NSTimeZone
): KDateTime {
    val dt = LocalDateTime(year, month, dayOfMonth, hour, minute, second, nanoOfSecond)
    val kZone = zone.toKotlinTimeZone()
    return ofDate(dt.toInstant(kZone).toNSDate(), zone)
}

fun create(epochSeconds: Long, nanoOfSecond: Int, zone: NSTimeZone): KDateTime {
    return ofDate(Instant.fromEpochSeconds(epochSeconds, nanoOfSecond).toNSDate(), zone)
}

fun ofDate(
    date: NSDate,
    zone: NSTimeZone = NSTimeZone.defaultTimeZone()
): KDateTime {
    return KDateTime(date.toKotlinInstant(), zone.toKotlinTimeZone())
}

