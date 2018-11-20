package es.exceptioncoders.thepersonaltrainerclub.network.entity

data class RegisterRequest (
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val isTrainer: Boolean
)