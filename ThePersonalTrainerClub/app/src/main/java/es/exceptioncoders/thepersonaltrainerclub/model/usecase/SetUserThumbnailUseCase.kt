package es.exceptioncoders.thepersonaltrainerclub.model.usecase

import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetThumbnailProvider
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetUserThumbnailRequest

interface SetUserThumbnailUseCase {
    fun setThumbnail(model:SetUserThumbnailRequest, completion: (Boolean, SetThumbnailProvider.Error?) -> Unit)
}

class SetUserThumbnailUseCaseImp(private val provider: SetThumbnailProvider): SetUserThumbnailUseCase {
    override fun setThumbnail(model:SetUserThumbnailRequest, completion: (Boolean, SetThumbnailProvider.Error?) -> Unit) {
        provider.setThumbnail(model, completion)
    }
}