package com.louis.kdatetime

import com.louis.kdatetime.KDateTime
import com.louis.kdatetime.KDateTimeUnit
import kotlinx.datetime.*

fun lengthOfMonth(year: Int, month: Int): Int {
    val date = KDateTime.of(
        year,
        month,
        1,
        0,
        0,
        0,
        0,
        TimeZone.UTC
    )
    val monthLater = date.instant.plus(1, DateTimeUnit.MONTH, TimeZone.UTC)
    return date.instant.daysUntil(monthLater, TimeZone.UTC)
}

fun KDateTime.firstDayOfWeek(): Int {
    val nbDayInWeek = this.dateTime.dayOfWeek.minus()
    val newDate = minus(nbDayInWeek, KDateTimeUnit.DAY)
    return newDate.dayOfMonth
}

fun DayOfWeek.minus(): Int {
    return when(this) {
        DayOfWeek.MONDAY -> 0
        DayOfWeek.TUESDAY -> 1
        DayOfWeek.WEDNESDAY -> 2
        DayOfWeek.THURSDAY -> 3
        DayOfWeek.FRIDAY -> 4
        DayOfWeek.SATURDAY -> 5
        DayOfWeek.SUNDAY -> 6
        else -> 0
    }
}

fun KDateTime.firstMonthInQuarter(): Month {
    val nbMonthToRemove = this.dateTime.month.minus()
    val newDate = minus(nbMonthToRemove, KDateTimeUnit.MONTH)
    return newDate.month
}

fun Month.minus(): Int {
    return when(this) {
        Month.JANUARY,
        Month.APRIL,
        Month.JULY,
        Month.OCTOBER -> 0

        Month.FEBRUARY,
        Month.MAY,
        Month.AUGUST,
        Month.NOVEMBER -> 1

        Month.MARCH,
        Month.JUNE,
        Month.SEPTEMBER,
        Month.DECEMBER -> 2

        else -> 0
    }
}