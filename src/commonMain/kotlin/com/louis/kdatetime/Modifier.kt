package com.louis.kdatetime

import com.louis.kdatetime.KDateTime
import kotlinx.datetime.number

fun KDateTime.withNano(nano: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withMicro(micro: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        micro * 100_000,
        zone
    )
}

fun KDateTime.withMilli(milli: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        milli * 100_000_000 ,
        zone
    )
}

fun KDateTime.withSecond(second: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withMinute(minute: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withHour(hour: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withDay(dayOfMonth: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withMonth(month: Int): KDateTime {
    return KDateTime.of(
        year,
        month,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}

fun KDateTime.withYear(year: Int): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        dayOfMonth,
        hour,
        minute,
        second,
        nano,
        zone
    )
}
