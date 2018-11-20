package es.exceptioncoders.thepersonaltrainerclub.view.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseContract.BaseView {

    protected abstract fun bindLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutInflater.inflate(bindLayout(), null))

        localizeView()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showAlertMessage(title: Int?, message: Int) {
        val builder = AlertDialog.Builder(this)

        title?.let {
            builder.setTitle(resources.getString(title))
        }

        builder.setMessage(resources.getString(message))

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    protected abstract fun localizeView()
}