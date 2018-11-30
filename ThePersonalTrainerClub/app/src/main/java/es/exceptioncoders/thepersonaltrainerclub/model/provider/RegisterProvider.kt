package es.exceptioncoders.thepersonaltrainerclub.model.provider

import es.exceptioncoders.thepersonaltrainerclub.model.model.RegisterModel
import es.exceptioncoders.thepersonaltrainerclub.network.Endpoint
import es.exceptioncoders.thepersonaltrainerclub.network.WebService
import es.exceptioncoders.thepersonaltrainerclub.network.WebServiceError
import es.exceptioncoders.thepersonaltrainerclub.network.entity.RegisterRequest
import es.exceptioncoders.thepersonaltrainerclub.network.entity.RegisterResponse
import java.time.format.DateTimeFormatter


interface RegisterProvider {
    enum class RegisterError {
        DuplicateError,
        IncorrectEntry,
        OtherError
    }

    fun register(model: RegisterModel, completion: (Boolean, RegisterError?) -> Unit)
}

class RegisterProviderImp: RegisterProvider {
    override fun register(model: RegisterModel, completion: (Boolean, RegisterProvider.RegisterError?) -> Unit) {
        // TODO: Find a way to do it for API < 26
        val requestModel = RegisterRequest(
                model.name,
                model.lastname,
                model.gender.value,
                model.email,
                model.password,
                model.birthday.format(DateTimeFormatter.ISO_DATE_TIME),
                model.isTrainer
        )

        val endpoint = Endpoint(Endpoint.EndpointType.Register(requestModel))

        val ws = WebService()

        ws.load<RegisterResponse>(endpoint) { response: RegisterResponse?, e: WebServiceError? ->
            var error: RegisterProvider.RegisterError? = null
            var registered = false
            e?.let {
                error = when (it) {
                    WebServiceError.DuplicateError -> RegisterProvider.RegisterError.DuplicateError
                    WebServiceError.UnprocessableEntity -> RegisterProvider.RegisterError.IncorrectEntry
                    else -> RegisterProvider.RegisterError.OtherError
                }
            } ?: kotlin.run {
                registered = true
            }

            completion(registered, error)
        }
    }
}