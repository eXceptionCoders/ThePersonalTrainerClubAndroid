package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.LocationProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface AddLocationUseCase {
    fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit)
}

class AddLocationUseCaseImp(private val provider: LocationProvider, private val userProvider: UserProvider): AddLocationUseCase {
    override fun addLocation(model: LocationModel, completion: (Boolean, LocationProvider.Error?) -> Unit) {
        provider.addLocation(model) { success, error ->
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