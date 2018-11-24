package es.exceptioncoders.thepersonaltrainerclub.view.typeSelectionDashboard

import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

enum class TypeSelection {
    Trainer,
    Client
}

class TypeSelectionActivityPresenter(private val mNavigator: TypeSelectionActivityContract.TypeSelectionViewNavigator<TypeSelectionActivityContract.TypeSelectionView>) : BasePresenter<TypeSelectionActivityContract.TypeSelectionView>(), TypeSelectionActivityContract.TypeSelectionViewPresenter<TypeSelectionActivityContract.TypeSelectionView> {
    override fun onTypeSelected(type: TypeSelection) {
        when (type) {
            TypeSelection.Trainer -> mNavigator.navigateToTrainerView()
            else -> mNavigator.navigateToClientView()
        }
    }
}