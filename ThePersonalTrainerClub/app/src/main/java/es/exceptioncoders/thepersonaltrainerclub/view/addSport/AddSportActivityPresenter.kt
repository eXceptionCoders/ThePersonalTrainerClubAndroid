package es.exceptioncoders.thepersonaltrainerclub.view.addSport

import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SetSportsModel
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import es.exceptioncoders.thepersonaltrainerclub.model.provider.ActivityProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetActivitiesProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.ActivityUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.ActivityUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetActivityUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetActivityUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

class AddSportActivityPresenter(private val mNavigator: AddSportActivityContract.Navigator<AddSportActivityContract.View>) : BasePresenter<AddSportActivityContract.View>(), AddSportActivityContract.Presenter<AddSportActivityContract.View> {
    private val activityUseCase: ActivityUseCase = ActivityUseCaseImp(ActivityProviderImp())
    private val setActivitiesUseCase: SetActivityUseCase = SetActivityUseCaseImp(SetActivitiesProviderImp(), UserProviderImp())

    override fun create() {
        mView?.showLoading()

        activityUseCase.getAllActivities { arrayOfSportModels, activityError ->
            mView?.hideLoading()

            activityError?.let {
                mView?.showAlertMessage(null, R.string.add_sport_get_activities_error)
            } ?: kotlin.run {
                mView?.showSports(arrayOfSportModels)
            }
        }
    }

    override fun saveSports(sports: List<SportModel>) {
        mView?.showLoading()

        var str: MutableList<String> = mutableListOf()

        for (sport in sports) {
            str.add(sport._id)
        }

        setActivitiesUseCase.setActivities(SetSportsModel(str)) { success, error ->
            mView?.hideLoading()

            error?.let {
                mView?.showAlertMessage(null, R.string.add_sport_error)
            } ?: run {
                mNavigator?.popBack()
            }
        }
    }
}