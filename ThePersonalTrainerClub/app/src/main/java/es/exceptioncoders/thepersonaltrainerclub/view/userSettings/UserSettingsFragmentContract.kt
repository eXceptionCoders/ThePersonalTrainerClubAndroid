package es.exceptioncoders.thepersonaltrainerclub.view.userSettings

import android.graphics.Bitmap
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseContract

interface UserSettingsFragmentContract {
    interface View : BaseContract.BaseView {
        fun setUser(user: UserModel)
    }

    interface Presenter<V : View> : BaseContract.BasePresenter<V> {
        fun saveThumbnail(bitmap: Bitmap)
        fun fetchUser()
    }

    interface Navigator<V : View> : BaseContract.BaseNavigator<V>
}