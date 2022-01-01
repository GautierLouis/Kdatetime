package com.louis.kdatetime.formating

import com.louis.kdatetime.DateFormatter
import com.louis.kdatetime.DateTimeStyle
import com.louis.kdatetime.Format
import com.louis.kdatetime.format
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

//Medium, Long and Full style have different representation on Android / iOS
//But both of them are right
expect val mediumStyle: String
expect val longStyle: String
expect val fullStyle: String

class TestDateStyle {

    //15-12-2021 15:52:43 - fr
    //12-15-2021 3:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)

    @Test
    fun test_format_with_style_short() {
        val string = format(fakeInstant, DateFormatter(Format.DATE_TIME, DateTimeStyle.SHORT), TimeZone.UTC)
        assertEquals( "12/15/21, 3:52 PM", string)
    }

    @Test
    fun test_format_with_style_medium() {
        val string = format(fakeInstant, DateFormatter(Format.DATE_TIME, DateTimeStyle.MEDIUM), TimeZone.UTC)
        assertEquals(mediumStyle, string)
    }

    @Test
    fun test_format_with_style_long() {
        val string = format(fakeInstant, DateFormatter(Format.DATE_TIME, DateTimeStyle.LONG), TimeZone.UTC)
        assertEquals(longStyle, string)
    }

    @Test
    fun test_format_with_style_full() {
        val string = format(fakeInstant, DateFormatter(Format.DATE_TIME, DateTimeStyle.FULL), TimeZone.UTC)
        assertEquals(fullStyle, string)
    }
}