package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProvider
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider
import es.exceptioncoders.thepersonaltrainerclub.network.entity.TrainerClassResponse
import es.exceptioncoders.thepersonaltrainerclub.network.entity.UserResponse

interface TrainerManagementUseCase {
    fun getUser(model: UserModel, completion: (UserResponse?, UserProvider.UserError?) -> Unit)
    fun getClasses(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit)
}

class TrainerManagementUseCaseImp(private val userProvider: UserProvider, private val classProvider: ClassProvider): TrainerManagementUseCase {
    override fun getUser(model: UserModel, completion: (UserResponse?, UserProvider.UserError?) -> Unit) {
        userProvider.getUser(model, completion)
    }

    override fun getClasses(trainerId: String, completion: (TrainerClassResponse?, ClassProvider.ClassError?) -> Unit) {
        classProvider.getClassesForTrainer(trainerId, completion)
    }
}