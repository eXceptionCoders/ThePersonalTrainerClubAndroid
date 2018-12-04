package es.exceptioncoders.thepersonaltrainerclub.model.model

data class LocationModel(
        val type: String,
        val coordinates: Array<Float>,
        val description: String
)