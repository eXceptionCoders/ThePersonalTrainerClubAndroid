package es.exceptioncoders.thepersonaltrainerclub.utils

import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {
    companion object {
        val ISODATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val DATETIME_FORMAT: String = "dd/MM/yyyy HH:mm"

        fun convertStringToLocalDateTime(dateString: String): LocalDateTime? {
            // TODO: Find a way to do it for API < 26
            val formatter = DateTimeFormatter.ofPattern(ISODATE_FORMAT, Locale.US)

            try {
                // TODO: Find a way to do it for API < 26
                return LocalDateTime.parse(dateString, formatter)
            } catch (e: Exception) {
                return null
            }
        }

        fun convertDateToString(date: LocalDateTime, dateFormat: String): String {
            try {
                val formatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT, Locale.US)
                return date.format(formatter)
            } catch (e: Exception) {
                return ""
            }
        }
    }
}