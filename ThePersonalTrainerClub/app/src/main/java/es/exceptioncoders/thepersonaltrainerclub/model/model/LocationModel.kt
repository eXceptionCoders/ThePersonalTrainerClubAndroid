package es.exceptioncoders.thepersonaltrainerclub.model.model

data class LocationModel(
        val id: String?,
        val type: String,
        val coordinates: Array<Double>,
        val description: String?
)