package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import es.exceptioncoders.thepersonaltrainerclub.model.model.SetSportsModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ActivityProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetActivitiesProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.ActivityUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.ActivityUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetActivityUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetActivityUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class AddSportActivityPresenter(private val mNavigator: AddSportActivityContract.Navigator<AddSportActivityContract.View>) : BasePresenter<AddSportActivityContract.View>(), AddSportActivityContract.Presenter<AddSportActivityContract.View> {

    val activityUseCase: ActivityUseCase = ActivityUseCaseImp(ActivityProviderImp())
    val setActivitiesUseCase: SetActivityUseCase = SetActivityUseCaseImp(SetActivitiesProviderImp())

    override fun create() {
        mView?.showLoading()

        activityUseCase.getAllActivities { arrayOfSportModels, activityError ->

        }
    }

    override fun saveSports() {
        mView?.showLoading()

        var str = ""

        setActivitiesUseCase.setActivities(SetSportsModel(str)) { success, error ->
            //TODO: Check error
            mView?.hideLoading()
            mNavigator?.popBack()
        }
    }
}