package es.exceptioncoders.thepersonaltrainerclub.view.register

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.GenderType
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*
import android.widget.TextView
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class RegisterActivity: BaseActivity(), RegisterActivityContract.RegisterView {

    private lateinit var mPresenter: RegisterActivityContract.RegisterViewPresenter<RegisterActivity>
    val DATE_FORMAT: String = "dd/MM/yyyy"

    override fun bindLayout(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = RegisterActivityNavigator() as RegisterActivityContract.RegisterViewNavigator<RegisterActivityContract.RegisterView>
        mNavigator.attachView(this)

        mPresenter = RegisterActivityPresenter(mNavigator) as RegisterActivityContract.RegisterViewPresenter<RegisterActivity>
        mPresenter.attachView(this)

        registerButton.setOnClickListener {
            val gender: GenderType = if (maleRadioButton.isChecked) GenderType.MALE else GenderType.FEMALE
            val birthday = convertStringToLocalDateTime (birthdayEditText.text.toString())

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
        isTrainerCheckBox.text = getString(R.string.register_isTrainer_placeholder)
        maleRadioButton.text = getString(R.string.register_genderMale_text)
        femaleRadioButton.text = getString(R.string.register_genderFemale_text)
        (registerButton as Button).text = resources.getString(R.string.register_register_button)
    }

    private fun setUpDatePicker() {
        val textView: TextView  = findViewById(R.id.birthdayEditText)
        textView.text = SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis())
        textView.showSoftInputOnFocus = false

        var calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = DATE_FORMAT
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            textView.text = sdf.format(calendar.time)

        }

        textView.setOnClickListener {
            DatePickerDialog(this@RegisterActivity, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    // TODO: Move to a utilities library
    private fun convertStringToLocalDateTime (dateString: String): LocalDateTime? {
        val formatter = DateTimeFormatter.ofPattern("$DATE_FORMAT HH:mm:ss.SSS", Locale.US)

        try {
            return LocalDateTime.parse("$dateString 00:00:00.000", formatter)
        }
        catch (e: Exception) {
            return null
        }

    }
}
