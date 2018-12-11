package es.exceptioncoders.thepersonaltrainerclub.network.entity

class FindClassRequest (
        val sport: String,
        val longitude: Double,
        val latitude: Double,
        val distance: Int,
        val price: String,
        val page: Int,
        val perPage: Int
)

data class FindClassResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val data: Array<ClassEntity>?,
        val total: Int?,
        val error: Any
) : BaseResponse(version, status, message, datetime, error)