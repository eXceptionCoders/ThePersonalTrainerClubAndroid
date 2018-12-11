package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.NewClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProvider

interface NewClassUseCase {
    fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit)
}

class NewClassUseCaseImp(private val provider: ClassProvider): NewClassUseCase {
    override fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit) {
        provider.create(model, completion)
    }
}