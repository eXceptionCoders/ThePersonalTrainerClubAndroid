package es.exceptioncoders.thepersonaltrainerclub.model.model

import java.io.Serializable

data class LocationModel(
        val id: String?,
        val type: String,
        val coordinates: Array<Double>,
        val description: String?
): Serializable