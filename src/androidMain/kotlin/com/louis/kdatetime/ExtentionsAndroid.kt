package com.louis.kdatetime

import android.annotation.SuppressLint
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@SuppressLint("NewApi")
fun DateTimeStyle.toJavaFormatStyle(): FormatStyle = when (this) {
    DateTimeStyle.FULL -> FormatStyle.FULL
    DateTimeStyle.LONG -> FormatStyle.LONG
    DateTimeStyle.MEDIUM -> FormatStyle.MEDIUM
    DateTimeStyle.SHORT -> FormatStyle.SHORT
}

@SuppressLint("NewApi")
fun DateFormatter.toJavaDateTimeFormatter(): DateTimeFormatter = when (format) {
    Format.DATE -> DateTimeFormatter.ofLocalizedDate(dateStyle.toJavaFormatStyle())
    Format.TIME -> DateTimeFormatter.ofLocalizedTime(timeStyle.toJavaFormatStyle())
    Format.DATE_TIME -> DateTimeFormatter.ofLocalizedDateTime(dateStyle.toJavaFormatStyle(), timeStyle.toJavaFormatStyle())
}

@SuppressLint("NewApi")
fun ISO.toJavaDateTimeIso(): DateTimeFormatter = when(this) {
    ISO.BASIC_ISO_DATE -> DateTimeFormatter.BASIC_ISO_DATE
    ISO.ISO_DATE -> DateTimeFormatter.ISO_DATE
    ISO.ISO_DATE_TIME -> DateTimeFormatter.ISO_DATE_TIME
    ISO.ISO_INSTANT -> DateTimeFormatter.ISO_INSTANT
    ISO.ISO_LOCAL_DATE -> DateTimeFormatter.ISO_LOCAL_DATE
    ISO.ISO_LOCAL_DATE_TIME -> DateTimeFormatter.ISO_LOCAL_DATE_TIME
    ISO.ISO_LOCAL_TIME -> DateTimeFormatter.ISO_LOCAL_TIME
    ISO.ISO_OFFSET_DATE -> DateTimeFormatter.ISO_OFFSET_DATE
    ISO.ISO_OFFSET_DATE_TIME -> DateTimeFormatter.ISO_OFFSET_DATE_TIME
    ISO.ISO_OFFSET_TIME -> DateTimeFormatter.ISO_OFFSET_TIME
    ISO.ISO_ORDINAL_DATE -> DateTimeFormatter.ISO_ORDINAL_DATE
    ISO.ISO_TIME -> DateTimeFormatter.ISO_TIME
    ISO.ISO_WEEK_DATE -> DateTimeFormatter.ISO_WEEK_DATE
    ISO.ISO_ZONED_DATE_TIME -> DateTimeFormatter.ISO_ZONED_DATE_TIME
    ISO.RFC_1123_DATE_TIME -> DateTimeFormatter.RFC_1123_DATE_TIME
}
