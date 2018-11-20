package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.RegisterModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.RegisterProvider


interface RegisterUseCase {
    fun register(model: RegisterModel, completion: (Boolean, RegisterProvider.RegisterError?) -> Unit)
}

class RegisterUseCaseImp(private val provider: RegisterProvider): RegisterUseCase {
    override fun register(model: RegisterModel, completion: (Boolean, RegisterProvider.RegisterError?) -> Unit) {
        provider.register(model, completion)
    }
}