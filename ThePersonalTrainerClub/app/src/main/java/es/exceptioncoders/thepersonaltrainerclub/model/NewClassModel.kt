package es.exceptioncoders.thepersonaltrainerclub.model

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel

data class NewClassModel (
        val instructor: String,
        val sport: String,
        val location: LocationModel,
        val description: String,
        val price: Double,
        val maxusers: Int,
        val duration: Int
)