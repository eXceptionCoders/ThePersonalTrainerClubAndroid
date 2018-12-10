package es.exceptioncoders.thepersonaltrainerclub.network.entity

data class SetUserThumbnailRequest (
    val image: ByteArray
)

data class SetUserThumbnailResponse (
        val version: String,
        val status: String,
        val message: String,
        val datetime: String,
        val error: Any
): BaseResponse(version, status, message, datetime, error)