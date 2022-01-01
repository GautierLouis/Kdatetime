package com.louis.kdatetime.kdate

import com.louis.kdatetime.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlin.test.Test
import kotlin.test.assertEquals

//TODO For the moment iOS doesn't seems to handle micro/nanosecond
class TestTruncated {

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
    fun test_truncated_to_nanosecond() {
        val truncated = fakeDate.truncatedNano()
        assertEquals("2021-12-15 15:52:43:100200300", truncated.print())
    }

    @Test
    fun test_truncated_to_microsecond() {
        val truncated = fakeDate.truncatedMicro()
        assertEquals("2021-12-15 15:52:43:100200000", truncated.print())
    }

    @Test
    fun test_truncated_to_millisecond() {
        val truncated = fakeDate.truncatedMilli()
        assertEquals("2021-12-15 15:52:43:100000000", truncated.print())
    }

    @Test
    fun test_truncated_to_second() {
        val truncated = fakeDate.truncatedSecond()
        assertEquals("2021-12-15 15:52:43:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_minute() {
        val truncated = fakeDate.truncatedMinute()
        assertEquals("2021-12-15 15:52:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_hour() {
        val truncated = fakeDate.truncatedHour()
        assertEquals("2021-12-15 15:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_day() {
        val truncated = fakeDate.truncatedDay()
        assertEquals("2021-12-15 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_week() {
        val truncated = fakeDate.truncatedWeek()
        assertEquals("2021-12-13 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_month() {
        val truncated = fakeDate.truncatedMonth()
        assertEquals("2021-12-01 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_quarter() {
        val truncated = fakeDate.truncatedQuarter()
        assertEquals("2021-10-01 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_quarter_02() {
        val january = fakeDate.plus(1, KDateTimeUnit.MONTH).truncatedMonth()
        assertEquals("2022-01-01 00:00:00:000000000", january.print())
        val truncated = january.truncatedQuarter()
        assertEquals("2022-01-01 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_year() {
        val truncated = fakeDate.truncatedYear()
        assertEquals("2021-01-01 00:00:00:000000000", truncated.print())
    }

    @Test
    fun test_truncated_to_century() {
        val truncated = fakeDate.truncatedCentury()
        assertEquals("1921-01-01 00:00:00:000000000", truncated.print())
    }

    //Also test the generic one
    @Test
    fun test_truncated() {
        val mapOfResult = KDateTimeUnit.values()
            .map {
                it to when(it) {
                    KDateTimeUnit.NANOSECOND -> "2021-12-15 15:52:43:100200300"
                    KDateTimeUnit.MICROSECOND -> "2021-12-15 15:52:43:100200000"
                    KDateTimeUnit.MILLISECOND -> "2021-12-15 15:52:43:100000000"
                    KDateTimeUnit.SECOND -> "2021-12-15 15:52:43:000000000"
                    KDateTimeUnit.MINUTE -> "2021-12-15 15:52:00:000000000"
                    KDateTimeUnit.HOUR -> "2021-12-15 15:00:00:000000000"
                    KDateTimeUnit.DAY -> "2021-12-15 00:00:00:000000000"
                    KDateTimeUnit.WEEK -> "2021-12-13 00:00:00:000000000"
                    KDateTimeUnit.MONTH -> "2021-12-01 00:00:00:000000000"
                    KDateTimeUnit.QUARTER -> "2021-10-01 00:00:00:000000000"
                    KDateTimeUnit.YEAR -> "2021-01-01 00:00:00:000000000"
                    KDateTimeUnit.CENTURY -> "1921-01-01 00:00:00:000000000"
                }
            }.toMap()

        KDateTimeUnit.values().forEach {
            val truncated = fakeDate.truncated(it)
            assertEquals(mapOfResult[it], truncated.print())
        }
    }
}