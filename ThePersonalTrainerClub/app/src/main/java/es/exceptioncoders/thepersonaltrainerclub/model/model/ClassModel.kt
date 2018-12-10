package es.exceptioncoders.thepersonaltrainerclub.model.model

data class ClassModel (
        val id: String,
        val instructor: TrainerModel,
        val sport: SportModel,
        val location: LocationModel,
        val description: String,
        val price: Float,
        val maxusers: Int,
        val duration: Int,
        val registered: Int,
        val place: String,
        val date: String
)