package es.exceptioncoders.thepersonaltrainerclub.view.newClass

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.NewClassModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.NewClassUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.NewClassUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class NewClassFragmentPresenter(private val mNavigator: NewClassFragmentContract.Navigator<NewClassFragmentContract.View>) : BasePresenter<NewClassFragmentContract.View>(), NewClassFragmentContract.Presenter<NewClassFragmentContract.View> {

    val useCase: NewClassUseCase = NewClassUseCaseImp(ClassProviderImp())

    override fun onCreate(sport: SportModel, location: LocationModel, description: String, price: Double, quota: Int) {
        val model = NewClassModel(
                SharedApp.preferences.user!!.id,
                sport._id,
                location,
                description,
                price.toFloat(),
                quota,
                30
        )

        mView?.showLoading()

        useCase.create(model) { success, error ->
            mView?.hideLoading()

            error?.let {
                mView?.showAlertMessage(null, R.string.new_class_other_error)
            } ?: kotlin.run {
                mView?.showAlertMessage(null, R.string.new_class_success)
                mView?.resetInputs()
            }
        }
    }

    override fun fetchUser() {
        mView?.setUser(SharedApp.preferences.user!!)
    }
}