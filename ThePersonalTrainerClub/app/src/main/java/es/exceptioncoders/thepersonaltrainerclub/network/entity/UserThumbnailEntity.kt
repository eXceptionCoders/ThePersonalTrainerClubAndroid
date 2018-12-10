package es.exceptioncoders.thepersonaltrainerclub.network.entity

import java.io.File

data class SetUserThumbnailRequest (
    val image: ByteArray,
    val file: File,
    val cacheDir: File
)

data class SetUserThumbnailResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
): BaseResponse(version, status, message, datetime, error)