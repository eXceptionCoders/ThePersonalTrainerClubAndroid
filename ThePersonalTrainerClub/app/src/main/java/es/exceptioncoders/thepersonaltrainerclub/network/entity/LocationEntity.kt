package es.exceptioncoders.thepersonaltrainerclub.network.entity

class LocationEntity (
    val _id: String,
    val type: String,
    val description: String,
    val coordinates: Array<Float>
)

data class AddLocationRequest (
    val description: String,
    val longitude: Double,
    val latitude: Double
)

data class AddLocationResponse(
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
): BaseResponse(version, status, message, datetime, error)

