package es.exceptioncoders.thepersonaltrainerclub.view.typeSelection

import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter

enum class TypeSelection {
    Trainer,
    Client
}

class TypeSelectionFragmentPresenter(private val mNavigator: TypeSelectionFragmentContract.TypeSelectionViewNavigator<TypeSelectionFragmentContract.TypeSelectionView>) : BasePresenter<TypeSelectionFragmentContract.TypeSelectionView>(), TypeSelectionFragmentContract.TypeSelectionViewPresenter<TypeSelectionFragmentContract.TypeSelectionView> {
    override fun onTypeSelected(type: TypeSelection) {
        when (type) {
            TypeSelection.Trainer -> mNavigator.navigateToTrainerView()
            else -> mNavigator.navigateToClientView()
        }
    }
}