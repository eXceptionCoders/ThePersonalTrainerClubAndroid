package es.exceptioncoders.thepersonaltrainerclub.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {
    companion object {
        val DATE_FORMAT: String = "dd/MM/yyyy"

        fun convertStringToLocalDateTime(dateString: String): LocalDateTime? {
            // TODO: Find a way to do it for API < 26
            val formatter = DateTimeFormatter.ofPattern("$DATE_FORMAT HH:mm:ss.SSS", Locale.US)

            try {
                // TODO: Find a way to do it for API < 26
                return LocalDateTime.parse("$dateString 00:00:00.000", formatter)
            } catch (e: Exception) {
                return null
            }
        }

        fun convertDateToString(date: Date): String {
            return SimpleDateFormat(DATE_FORMAT, Locale.US).format(date)
        }
    }
}