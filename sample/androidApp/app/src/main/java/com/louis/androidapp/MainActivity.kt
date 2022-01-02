package com.louis.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.louis.kdatetime.*
import kotlinx.datetime.TimeZone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dateTime = KDateTime.now(TimeZone.currentSystemDefault())
        val formatter = KDateTimeFormatter(Format.DATE_TIME, DateTimeStyle.MEDIUM)
        val dateTimeFormatted = dateTime.format(formatter, getCurrentLocaleString())
        findViewById<TextView>(R.id.text_view).text = dateTimeFormatted
    }
}