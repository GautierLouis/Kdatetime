package com.louis.kdatetime.formating

import com.louis.kdatetime.format
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestDatePattern {

    //15-12-2021 15:52:43 - fr
    //12-15-2021 3:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)

    @Test
    fun test_format_with_string_patten_01() {
        val string = format(fakeInstant, "dd-MM-yyyy HH:mm:ss", TimeZone.UTC)
        assertEquals( "15-12-2021 15:52:43", string)
    }

    @Test
    fun test_format_with_string_pattern_02() {
        val string = format(fakeInstant, "dd MMMM YYYY", TimeZone.UTC)
        assertEquals( "15 December 2021", string)
    }

    @Test
    fun test_format_with_string_pattern_03() {
        val string = format(fakeInstant, "eeee MMMM YYYY", TimeZone.UTC)
        assertEquals( "Wednesday December 2021", string)
    }
}