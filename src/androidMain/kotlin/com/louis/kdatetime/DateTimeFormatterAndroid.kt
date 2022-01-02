package com.louis.kdatetime

import android.annotation.SuppressLint
import kotlinx.datetime.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import java.lang.Exception
import java.time.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.*
import java.util.*

@SuppressLint("NewApi")
actual fun format(
    instant: Instant,
    pattern: String,
    zone: TimeZone,
    localeString: String
): String {
    return format(instant, DateTimeFormatter.ofPattern(pattern), zone, localeString)
}

@SuppressLint("NewApi")
actual fun format(
    instant: Instant,
    formatter: KDateTimeFormatter,
    zone: TimeZone,
    localeString: String
): String {
    return format(instant, formatter.toJavaDateTimeFormatter(), zone, localeString)

}

@SuppressLint("NewApi")
private fun format(
    instant: Instant,
    formatter: DateTimeFormatter,
    zone: TimeZone,
    localeString: String
): String {
    val locale = Locale.forLanguageTag(localeString)
    return formatter
        .withZone(zone.toJavaZoneId())
        .withLocale(locale)
        .format(instant.toJavaInstant())
}

@SuppressLint("NewApi")
actual fun formatISO(
    instant: Instant,
    zone: TimeZone,
    iso: ISO
): String {
    return iso
        .toJavaDateTimeIso()
        .withZone(zone.toJavaZoneId())
        .format(instant.toJavaInstant())

}

@SuppressLint("NewApi")
actual fun parse(isoString: String, iso: ISO): KDateTime? {
    return if (iso == ISO.ISO_ZONED_DATE_TIME) tmp(isoString)
    else parseIso(isoString, iso)

}

@SuppressLint("NewApi")
private fun tmp(isoString: String): KDateTime? {
    return try {
        val zdt = ZonedDateTime.parse(isoString, ISO.ISO_ZONED_DATE_TIME.toJavaDateTimeIso())
        KDateTime(zdt!!.toInstant()!!.toKotlinInstant(), zdt.zone.toKotlinTimeZone())
    } catch (e: Exception) {
        null
    }
}

@Suppress("UNREACHABLE_CODE", "NewApi")
private fun parseIso(isoString: String, iso: ISO): KDateTime? {
    return TODO()
    return try {
        println("INPUT : $isoString with ${iso.name}")

        val temporal: TemporalAccessor = iso.toJavaDateTimeIso()
            .parseBest(isoString, ZonedDateTime::from, LocalDate::from, LocalTime::from)

        println("TEMPORAL : $temporal")


        if (temporal.isSupported(ChronoField.HOUR_OF_DAY) && temporal.isSupported(ChronoField.YEAR)) {
            val zdt = ZonedDateTime.from(temporal)
            println("ZDT : $zdt")
            val kdate = KDateTime(zdt!!.toInstant()!!.toKotlinInstant(), zdt.zone.toKotlinTimeZone())
            println("KDATE : $kdate \n")
        } else if (temporal.isSupported(ChronoField.YEAR)) {
            val localDate = LocalDate.from(temporal)
            println("ZDT : $localDate")
            val kdate = KDateTime(localDate.atStartOfDay().toKotlinLocalDateTime().toInstant(TimeZone.UTC), TimeZone.UTC)
            println("KDATE : $kdate \n")
        } else {
            //TODO What should we do without date ?
            val localTime = LocalTime.from(temporal)
            println("ZDT : $localTime")
            val kdate = KDateTime.of(0, 1, 1, localTime.hour, localTime.minute, localTime.second, localTime.nano, TimeZone.UTC)
            println("KDATE : $kdate \n")
        }
//
//
//
//        println("DATE TIME : $dateTime")


//        val instant = offsetDateTime.toInstant()
//        println("INSTANT : $instant")
//
//        val zone = offsetDateTime.query(ZoneId::from)
//        println("ZONE : $zone")

        //val instant = jInstant.from(offsetDateTime).toKotlinInstant()

        KDateTime.now()

    } catch (e: DateTimeException) {
        println(e)
        //TODO Log this
        null
    }

}
