package es.exceptioncoders.thepersonaltrainerclub.network.entity

import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel

class UserEntity (
        val _id: String,
        val name: String,
        val lastname: String,
        val coach: Boolean,
        val gender: String,
        val thumbnail: String,
        val email: String,
        val locations: Array<LocationEntity>,
        val sports: Array<SportEntity>,
        val classes: Array<ClassEntity>,
        val activeBookings: Array<ClassEntity>
)

data class UserResponse (
    val version: String,
    val status: String,
    val message: String,
    val datetime: String,
    val error: Any,
    val data: UserEntity
) : BaseResponse(version, status, message, datetime, error)