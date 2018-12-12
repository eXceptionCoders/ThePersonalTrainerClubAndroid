package es.exceptioncoders.thepersonaltrainerclub.network.entity

class BookClassRequest (
        val classValue: String
)

data class BookClassResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
) : BaseResponse(version, status, message, datetime, error)