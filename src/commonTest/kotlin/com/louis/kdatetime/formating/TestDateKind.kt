package com.louis.kdatetime.formating

import com.louis.kdatetime.DateFormatter
import com.louis.kdatetime.DateTimeStyle
import com.louis.kdatetime.Format
import com.louis.kdatetime.format
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TestDateKind {

    //15-12-2021 15:52:43 - fr
    //12-15-2021 3:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)

    @Test
    fun test_format_with_time_kind() {
        val string = format(fakeInstant, DateFormatter(Format.TIME, DateTimeStyle.SHORT), TimeZone.UTC)
        assertEquals( "3:52 PM", string)
    }

    @Test
    fun test_format_with_date_time_kind() {
        val string = format(fakeInstant, DateFormatter(Format.DATE_TIME, DateTimeStyle.SHORT), TimeZone.UTC)
        assertEquals( "12/15/21, 3:52 PM", string)
    }

    @Test
    fun test_format_with_date_kind() {
        val string = format(fakeInstant, DateFormatter(Format.DATE, DateTimeStyle.SHORT), TimeZone.UTC)
        assertEquals( "12/15/21", string)
    }
}