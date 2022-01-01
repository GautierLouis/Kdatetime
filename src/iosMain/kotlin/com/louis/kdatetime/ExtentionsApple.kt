package com.louis.kdatetime

import platform.Foundation.*


fun DateTimeStyle.toNSDateFormatter() = when(this) {
    DateTimeStyle.FULL -> NSDateFormatterFullStyle
    DateTimeStyle.LONG -> NSDateFormatterLongStyle
    DateTimeStyle.MEDIUM -> NSDateFormatterMediumStyle
    DateTimeStyle.SHORT -> NSDateFormatterShortStyle
}

fun ISO.toNSISO(): NSISO8601DateFormatter {
    //TODO make that compatible
    return NSISO8601DateFormatter()
}
