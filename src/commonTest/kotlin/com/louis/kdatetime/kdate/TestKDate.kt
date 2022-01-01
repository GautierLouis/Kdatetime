package com.louis.kdatetime.kdate

import com.louis.kdatetime.KDateTime
import com.louis.kdatetime.KDateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.*

class TestKDate {

    //15-12-2021 16:52:43 - fr
    //12-15-2021 4:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)


    @Test
    fun test_is_not_same_day() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.DAY)
        assertFalse(day1.isSameDay(day2))
    }

    @Test
    fun test_is_not_same_day_02() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.MONTH)
        assertFalse(day1.isSameDay(day2))
    }

    @Test
    fun test_is_same_day() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC)
        assertTrue(day1.isSameDay(day2))
    }

    @Test
    fun test_is_not_same_year() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.YEAR)
        assertFalse(day1.isSameYear(day2))
    }

    @Test
    fun test_is_same_year() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC)
        assertTrue(day1.isSameYear(day2))
    }

    @Test
    fun test_is_before_01() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.MILLISECOND)
        assertTrue(day1.isBefore(day2))
    }

    @Test
    fun test_is_before_02() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.HOUR)
        assertTrue(day1.isBefore(day2))
    }

    @Test
    fun test_is_after_01() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.MILLISECOND)
        assertTrue(day2.isAfter(day1))
    }

    @Test
    fun test_is_after_02() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.HOUR)
        assertTrue(day2.isAfter(day1))
    }

    @Test
    fun test_period_until_01() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.HOUR)
        val period = day1.periodUntil(day2)

        assertEquals(0, period.years)
        assertEquals(0, period.months)
        assertEquals(0, period.days)
        assertEquals(1, period.hours)
        assertEquals(0, period.minutes)
        assertEquals(0, period.seconds)
    }

    @Test
    fun test_period_until_02() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.HOUR)
        val period = day2.periodUntil(day1)

        assertEquals(0, period.years)
        assertEquals(0, period.months)
        assertEquals(0, period.days)
        assertEquals(-1, period.hours)
        assertEquals(0, period.minutes)
        assertEquals(0, period.seconds)
    }

    @Test
    fun test_to_string() {
        val utc = KDateTime(fakeInstant, TimeZone.UTC)
        val paris = KDateTime(fakeInstant, TimeZone.of("Europe/Paris"))
        val london = KDateTime(fakeInstant, TimeZone.of("Europe/London"))

        assertEquals("2021-12-15T15:52:43Z", utc.toString())
        assertEquals("2021-12-15T16:52:43+01:00[Europe/Paris]", paris.toString())
        assertEquals("2021-12-15T15:52:43+00:00[Europe/London]", london.toString())
    }

    @Test
    fun test_equals() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC)
        assertEquals(day1, day2)
    }

    @Test
    fun test_not_equals() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC).plus(1, KDateTimeUnit.HOUR)
        assertNotEquals(day1, day2)
    }

    @Test
    fun test_equals_hash() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.UTC)
        assertEquals(day1.hashCode(), day2.hashCode())
    }

    @Test
    fun test_not_equals_hash() {
        val day1 = KDateTime(fakeInstant, TimeZone.UTC)
        val day2 = KDateTime(fakeInstant, TimeZone.of("Europe/Paris"))
        assertNotEquals(day1.hashCode(), day2.hashCode())
    }

}