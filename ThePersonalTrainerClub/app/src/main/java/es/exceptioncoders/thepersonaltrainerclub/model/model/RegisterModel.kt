package es.exceptioncoders.thepersonaltrainerclub.model.model

enum class GenderType(val value: String) {
    MALE("male"),
    FEMALE("female")
}

data class RegisterModel (
        val name: String,
        val lastname: String,
        val gender: GenderType,
        val email: String,
        val password: String,
        val isTrainer: Boolean
)