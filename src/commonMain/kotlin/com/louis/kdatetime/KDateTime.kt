package com.louis.kdatetime

import kotlinx.datetime.*

class KDateTime (
    val instant: Instant,
    val zone: TimeZone
) {

    companion object {
        fun now(zone: TimeZone = TimeZone.currentSystemDefault()): KDateTime {
            val instant = Clock.System.now()
            return ofInstant(instant, zone)
        }

        fun nowZero(zone: TimeZone): KDateTime {
            return create(0L, 0, zone)
        }

        fun of(
            year: Int, month: Int, dayOfMonth: Int,
            hour: Int, minute: Int, second: Int,
            nanoOfSecond: Int, zone: TimeZone
        ): KDateTime {
            val dt = LocalDateTime(year, month, dayOfMonth, hour, minute, second, nanoOfSecond)
            return ofInstant(dt.toInstant(zone), zone)
        }

        fun create(epochSeconds: Long, nanoOfSecond: Int, zone: TimeZone): KDateTime {
            return ofInstant(Instant.fromEpochSeconds(epochSeconds, nanoOfSecond), zone)
        }

        fun ofInstant(instant: Instant, zone: TimeZone): KDateTime {
            return KDateTime(instant, zone)
        }

        fun parse(isoDate: String): KDateTime? {
            return parse(isoDate, ISO.ISO_ZONED_DATE_TIME)
        }
    }

    val offset: UtcOffset
        get() =  zone.offsetAt(instant)

    val dateTime: LocalDateTime
        get() = instant.toLocalDateTime(zone)

    // get fields
    val year: Int = dateTime.year
    val month: Month = dateTime.month
    val dayOfMonth: Int = dateTime.dayOfMonth
    val hour: Int = dateTime.hour
    val minute: Int = dateTime.minute
    val second: Int = dateTime.second
    val nano = dateTime.nanosecond

    fun isSameDay(other: KDateTime): Boolean {
        return instant.daysUntil(other.instant, zone) == 0
    }

    fun isSameYear(other: KDateTime): Boolean {
        return instant.yearsUntil(other.instant, zone) == 0
    }

    fun isBefore(other: KDateTime?): Boolean {
        return instant.toEpochMilliseconds() < other?.instant?.toEpochMilliseconds() ?: 0
    }

    fun isAfter(other: KDateTime?): Boolean {
        return instant.toEpochMilliseconds() > other?.instant?.toEpochMilliseconds() ?: 0
    }

    fun periodUntil(other: KDateTime): DateTimePeriod {
        return instant.periodUntil(other.instant, zone)
    }

    fun plus(value: Int, unit: KDateTimeUnit): KDateTime {
        return ofInstant(instant.plus(value, unit.toNative(), zone), zone)
    }

    fun minus(value: Int, unit: KDateTimeUnit): KDateTime {
        return ofInstant(instant.minus(value, unit.toNative(), zone), zone)
    }

    fun truncated(to: KDateTimeUnit): KDateTime {
        return when(to) {
            KDateTimeUnit.NANOSECOND -> truncatedNano()
            KDateTimeUnit.MICROSECOND -> truncatedMicro()
            KDateTimeUnit.MILLISECOND -> truncatedMilli()
            KDateTimeUnit.SECOND -> truncatedSecond()
            KDateTimeUnit.MINUTE -> truncatedMinute()
            KDateTimeUnit.HOUR -> truncatedHour()
            KDateTimeUnit.DAY -> truncatedDay()
            KDateTimeUnit.WEEK -> truncatedWeek()
            KDateTimeUnit.MONTH -> truncatedMonth()
            KDateTimeUnit.QUARTER -> truncatedQuarter()
            KDateTimeUnit.YEAR -> truncatedYear()
            KDateTimeUnit.CENTURY -> truncatedCentury()
        }
    }

    fun with(value: Int, unit: KDateTimeUnit): KDateTime {
        return when(unit) {
            KDateTimeUnit.NANOSECOND -> withNano(value)
            KDateTimeUnit.MICROSECOND -> withMicro(value)
            KDateTimeUnit.MILLISECOND -> withMilli(value)
            KDateTimeUnit.SECOND -> withSecond(value)
            KDateTimeUnit.MINUTE -> withHour(value)
            KDateTimeUnit.HOUR -> withHour(value)
            KDateTimeUnit.DAY -> withDay(value)
            KDateTimeUnit.MONTH -> withMonth(value)
            KDateTimeUnit.YEAR -> withYear(value)
            else -> error("$unit is not supported")
        }
    }

    fun format(pattern: String, withLocale: String): String {
        return format(instant, pattern, zone, withLocale)
    }

    fun format(formatter: DateFormatter, withLocale: String): String {
        return format(instant, formatter, zone, withLocale)
    }

    fun format(iso: ISO): String {
        return formatISO(instant, zone, iso)
    }

    override fun toString(): String {
        return formatISO(instant, zone, ISO.ISO_ZONED_DATE_TIME)
    }

    override fun equals(other: Any?): Boolean {
        return instant == (other as? KDateTime)?.instant ?: false
    }

    override fun hashCode(): Int {
        return instant.hashCode() + zone.hashCode()
    }
}