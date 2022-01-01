package com.louis.kdatetime.kdate

import com.louis.kdatetime.KDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCompanion {

    @Test
    fun of_in_utc() {
        val date = KDateTime.of(
            2021,
            1,
            28,
            4,
            59,
            34,
            100,
            TimeZone.UTC
        )

        assertEquals(date.year, 2021)
        assertEquals(date.month.number, 1)
        assertEquals(date.dayOfMonth, 28)
        assertEquals(date.hour, 4)
        assertEquals(date.minute, 59)
        assertEquals(date.second, 34)
    }

    @Test
    fun of_in_paris() {
        val date = KDateTime.of(
            2021,
            1,
            28,
            4,
            59,
            34,
            100,
            TimeZone.of("Europe/Paris")
        )

        assertEquals(date.year, 2021)
        assertEquals(date.month.number, 1)
        assertEquals(date.dayOfMonth, 28)
        assertEquals(date.hour, 4)
        assertEquals(date.minute, 59)
        assertEquals(date.second, 34)
    }

    @Test
    fun of_zero() {
        val date = KDateTime.of(
            2021,
            1,
            1,
            0,
            0,
            0,
            0,
            TimeZone.UTC
        )


        assertEquals(date.year, 2021)
        assertEquals(date.month.number, 1)
        assertEquals(date.dayOfMonth, 1)
        assertEquals(date.hour, 0)
        assertEquals(date.minute, 0)
        assertEquals(date.second, 0)
    }

    @Test
    fun of_90() {
        val date = KDateTime.of(
            90,
            1,
            1,
            0,
            0,
            0,
            0,
            TimeZone.UTC
        )

        assertEquals(date.year, 90)
        assertEquals(date.month.number, 1)
        assertEquals(date.dayOfMonth, 1)
        assertEquals(date.hour, 0)
        assertEquals(date.minute, 0)
        assertEquals(date.second, 0)
    }
}