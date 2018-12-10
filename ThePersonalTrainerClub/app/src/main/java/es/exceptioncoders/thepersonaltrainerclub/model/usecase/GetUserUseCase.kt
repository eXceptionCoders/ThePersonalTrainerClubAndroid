package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProvider

interface GetUserUseCase {
    fun fetchUser(completion: (UserModel?, UserProvider.UserError?) -> Unit)
}

class GetUserUseCaseImp(private val provider: UserProvider): GetUserUseCase {
    override fun fetchUser(completion: (UserModel?, UserProvider.UserError?) -> Unit) {
        provider.getUser(completion)
    }
}