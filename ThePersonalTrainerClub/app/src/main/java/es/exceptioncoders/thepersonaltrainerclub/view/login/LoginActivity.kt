package es.exceptioncoders.thepersonaltrainerclub.view.login

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.utils.SharedApp
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginActivityContract.View {
    private lateinit var mPresenter: LoginActivityContract.Presenter<LoginActivity>

    override fun bindLayout(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = LoginActivityNavigator() as LoginActivityContract.Navigator<LoginActivityContract.View>
        mNavigator.attachView(this)

        SharedApp.preferences.user?.let {
            mNavigator.navigateToDashboardActivity()
            return
        }

        mPresenter = LoginActivityPresenter(mNavigator) as LoginActivityContract.Presenter<LoginActivity>
        mPresenter.attachView(this)

        mPresenter.onCreate()

        loginButton?.setOnClickListener {
            mPresenter.onLogin((emailEditText?.text ?: "").toString(), (passwordEditText?.text ?: "").toString())
        }

        registerButton?.setOnClickListener {
            mPresenter.onRegister()
        }

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.app_bar_name)
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE
        emailEditText?.isEnabled = false
        passwordEditText?.isEnabled = false
        (loginButton as? Button)?.isEnabled = false
        (registerButton as? Button)?.isEnabled = false
    }

    override fun hideLoading() {
        progressBar?.visibility = View.INVISIBLE
        emailEditText?.isEnabled = true
        passwordEditText?.isEnabled = true
        (loginButton as? Button)?.isEnabled = true
        (registerButton as? Button)?.isEnabled = true
    }

    override fun localizeView() {
        emailEditText?.hint = resources.getString(R.string.login_email_placeholder)
        passwordEditText?.hint = resources.getString(R.string.login_password_placeholder)
        (loginButton as? Button)?.text =  resources.getString(R.string.login_login_button)
        (registerButton as? Button)?.text = resources.getString(R.string.login_register_button)
    }
}
