package es.exceptioncoders.thepersonaltrainerclub.view.base

interface BaseContract {

    interface BaseView {
        fun showLoading()
        fun hideLoading()
        fun showAlertMessage(title: Int?, message: Int)
    }

    interface BasePresenter<in T : BaseView> {
        fun attachView(view: T)
    }

    interface BaseNavigator<in T: BaseView> {
        fun attachView(view: T)
    }
}