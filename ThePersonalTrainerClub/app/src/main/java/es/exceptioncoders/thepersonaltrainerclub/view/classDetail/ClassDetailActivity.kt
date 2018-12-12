package es.exceptioncoders.thepersonaltrainerclub.view.classDetail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_class_detail.*

class ClassDetailActivity : BaseActivity(), ClassDetailActivityContract.View {

    companion object {
        const val MODEL_CLASS_KEY = "modelClass"
    }

    private lateinit var mPresenter: ClassDetailActivityContract.Presenter<ClassDetailActivity>

    override fun bindLayout(): Int = R.layout.activity_class_detail

    private lateinit var model: ClassModel

    override fun localizeView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mNavigator = ClassDetailActivityNavigator() as ClassDetailActivityContract.Navigator<ClassDetailActivityContract.View>
        mNavigator.attachView(this)

        mPresenter = ClassDetailActivityPresenter(mNavigator) as ClassDetailActivityContract.Presenter<ClassDetailActivity>
        mPresenter.attachView(this)

        model = intent.extras.getSerializable(MODEL_CLASS_KEY) as ClassModel

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)

        supportActionBar?.title = resources.getString(R.string.class_detail_title)

        showClass()
    }

    private fun showClass() {
        Picasso.get().load(model.instructor.thumbnail).into(thumbnail)
        userType.text = resources.getString(R.string.user_settings_type_trainer)
        userName.text = model.instructor.name + " " + model.instructor.lastname

        sportName.text = model.sport.name
        Picasso.get().load(model.sport.icon).into(sportImage)
        locationText.text = model.place
        timeText.text = model.duration.toString()
        countText.text = "" + model.registered + "/" + model.maxusers
        priceText.text = "" + model.price + "€"

        description.text = model.description

        (bookButton as Button).text =  resources.getString(R.string.class_detail_book_button_title)
    }
}
