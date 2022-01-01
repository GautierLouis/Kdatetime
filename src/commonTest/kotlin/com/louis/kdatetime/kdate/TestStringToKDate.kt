package com.louis.kdatetime.kdate

import com.louis.kdatetime.ISO
import com.louis.kdatetime.KDateTime
import com.louis.kdatetime.parse
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class TestStringToKDate {

    @Test
    fun convert_iso_string_utc_to_date_01() {
        val dateString = "2021-12-15T15:52:43Z"
        val formatted = KDateTime.parse(dateString)

        assertNotNull(formatted)
        assertEquals(formatted.year, 2021)
        assertEquals(formatted.month.number, 12)
        assertEquals(formatted.dayOfMonth, 15)
        assertEquals(formatted.hour, 15)
        assertEquals(formatted.minute, 52)
        assertEquals(formatted.second, 43)
        assertEquals(formatted.zone, TimeZone.UTC)
        assertEquals(formatted.toString(), "2021-12-15T15:52:43Z")

    }

    @Test
    fun convert_iso_string_paris_to_date_02() {
        val dateString = "2021-12-15T16:52:43+01:00[Europe/Paris]"
        val formatted = KDateTime.parse(dateString)

        assertNotNull(formatted)
        assertEquals(formatted.year, 2021)
        assertEquals(formatted.month.number, 12)
        assertEquals(formatted.dayOfMonth, 15)
        assertEquals(formatted.hour, 16)
        assertEquals(formatted.minute, 52)
        assertEquals(formatted.second, 43)
        assertEquals(formatted.zone, TimeZone.of("Europe/Paris"))
        assertEquals(formatted.toString(), "2021-12-15T16:52:43+01:00[Europe/Paris]")
    }

    @Test
    fun convert_iso_string_london_to_date_03() {
        val dateString = "2021-12-15T16:52:43Z[Europe/London]"
        val formatted = KDateTime.parse(dateString)

        assertNotNull(formatted)
        assertEquals(formatted.year, 2021)
        assertEquals(formatted.month.number, 12)
        assertEquals(formatted.dayOfMonth, 15)
        assertEquals(formatted.hour, 16)
        assertEquals(formatted.minute, 52)
        assertEquals(formatted.second, 43)
        assertEquals(formatted.zone, TimeZone.of("Europe/London"))
        assertEquals(formatted.toString(), "2021-12-15T16:52:43Z[Europe/London]")
    }


    @Test
    fun convert_non_valid_string_to_date_01() {
        val dateString = "2021 12 15 Z"
        val formatted = KDateTime.parse(dateString)
        assertNull(formatted)
    }

    @Test
    fun convert_non_valid_string_to_date_02() {
        val dateString = "2021/12/15T16:52:43Z"
        val formatted = KDateTime.parse(dateString)
        assertNull(formatted)
    }

}