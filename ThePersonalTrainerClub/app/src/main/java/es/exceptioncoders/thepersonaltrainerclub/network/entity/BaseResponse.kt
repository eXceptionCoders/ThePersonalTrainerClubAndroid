package es.exceptioncoders.thepersonaltrainerclub.network.entity

open class BaseResponse (
    version: String,
    status: String,
    message: String,
    datetime: String,
    error: Any
)
