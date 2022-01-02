package com.louis.kdatetime

import kotlinx.datetime.*
import platform.Foundation.*

actual fun format(
    instant: Instant,
    pattern: String,
    zone: TimeZone,
    localeString: String
): String {
    val formatter = NSDateFormatter().apply {
        dateFormat = pattern
    }
    return format(instant, formatter, zone, localeString)
}

actual fun format(
    instant: Instant,
    formatter: KDateTimeFormatter,
    zone: TimeZone,
    localeString: String
): String {
    val kind = formatter.format
    val nsFormatter = NSDateFormatter().apply {
        if (kind == Format.TIME || kind == Format.DATE_TIME) {
            timeStyle = formatter.timeStyle.toNSDateFormatter()
        }

        if (kind == Format.DATE || kind == Format.DATE_TIME) {
            dateStyle = formatter.dateStyle.toNSDateFormatter()
        }
    }

    return format(instant, nsFormatter, zone, localeString)
}

private fun format(instant: Instant, formatter: NSDateFormatter, zone: TimeZone, localeString: String): String {
    return formatter.apply {
        setTimeZone(zone.toNSTimeZone())
        locale = NSLocale.localeWithLocaleIdentifier(localeString)
    }.stringFromDate(instant.toNSDate())
}

actual fun formatISO(instant: Instant, zone: TimeZone, iso: ISO): String {
    return NSISO8601DateFormatter().stringFromDate(instant.toNSDate())
}

actual fun parse(isoString: String, iso: ISO): KDateTime? {

    return null
}

//Simple functions with native objects (easier to use in swift)
fun format(
    date: NSDate,
    pattern: String,
    zone: NSTimeZone,
    locale: NSLocale
): String {
    return format(date.toKotlinInstant(), pattern, zone.toKotlinTimeZone(), locale.languageCode)
}

fun format(
    date: NSDate,
    formatter: KDateTimeFormatter,
    zone: NSTimeZone,
    locale: NSLocale
): String {
    return format(date.toKotlinInstant(), formatter, zone.toKotlinTimeZone(), locale.languageCode)
}
