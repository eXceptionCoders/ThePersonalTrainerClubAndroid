package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.SetSportsModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetActivitiesProvider

interface SetActivityUseCase {
    fun setActivities(model: SetSportsModel, completion: (Boolean, SetActivitiesProvider.SetActivitiesError?) -> Unit)
}

class SetActivityUseCaseImp(private val provider: SetActivitiesProvider): SetActivityUseCase {
    override fun setActivities(model: SetSportsModel, completion: (Boolean, SetActivitiesProvider.SetActivitiesError?) -> Unit) {
        provider.setSports(model, completion)
    }
}