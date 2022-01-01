package com.louis.kdatetime

import kotlinx.datetime.DateTimeUnit

enum class KDateTimeUnit {
    NANOSECOND,
    MICROSECOND,
    MILLISECOND,
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    WEEK,
    MONTH,
    QUARTER,
    YEAR,
    CENTURY
}

fun KDateTimeUnit.toNative(): DateTimeUnit {
    return when(this) {
        KDateTimeUnit.NANOSECOND -> DateTimeUnit.NANOSECOND
        KDateTimeUnit.MICROSECOND -> DateTimeUnit.MICROSECOND
        KDateTimeUnit.MILLISECOND -> DateTimeUnit.MILLISECOND
        KDateTimeUnit.SECOND -> DateTimeUnit.SECOND
        KDateTimeUnit.MINUTE -> DateTimeUnit.MINUTE
        KDateTimeUnit.HOUR -> DateTimeUnit.HOUR
        KDateTimeUnit.DAY -> DateTimeUnit.DAY
        KDateTimeUnit.WEEK -> DateTimeUnit.WEEK
        KDateTimeUnit.MONTH -> DateTimeUnit.MONTH
        KDateTimeUnit.QUARTER -> DateTimeUnit.QUARTER
        KDateTimeUnit.YEAR -> DateTimeUnit.YEAR
        KDateTimeUnit.CENTURY -> DateTimeUnit.CENTURY
    }
}