package es.exceptioncoders.thepersonaltrainerclub.model.model

import java.io.Serializable

data class TrainerModel (
    val id: String,
    val name: String,
    val lastname: String,
    val thumbnail: String?
) : Serializable
