package es.exceptioncoders.thepersonaltrainerclub.view.userSettings

import android.app.Fragment
import android.graphics.Bitmap
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.provider.SetThumbnailProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.provider.UserProviderImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.GetUserUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.GetUserUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetUserThumbnailUseCase
import es.exceptioncoders.thepersonaltrainerclub.model.usecase.SetUserThumbnailUseCaseImp
import es.exceptioncoders.thepersonaltrainerclub.network.entity.SetUserThumbnailRequest
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BasePresenter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class UserSettingsFragmentPresenter(private val mNavigator: UserSettingsFragmentContract.Navigator<UserSettingsFragmentContract.View>) : BasePresenter<UserSettingsFragmentContract.View>(), UserSettingsFragmentContract.Presenter<UserSettingsFragmentContract.View> {
    val useCase: GetUserUseCase = GetUserUseCaseImp(UserProviderImp())
    val thumbnailUseCase: SetUserThumbnailUseCase = SetUserThumbnailUseCaseImp(SetThumbnailProviderImp())

    override fun fetchUser() {
        mView?.showLoading()

        mView?.setUser(SharedApp.preferences.user!!)

        useCase.fetchUser { userModel, userError ->
            mView?.hideLoading()

            userError?.let {
                mView?.showAlertMessage(null, R.string.user_setting_server_error)
                return@fetchUser
            }

            userModel?.let {
                mView?.setUser(it)
            } ?: run {
                mView?.showAlertMessage(null, R.string.user_setting_server_error)
            }
        }
    }

    override fun saveThumbnail(bitmap: Bitmap) {
        mView?.showLoading()
        //create a file to write bitmap data
        val f = File((mView!! as UserSettingsFragment).activity!!.cacheDir, "user-profile")
        f.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos)
        val bitmapdata = bos.toByteArray()

        //write the bytes in file
        val fos = FileOutputStream(f)
        fos.write(bitmapdata)
        fos.flush()
        fos.close()

        thumbnailUseCase.setThumbnail(SetUserThumbnailRequest(bitmapdata)) { success, error ->
            mView?.hideLoading()
            print("")
        }
    }
}