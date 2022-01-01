package com.louis.kdatetime

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun getCurrentLocaleString() = NSLocale.currentLocale.languageCode
