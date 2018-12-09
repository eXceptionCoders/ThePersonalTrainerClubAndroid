package es.exceptioncoders.thepersonaltrainerclub.model.model


data class UserModel (
        val id: String,
        val name: String,
        val lastname: String,
        val coach: Boolean,
        val gender: String,
        val thumbnail: String,
        val email: String,
        val locations: Array<LocationModel>,
        val activities: Array<SportModel>
)
