package es.exceptioncoders.thepersonaltrainerclub.view.classDetail

import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseNavigator

class ClassDetailActivityNavigator : BaseNavigator<ClassDetailActivityContract.View>(), ClassDetailActivityContract.Navigator<ClassDetailActivityContract.View> {
    override fun popBack() {
        (mView as ClassDetailActivity).finish()
    }
}
