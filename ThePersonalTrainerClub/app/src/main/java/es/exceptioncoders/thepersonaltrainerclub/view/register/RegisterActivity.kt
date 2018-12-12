package es.exceptioncoders.thepersonaltrainerclub.view.register

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.GenderType
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: BaseActivity(), RegisterActivityContract.RegisterView {
    private lateinit var mPresenter: RegisterActivityContract.RegisterViewPresenter<RegisterActivity>

    override fun bindLayout(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = RegisterActivityNavigator() as RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>
        val maleRadioButton = maleRadioButton as RadioButton;
        mNavigator.attachView(this)

        mPresenter = RegisterActivityPresenter(mNavigator) as RegisterActivityContract.RegisterViewPresenter<RegisterActivity>
        mPresenter.attachView(this)

        registerButton?.setOnClickListener {
            val gender: GenderType = if (maleRadioButton.isChecked) GenderType.MALE else GenderType.FEMALE

            mPresenter.onRegister(nameEditText.text.toString(),
                    lastnameEditText.text.toString(),
                    gender,
                    emailEditText.text.toString(),
                    pwdEditText.text.toString(),
                    isTrainerCheckBox.isSelected
                )
        }

        maleRadioButton.isChecked = true

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = resources.getString(R.string.register_bar_name)
    }

    override fun showLoading() {
        progressBar?.visibility = View.VISIBLE

        nameEditText?.isEnabled = false
        lastnameEditText?.isEnabled = false
        emailEditText?.isEnabled = false
        pwdEditText?.isEnabled = false
        (isTrainerCheckBox as? CheckBox)?.isEnabled = false
        (maleRadioButton as? RadioButton)?.isEnabled = false
        (femaleRadioButton as? RadioButton)?.isEnabled = false
        (registerButton as? Button)?.isEnabled = false
    }

    override fun hideLoading() {
        progressBar?.visibility = View.INVISIBLE

        nameEditText?.isEnabled = true
        lastnameEditText?.isEnabled = true
        emailEditText?.isEnabled = true
        pwdEditText?.isEnabled = true
        (isTrainerCheckBox as? CheckBox)?.isEnabled = true
        (maleRadioButton as? RadioButton)?.isEnabled = true
        (femaleRadioButton as? RadioButton)?.isEnabled = true
        (registerButton as? Button)?.isEnabled = true
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun localizeView() {
        nameEditText?.hint = getString(R.string.register_name_placeholder)
        lastnameEditText?.hint = getString(R.string.register_lastName_placeholder)
        emailEditText?.hint = getString(R.string.register_email_placeholder)
        pwdEditText?.hint = getString(R.string.register_pwd_placeholder)
        (isTrainerCheckBox as? CheckBox)?.text = getString(R.string.register_isTrainer_placeholder)
        (maleRadioButton as? RadioButton)?.text = getString(R.string.register_genderMale_text)
        (femaleRadioButton as? RadioButton)?.text = getString(R.string.register_genderFemale_text)
        (registerButton as? Button)?.text = resources.getString(R.string.register_register_button)
    }
}
