package es.exceptioncoders.thepersonaltrainerclub.model.model

data class RegisterModel (
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val isTrainer: Boolean
)