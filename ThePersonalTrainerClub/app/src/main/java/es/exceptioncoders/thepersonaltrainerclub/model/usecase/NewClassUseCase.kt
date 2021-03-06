package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.NewClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface NewClassUseCase {
    fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit)
}

class NewClassUseCaseImp(private val provider: ClassProvider, private val userProvider: UserProvider): NewClassUseCase {
    override fun create(model: NewClassModel, completion: (Boolean, ClassProvider.ClassError?) -> Unit) {
        provider.create(model) { success, error ->
            if (success) {
                userProvider.getUser { user, error ->
                    user?.let {
                        SharedApp.preferences.user = user

                        completion(true, null)
                    }
                }
            } else {
                completion(false, error)
            }
        }
    }
}