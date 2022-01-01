package com.louis.kdatetime

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestUtils {

    @Test
    fun test_lengthOfMonth_01() {
        val result = lengthOfMonth(2021, 1)
        assertEquals(31, result)
    }

    @Test
    fun test_lengthOfMonth_02() {
        val result = lengthOfMonth(2021, 2)
        assertEquals(28, result)
    }

    @Test
    fun test_lengthOfMonth_03() {
        val result = lengthOfMonth(2024, 2)
        assertEquals(29, result)
    }

    @Test
    fun test_first_day_of_week() {
        val fakeDate = KDateTime.of(
            2021,
            12,
            15,
            0,
            0,
            0,
            0,
            TimeZone.currentSystemDefault()
        )
        val firstDay = fakeDate.firstDayOfWeek()

        assertEquals(13, firstDay)

    }

    @Test
    fun test_first_day_of_week_02() {
        val fakeDate = KDateTime.of(
            2021,
            12,
            1,
            0,
            0,
            0,
            0,
            TimeZone.currentSystemDefault()
        )

        val firstDay = fakeDate.firstDayOfWeek()

        assertEquals(29, firstDay)

    }
}