package es.exceptioncoders.thepersonaltrainerclub.view.base

abstract class BasePresenter<T : BaseContract.BaseView>: BaseContract.BasePresenter<T> {

    protected var mView: T? = null

    override fun attachView(view: T) {
        mView = view
    }
}