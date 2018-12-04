package es.exceptioncoders.thepersonaltrainerclub.network.entity

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel

class TrainerClassRequest (
    val id: String
)

class TrainerClassEntity (
    val _id: String,
    val sport: SportEntity,
    val location: LocationEntity,
    val description: String,
    val price: Float,
    val duration: Int,
    val quota: Int
)

data class TrainerClassResponse (
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: TrainerClassEntity
) : BaseResponse(version, status, message, datetime, error)