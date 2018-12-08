package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.SetSportsModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetActivitiesProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp

interface SetActivityUseCase {
    fun setActivities(model: SetSportsModel, completion: (Boolean, SetActivitiesProvider.SetActivitiesError?) -> Unit)
}

class SetActivityUseCaseImp(private val provider: SetActivitiesProvider, private val userProvider: UserProvider): SetActivityUseCase {
    override fun setActivities(model: SetSportsModel, completion: (Boolean, SetActivitiesProvider.SetActivitiesError?) -> Unit) {
        provider.setSports(model) { success, error ->
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