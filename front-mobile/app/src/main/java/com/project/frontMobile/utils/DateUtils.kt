package com.project.frontMobile.utils

import java.text.DateFormatSymbols

class DateUtils {

    fun formatDate(year: Int, month: Int, day: Int): String {
        val m = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
        val d = if (day < 10) "0$day" else "$day"
        return "$year-$m-$d"
    }

    fun formatBirthday(date: String): String {
        val year = extractYear(date)
        val month = extractMonth(date)
        val monthName = DateFormatSymbols.getInstance().months[Integer.parseInt(month) -1]
        val day = extractDayOfMonth(date)

        return "$day $monthName $year"
    }

    fun extractYear(date: String): String {
        return date.substring(0, 4)
    }

    fun extractMonth(date: String): String {
        return date.substring(5, 7)
    }

    fun extractDayOfMonth(date: String): String {
        return date.substring(8, 10)
    }
}