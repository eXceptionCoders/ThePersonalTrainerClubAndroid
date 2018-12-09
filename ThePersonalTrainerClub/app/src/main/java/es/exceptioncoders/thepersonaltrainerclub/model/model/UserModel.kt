package es.exceptioncoders.thepersonaltrainerclub.model.model


data class UserModel (
    val id: String = "",
    val name: String = "",
    val lastname: String ="",
    val coach: Boolean = false,
    val gender: String = "",
    val thumbnail: String = "",
    val email: String = "",
    val locations: Array<LocationModel> = emptyArray<LocationModel>(),
    val activities: Array<SportModel> = emptyArray<SportModel>()
)
