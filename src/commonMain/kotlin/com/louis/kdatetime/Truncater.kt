package com.louis.kdatetime

import kotlinx.datetime.number

fun KDateTime.truncatedNano(): KDateTime {
    val remove = nano - (nano / 10) * 10
    return minus(remove, KDateTimeUnit.NANOSECOND)
}

fun KDateTime.truncatedMicro(): KDateTime {
    val remove = nano - nano / 1_000 * 1_000
    return minus(remove, KDateTimeUnit.NANOSECOND)
}

fun KDateTime.truncatedMilli(): KDateTime {
    val remove = nano - nano / 1_000_000 * 1_000_000
    return minus(remove, KDateTimeUnit.NANOSECOND)
}

fun KDateTime.truncatedSecond(): KDateTime {
    return minus(nano, KDateTimeUnit.NANOSECOND)
}

fun KDateTime.truncatedMinute(): KDateTime {
    return truncatedSecond()
        .minus(second, KDateTimeUnit.SECOND)
}

fun KDateTime.truncatedHour(): KDateTime {
    return truncatedMinute()
        .minus(minute, KDateTimeUnit.MINUTE)
}

fun KDateTime.truncatedDay(): KDateTime {
    return truncatedHour()
        .minus(hour, KDateTimeUnit.HOUR)
}

fun KDateTime.truncatedWeek(): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        firstDayOfWeek(),
        0,
        0,
        0,
        0,
        zone
    )
}

fun KDateTime.truncatedMonth(): KDateTime {
    return KDateTime.of(
        year,
        month.number,
        1,
        0,
        0,
        0,
        0,
        zone
    )
}

fun KDateTime.truncatedQuarter(): KDateTime {
    return KDateTime.of(
        year,
        firstMonthInQuarter().number,
        1,
        0,
        0,
        0,
        0,
        zone
    )
}


fun KDateTime.truncatedYear(): KDateTime {
    return KDateTime.of(
        year,
        1,
        1,
        0,
        0,
        0,
        0,
        zone
    )
}

fun KDateTime.truncatedCentury(): KDateTime {
    return KDateTime.of(
        year - 100,
        1,
        1,
        0,
        0,
        0,
        0,
        zone
    )
}