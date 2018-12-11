package es.exceptioncoders.thepersonaltrainerclub.network.entity

class NewClassRequest (
        val instructor: String,
        val sport: String,
        val location: LocationEntity,
        val description: String,
        val price: Double,
        val duration: Int,
        val maxusers: Int
)

data class ClassEntity (
        val _id: String,
        val instructor: UserEntity,
        val sport: SportEntity,
        val location: LocationEntity,
        val description: String,
        val place: String,
        val price: Double,
        val maxusers: Int,
        val duration: Int,
        val registered: Int?,
        val distance: Double?
)

data class NewClassResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
) : BaseResponse(version, status, message, datetime, error)