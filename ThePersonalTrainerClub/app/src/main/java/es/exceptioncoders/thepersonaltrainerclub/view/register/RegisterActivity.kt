package es.exceptioncoders.thepersonaltrainerclub.view.register

import android.app.DatePickerDialog
import android.os.Build
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
import java.util.*
import android.widget.TextView
import es.exceptioncoders.thepersonaltrainerclub.utils.DateUtils
import java.text.SimpleDateFormat


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

        registerButton.setOnClickListener {
            val gender: GenderType = if (maleRadioButton.isChecked) GenderType.MALE else GenderType.FEMALE
            val birthday = DateUtils.convertStringToLocalDateTime (birthdayEditText.text.toString())

            mPresenter.onRegister(nameEditText.text.toString(),
                    lastnameEditText.text.toString(),
                    gender,
                    emailEditText.text.toString(),
                    pwdEditText.text.toString(),
                    birthday,
                    isTrainerCheckBox.isSelected
                )
        }

        setUpDatePicker()
        maleRadioButton.isChecked = true

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = resources.getString(R.string.register_bar_name)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun localizeView() {
        nameEditText.hint = getString(R.string.register_name_placeholder)
        lastnameEditText.hint = getString(R.string.register_lastName_placeholder)
        emailEditText.hint = getString(R.string.register_email_placeholder)
        pwdEditText.hint = getString(R.string.register_pwd_placeholder)
        birthdayEditText.hint = getString(R.string.register_birthday_placeholder)
        (isTrainerCheckBox as CheckBox).text = getString(R.string.register_isTrainer_placeholder)
        (maleRadioButton as RadioButton).text = getString(R.string.register_genderMale_text)
        (femaleRadioButton as RadioButton).text = getString(R.string.register_genderFemale_text)
        (registerButton as Button).text = resources.getString(R.string.register_register_button)
    }

    private fun setUpDatePicker() {
        val textView: TextView  = findViewById(R.id.birthdayEditText)
        textView.text = SimpleDateFormat(DateUtils.DATE_FORMAT).format(System.currentTimeMillis())
        // TODO: Find a way to do it for API < 26
        textView.showSoftInputOnFocus = false

        var calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            textView.text = DateUtils.convertDateToString(calendar.time)
        }

        textView.setOnClickListener {
            DatePickerDialog(this@RegisterActivity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}
