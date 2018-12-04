package es.exceptioncoders.thepersonaltrainerclub.model.model

data class ClassModel (
        val id: String,
        val sport: ActivityModel,
        val location: LocationModel,
        val description: String,
        val price: Float,
        val quota: Int,
        val duration: Int
)