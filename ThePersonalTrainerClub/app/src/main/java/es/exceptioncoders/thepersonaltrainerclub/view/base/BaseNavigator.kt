package es.exceptioncoders.thepersonaltrainerclub.view.base

abstract class BaseNavigator<T : BaseContract.BaseView>: BaseContract.BaseNavigator<T> {

    protected var mView: T? = null

    override fun attachView(view: T) {
        mView = view
    }
}