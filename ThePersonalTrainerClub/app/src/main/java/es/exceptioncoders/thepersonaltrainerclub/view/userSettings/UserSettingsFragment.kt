package es.exceptioncoders.thepersonaltrainerclub.view.userSettings

import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.UserModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_settings.*
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.widget.Toast
import java.io.File


class UserSettingsFragment : BaseFragment(), UserSettingsFragmentContract.View {

    companion object {
        private val FINAL_TAKE_PHOTO = 1
        private val FINAL_CHOOSE_PHOTO = 2
    }

    private lateinit var mPresenter: UserSettingsFragmentContract.Presenter<UserSettingsFragment>
    private var imageUri: Uri? = null

    override fun bindLayout(): Int = R.layout.fragment_user_settings

    override fun localizeView() {
        (changePicture as Button).text = resources.getString(R.string.user_settings_change_photo)
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.INVISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mNavigator = UserSettingsFragmentNavigator() as UserSettingsFragmentContract.Navigator<UserSettingsFragmentContract.View>
        mNavigator.attachView(this)

        mPresenter = UserSettingsFragmentPresenter(mNavigator) as UserSettingsFragmentContract.Presenter<UserSettingsFragment>
        mPresenter.attachView(this)

        (changePicture as Button).setOnClickListener {
            select()
        }

        mPresenter.fetchUser()
    }

    override fun setUser(user: UserModel) {
        if (user.coach && user.showCoachView) {
            userTypeText.text = resources.getString(R.string.user_settings_type_trainer)
        } else {
            userTypeText.text = resources.getString(R.string.user_settings_type_athlete)
        }

        userName.text = user.name + " " + user.lastname

        if (user.thumbnail.isNotBlank() && user.thumbnail.isNotEmpty()) {
            Picasso.get().load(user.thumbnail).into(thumbnail)
        }
    }

    private fun select() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(resources.getString(R.string.user_settings_choose_source_image))
                .setCancelable(true)
                .setPositiveButton(resources.getString(R.string.user_settings_source_gallery)) { dialog, id -> takePictureFromGallery() }
                .setNegativeButton(resources.getString(R.string.user_settings_source_camera)) { dialog, id -> takePictureFromCamera() }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun takePictureFromGallery() {
        val checkSelfPermission = ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
        else{
            openAlbum()
        }
    }

    private fun takePictureFromCamera() {
        val outputImage = File(activity!!.externalCacheDir, "output_image.jpg")
        if(outputImage.exists()) {
            outputImage.delete()
        }
        outputImage.createNewFile()
        imageUri = if(Build.VERSION.SDK_INT >= 24){
            FileProvider.getUriForFile(activity!!, "es.exceptioncoders.thepersonaltrainerclub.fileprovider", outputImage)
        } else {
            Uri.fromFile(outputImage)
        }

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, FINAL_TAKE_PHOTO)
    }

    private fun openAlbum(){
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, FINAL_CHOOSE_PHOTO)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 ->
                if (grantResults.isNotEmpty() && grantResults.get(0) ==PackageManager.PERMISSION_GRANTED){
                    openAlbum()
                }
                else {
                    Toast.makeText(activity!!, "You denied the permission", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            FINAL_TAKE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    val bitmap = BitmapFactory.decodeStream(activity!!.contentResolver.openInputStream(imageUri))

                    //TODO: Save new thumbnail
                    thumbnail.setImageBitmap(bitmap)
                    mPresenter.saveThumbnail(bitmap)
                }
            FINAL_CHOOSE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    handleImage(data)
                }
        }
    }

    private fun handleImage(data: Intent?) {
        var imagePath: String? = null
        val uri = data!!.data
        if (DocumentsContract.isDocumentUri(activity!!, uri)){
//            document类型的Uri，用document id处理
            val docId = DocumentsContract.getDocumentId(uri)
            if ("com.android.providers.media.documents" == uri.authority){
                val id = docId.split(":")[1]
                val selsetion = MediaStore.Images.Media._ID + "=" + id
                imagePath = imagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selsetion)
            }
            else if ("com.android.providers.downloads.documents" == uri.authority){
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(docId))
                imagePath = imagePath(contentUri, null)
            }
        }
        else if ("content".equals(uri.scheme, ignoreCase = true)){
            imagePath = imagePath(uri, null)
        }
        else if ("file".equals(uri.scheme, ignoreCase = true)){
            imagePath = uri.path
        }

        displayImage(imagePath)
    }

    private fun imagePath(uri: Uri?, selection: String?): String {
        var path: String? = null
//        通过Uri和selection获取路径
        val cursor = activity!!.contentResolver.query(uri, null, selection, null, null )
        if (cursor != null){
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path!!
    }

    private fun displayImage(imagePath: String?){
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)

            thumbnail.setImageBitmap(bitmap)
        }
        else {
            Toast.makeText(activity!!, "Failed to get image", Toast.LENGTH_SHORT).show()
        }
    }

}
