package es.exceptioncoders.thepersonaltrainerclub.view.login

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginActivityContract.LoginView {
    private lateinit var mPresenter: LoginActivityContract.LoginViewPresenter<LoginActivity>

    override fun bindLayout(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = LoginActivityNavigator() as LoginActivityContract.LoginViewNavigator<LoginActivityContract.LoginView>
        mNavigator.attachView(this)

        mPresenter = LoginActivityPresenter(mNavigator) as LoginActivityContract.LoginViewPresenter<LoginActivity>
        mPresenter.attachView(this)

        (loginButton as Button).text = "Login"
        loginButton.setOnClickListener {
            mPresenter.onLogin(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        (registerButton as Button).text = "Register"
        registerButton.setOnClickListener {
            mPresenter.onRegister()
        }



        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = "GymUs"
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }
}
