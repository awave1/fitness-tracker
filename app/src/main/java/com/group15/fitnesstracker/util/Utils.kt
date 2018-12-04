package com.group15.fitnesstracker.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun formatDate(date: Date?, format: String = "dd/MM/yyyy HH:mm") = SimpleDateFormat(format).format(date)
}