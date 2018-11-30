package es.exceptioncoders.thepersonaltrainerclub.network.entity

data class RegisterRequest (
    val name: String,
    val lastname: String,
    val gender: String,
    val email: String,
    val password: String,
    val birthday: String,
    val isTrainer: Boolean
)