package es.exceptioncoders.thepersonaltrainerclub.view.typeSelectionDashboard

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface TypeSelectionActivityContract {
    interface TypeSelectionView : BaseContract.BaseView

    interface TypeSelectionViewPresenter<V : TypeSelectionView> : BaseContract.BasePresenter<V> {
        fun onTypeSelected(type: TypeSelection)
    }

    interface TypeSelectionViewNavigator<V : TypeSelectionView> : BaseContract.BaseNavigator<V> {
        fun navigateToTrainerView()
        fun navigateToClientView()
    }
}