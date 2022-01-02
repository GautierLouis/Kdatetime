package com.louis.kdatetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

actual fun format(
    instant: Instant,
    formatter: KDateTimeFormatter,
    zone: TimeZone,
    localeString: String
): String {
    TODO("Not yet implemented")
}

actual fun format(
    instant: Instant,
    pattern: String,
    zone: TimeZone,
    localeString: String
): String {
    TODO("Not yet implemented")
}

actual fun formatISO(
    instant: Instant,
    zone: TimeZone,
    iso: ISO
): String {
    TODO("Not yet implemented")
}

actual fun parse(
    isoString: String,
    iso: ISO
): KDateTime? {
    TODO("Not yet implemented")
}