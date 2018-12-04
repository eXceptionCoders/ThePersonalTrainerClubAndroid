package es.exceptioncoders.thepersonaltrainerclub.network.entity

class SportEntity (
    val _id: String,
    val name: String,
    // val category: String?
    val icon: String?
)

data class SportResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any,
        val data: SportEntity
) : BaseResponse(version, status, message, datetime, error)