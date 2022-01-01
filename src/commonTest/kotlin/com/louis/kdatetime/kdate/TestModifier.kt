package com.louis.kdatetime.kdate

import com.louis.kdatetime.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class TestModifier {

    //15-12-2021 15:52:43 - fr
    //12-15-2021 3:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)
        .plus(100, DateTimeUnit.MILLISECOND)
        .plus(200, DateTimeUnit.MICROSECOND)
        .plus(304, DateTimeUnit.NANOSECOND)
    private val fakeDate = KDateTime.ofInstant(fakeInstant, TimeZone.UTC)

    private val pattern = "YYYY-MM-dd HH:mm:ss:SSSSSSSSS"


    private fun KDateTime.print(): String {
        return format(instant, pattern, zone, getCurrentLocaleString())
    }

    @Test
    fun test_modifier_to_nanosecond() {
        val newValue = fakeDate.withNano(1)
        assertEquals("2021-12-15 15:52:43:000000001", newValue.print())
    }

    @Test
    fun test_modifier_to_microsecond() {
        val newValue = fakeDate.withMicro(1)
        assertEquals("2021-12-15 15:52:43:000100000", newValue.print())
    }

    @Test
    fun test_modifier_to_millisecond() {
        val newValue = fakeDate.withMilli(1)
        assertEquals("2021-12-15 15:52:43:100000000", newValue.print())
    }

    @Test
    fun test_modifier_to_second() {
        val newValue = fakeDate.withSecond(1)
        assertEquals("2021-12-15 15:52:01:100200304", newValue.print())
    }

    @Test
    fun test_modifier_to_minute() {
        val newValue = fakeDate.withMinute(1)
        assertEquals("2021-12-15 15:01:43:100200304", newValue.print())
    }

    @Test
    fun test_modifier_to_hour() {
        val newValue = fakeDate.withHour(1)
        assertEquals("2021-12-15 01:52:43:100200304", newValue.print())
    }

    @Test
    fun test_modifier_to_day() {
        val newValue = fakeDate.withDay(1)
        assertEquals("2021-12-01 15:52:43:100200304", newValue.print())
    }

    @Test
    fun test_modifier_to_month() {
        val newValue = fakeDate.withMonth(1)
        assertEquals("2021-01-15 15:52:43:100200304", newValue.print())
    }


    @Test
    fun test_modifier_to_year() {
        val newValue = fakeDate.withYear(2023)
        assertEquals("2023-12-15 15:52:43:100200304", newValue.print())
    }

    //Also test the generic one
    @Test
    fun test_with_success() {
        val mapOfResult = mapOf(
            KDateTimeUnit.NANOSECOND to "2021-12-15 15:52:43:000000001",
            KDateTimeUnit.MICROSECOND to "2021-12-15 15:52:43:100100000",
            KDateTimeUnit.MILLISECOND to "2021-12-15 15:52:43:100000000",
            KDateTimeUnit.SECOND to "2021-12-15 15:52:01:100200304",
            KDateTimeUnit.MINUTE to "2021-12-15 15:01:43:100200304",
            KDateTimeUnit.HOUR to "2021-12-15 01:52:43:100200304",
            KDateTimeUnit.DAY to "2021-12-01 15:52:43:100200304",
            KDateTimeUnit.MONTH to "2021-01-15 15:52:43:100200304",
            KDateTimeUnit.YEAR to "2023-12-15 15:52:43:100200304"
        )
        mapOfResult.forEach {
            val newValue = fakeDate.with(1, it.key)
            assertEquals(it.value, newValue.print())
        }
    }

    @Test
    fun test_with_error() {
        assertFails {
            fakeDate.with(1, KDateTimeUnit.CENTURY)
        }

        assertFails {
            fakeDate.with(1, KDateTimeUnit.QUARTER)
        }

        assertFails {
            fakeDate.with(1, KDateTimeUnit.WEEK)
        }

    }
}