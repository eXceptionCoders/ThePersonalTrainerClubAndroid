package es.exceptioncoders.thepersonaltrainerclub.view.base

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseContract.BaseView {

    protected abstract fun bindLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutInflater.inflate(bindLayout(), null))
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showAlertMessage(title: String?, message: String) {
        val builder = AlertDialog.Builder(this)

        title?.let {
            builder.setTitle(it)
        }

        builder.setMessage(message)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}