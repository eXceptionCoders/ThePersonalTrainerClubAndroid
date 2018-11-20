package es.exceptioncoders.thepersonaltrainerclub.view.register

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(), RegisterActivityContract.RegisterView {

    private lateinit var mPresenter: RegisterActivityContract.RegisterViewPresenter<RegisterActivity>

    override fun bindLayout(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = RegisterActivityNavigator() as RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>
        mNavigator.attachView(this)

        mPresenter = RegisterActivityPresenter(mNavigator) as RegisterActivityContract.RegisterViewPresenter<RegisterActivity>
        mPresenter.attachView(this)

        registerButton.setOnClickListener {
            mPresenter.onRegister(nameEditText.text.toString(),
                    surnameEditText.text.toString(),
                    emailEditText.text.toString(),
                    pwdEditText.text.toString(),
                    isTrainerCheckBox.isSelected
                    )
        }

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.app_bar_name)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun localizeView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
