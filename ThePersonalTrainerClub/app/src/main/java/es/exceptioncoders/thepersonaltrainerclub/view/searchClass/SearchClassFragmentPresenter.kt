package es.exceptioncoders.thepersonaltrainerclub.view.searchClass

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ClassProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.FindClassUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.FindClassUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class SearchClassFragmentPresenter(private val mNavigator: SearchClassFragmentContract.Navigator<SearchClassFragmentContract.View>) : BasePresenter<SearchClassFragmentContract.View>(), SearchClassFragmentContract.Presenter<SearchClassFragmentContract.View> {

    val useCase: FindClassUseCase = FindClassUseCaseImp(ClassProviderImp())

    override fun fetchUser() {
        mView?.setUser(SharedApp.preferences.user!!)
    }

    override fun onSearch(sport: SportModel, location: LocationModel, distance: Int, maxPrice: Int) {
        mView?.showLoading()

        var mp = ""
        if(maxPrice > 0) {
            mp = maxPrice.toString()
        }

        useCase.find(sport._id, location, distance, mp, 0, 20) { classes, error ->
            mView?.hideLoading()

            error?.let {
                mView?.showAlertMessage(null, R.string.search_class_search_error)
            } ?: kotlin.run {

                classes?.let {
                    if (it.total > 0) {
                        mNavigator.navigateToSerachClassResult(ArrayList(it.classes.toList()))
                    } else {
                        mView?.showAlertMessage(null, R.string.search_class_no_classes_found)
                    }
                } ?: run {
                    mView?.showAlertMessage(null, R.string.search_class_no_classes_found)
                }

                mView?.resetInputs()
            }
        }
    }
}