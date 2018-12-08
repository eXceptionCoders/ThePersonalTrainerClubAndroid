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
        val data: Array<SportEntity>
) : BaseResponse(version, status, message, datetime, error)

data class SetSportRequest (
        val sports: Array<String>
)

data class SetSportResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
): BaseResponse(version, status, message, datetime, error)