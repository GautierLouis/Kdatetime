package com.louis.kdatetime

import kotlinx.datetime.*


// Instant -> String
expect fun format(
    instant: Instant,
    pattern: String,
    zone: TimeZone,
    localeString: String = getCurrentLocaleString()
): String

expect fun format(
    instant: Instant,
    formatter: KDateTimeFormatter,
    zone: TimeZone,
    localeString: String = getCurrentLocaleString()
): String

//TODO Add locale here
expect fun formatISO(
    instant: Instant,
    zone: TimeZone,
    iso: ISO
): String


//String -> KDateTime
expect fun parse(isoString: String, iso: ISO): KDateTime?