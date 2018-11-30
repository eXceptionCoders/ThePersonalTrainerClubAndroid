package es.exceptioncoders.thepersonaltrainerclub.network.entity

data class RegisterResponse(
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
): BaseResponse(version, status, message, datetime, error)