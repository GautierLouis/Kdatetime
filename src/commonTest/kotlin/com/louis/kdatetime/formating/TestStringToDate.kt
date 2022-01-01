package com.louis.kdatetime.formating

import com.louis.kdatetime.ISO
import com.louis.kdatetime.KDateTime
import com.louis.kdatetime.formatISO
import com.louis.kdatetime.parse
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.TimeZone.Companion.UTC
import kotlin.test.Test
import kotlin.test.assertEquals

//TODO()
class TestStringToDate {

    //15-12-2021 15:52:43 - fr
    //12-15-2021 3:52:43 PM - en
    private val fakeInstant = Instant.fromEpochSeconds(1639583563L)
    private val PARIS = TimeZone.of("Europe/Paris")
    private val LONDON = TimeZone.of("Europe/London")

    @Test
    fun test_parse_with_basic_iso_date() {

        val utc = "20211215Z"
        val paris = "20211215+0100"
        val london = "20211215Z"

        val parseUtc: KDateTime? = parse(utc, ISO.BASIC_ISO_DATE)
        val parseParis: KDateTime? = parse(paris, ISO.BASIC_ISO_DATE)
        val parseLondon: KDateTime? = parse(london, ISO.BASIC_ISO_DATE)

        val expectedUtc: String = formatISO(parseUtc!!.instant, UTC, ISO.BASIC_ISO_DATE)
        val expectedParis: String = formatISO(parseParis!!.instant, PARIS, ISO.BASIC_ISO_DATE)
        val expectedLondon: String = formatISO(parseLondon!!.instant, LONDON, ISO.BASIC_ISO_DATE)

        assertEquals(expectedUtc, utc)
        assertEquals(expectedParis, paris)
        assertEquals(expectedLondon, london)
    }

    @Test
    fun test_parse_with_iso_date() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_DATE)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_DATE)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_DATE)
        assertEquals( "2021-12-15Z", utc)
        assertEquals( "2021-12-15+01:00", paris)
        assertEquals( "2021-12-15Z", london)
    }

    @Test
    fun test_parse_with_iso_date_time() {
//        val utc = formatISO(fakeInstant, UTC, ISO.ISO_DATE_TIME)
//        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_DATE_TIME)
//        val london = formatISO(fakeInstant, LONDON, ISO.ISO_DATE_TIME)
//        assertEquals( "2021-12-15T15:52:43Z", utc)
//        assertEquals( "2021-12-15T16:52:43+01:00[Europe/Paris]", paris)
//        assertEquals( "2021-12-15T15:52:43Z[Europe/London]", london)


        val utc = "20211215Z"
        val paris = "2021-12-15T16:52:43+01:00[Europe/Paris]"
        val london = "2021-W50-3Z"
        val hoursonly = "16:52:43"
//        val aa = "Wed, 15 Dec 2021 15:52:43 GMT"

        val parseUtc = parse(utc, ISO.BASIC_ISO_DATE)
        val parseParis = parse(paris, ISO.ISO_DATE_TIME)
        val parseLondon = parse(london, ISO.ISO_WEEK_DATE)
        val hours = parse(hoursonly, ISO.ISO_LOCAL_TIME)
//        val AA = parse(aa, ISO.RFC_1123_DATE_TIME)

        assertEquals(parseUtc.toString(), utc)
        assertEquals(parseParis.toString(), paris)
        assertEquals(parseLondon.toString(), london)
//        assertEquals(AA.toString(), aa)
    }

    @Test
    fun test_parse_with_iso_instant() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_INSTANT)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_INSTANT)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_INSTANT)
        assertEquals( "2021-12-15T15:52:43Z", utc)
        assertEquals( "2021-12-15T15:52:43Z", paris)
        assertEquals( "2021-12-15T15:52:43Z", london)
    }

    @Test
    fun test_parse_with_iso_local_date() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_LOCAL_DATE)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_LOCAL_DATE)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_LOCAL_DATE)
        assertEquals( "2021-12-15", utc)
        assertEquals( "2021-12-15", paris)
        assertEquals( "2021-12-15", london)
    }

    @Test
    fun test_parse_with_iso_local_date_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_LOCAL_DATE_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_LOCAL_DATE_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_LOCAL_DATE_TIME)
        assertEquals( "2021-12-15T15:52:43", utc)
        assertEquals( "2021-12-15T16:52:43", paris)
        assertEquals( "2021-12-15T15:52:43", london)
    }

    @Test
    fun test_parse_with_iso_local_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_LOCAL_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_LOCAL_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_LOCAL_TIME)
        assertEquals( "15:52:43", utc)
        assertEquals( "16:52:43", paris)
        assertEquals( "15:52:43", london)
    }

    @Test
    fun test_parse_with_iso_offset_date() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_OFFSET_DATE)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_OFFSET_DATE)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_OFFSET_DATE)
        assertEquals( "2021-12-15Z", utc)
        assertEquals( "2021-12-15+01:00", paris)
        assertEquals( "2021-12-15Z", london)
    }

    @Test
    fun test_parse_with_iso_offset_date_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_OFFSET_DATE_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_OFFSET_DATE_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_OFFSET_DATE_TIME)
        assertEquals( "2021-12-15T15:52:43Z", utc)
        assertEquals( "2021-12-15T16:52:43+01:00", paris)
        assertEquals( "2021-12-15T15:52:43Z", london)
    }

    @Test
    fun test_parse_with_iso_offset_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_OFFSET_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_OFFSET_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_OFFSET_TIME)
        assertEquals( "15:52:43Z", utc)
        assertEquals( "16:52:43+01:00", paris)
        assertEquals( "15:52:43Z", london)
    }

    @Test
    fun test_parse_with_iso_ordinal_date() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_ORDINAL_DATE)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_ORDINAL_DATE)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_ORDINAL_DATE)
        assertEquals( "2021-349Z", utc)
        assertEquals( "2021-349+01:00", paris)
        assertEquals( "2021-349Z", london)
    }

    @Test
    fun test_parse_with_iso_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_TIME)
        assertEquals( "15:52:43Z", utc)
        assertEquals( "16:52:43+01:00", paris)
        assertEquals( "15:52:43Z", london)
    }
    @Test
    fun test_parse_with_iso_week_date() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_WEEK_DATE)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_WEEK_DATE)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_WEEK_DATE)
        assertEquals( "2021-W50-3Z", utc)
        assertEquals( "2021-W50-3+01:00", paris)
        assertEquals( "2021-W50-3Z", london)
    }

    @Test
    fun test_parse_with_iso_zoned_date_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.ISO_ZONED_DATE_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.ISO_ZONED_DATE_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.ISO_ZONED_DATE_TIME)
        assertEquals( "2021-12-15T15:52:43Z", utc)
        assertEquals( "2021-12-15T16:52:43+01:00[Europe/Paris]", paris)
        assertEquals( "2021-12-15T15:52:43Z[Europe/London]", london)
    }

    @Test
    fun test_parse_with_rfc_1123_date_time() {
        val utc = formatISO(fakeInstant, UTC, ISO.RFC_1123_DATE_TIME)
        val paris = formatISO(fakeInstant, PARIS, ISO.RFC_1123_DATE_TIME)
        val london = formatISO(fakeInstant, LONDON, ISO.RFC_1123_DATE_TIME)
        assertEquals( "Wed, 15 Dec 2021 15:52:43 GMT", utc)
        assertEquals( "Wed, 15 Dec 2021 16:52:43 +0100", paris)
        assertEquals( "Wed, 15 Dec 2021 15:52:43 GMT", london)
    }

}