package es.exceptioncoders.thepersonaltrainerclub.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(), BaseContract.BaseView {

    protected abstract fun bindLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(bindLayout(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        localizeView()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showAlertMessage(title: Int?, message: Int) {
        val builder = AlertDialog.Builder(activity!!.baseContext)

        title?.let {
            builder.setTitle(resources.getString(title))
        }

        builder.setMessage(resources.getString(message))

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    protected abstract fun localizeView()
}