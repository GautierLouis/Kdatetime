package com.louis.kdatetime

data class KDateTimeFormatter(val format: Format, val style: DateTimeStyle) {
    //Use this if with different style between date and time
    //Only used when format == DATE_TIME
    var dateStyle = style
    var timeStyle = style
}

enum class Format {
    DATE,
    TIME,
    DATE_TIME
}

enum class DateTimeStyle {
    FULL,
    LONG,
    MEDIUM,
    SHORT
}

enum class ISO {
    BASIC_ISO_DATE,
    ISO_DATE,
    ISO_DATE_TIME,
    ISO_INSTANT,
    ISO_LOCAL_DATE,
    ISO_LOCAL_DATE_TIME,
    ISO_LOCAL_TIME,
    ISO_OFFSET_DATE,
    ISO_OFFSET_DATE_TIME,
    ISO_OFFSET_TIME,
    ISO_ORDINAL_DATE,
    ISO_TIME,
    ISO_WEEK_DATE,
    ISO_ZONED_DATE_TIME,
    RFC_1123_DATE_TIME,
}