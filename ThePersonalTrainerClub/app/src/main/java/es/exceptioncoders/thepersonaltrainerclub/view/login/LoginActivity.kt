package es.exceptioncoders.thepersonaltrainerclub.view.login

import android.os.Bundle
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

        loginButton.setOnClickListener {
            mPresenter.onLogin(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        registerButton.setOnClickListener {
            mPresenter.onRegister()
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String) {
        print(message)
    }
}
