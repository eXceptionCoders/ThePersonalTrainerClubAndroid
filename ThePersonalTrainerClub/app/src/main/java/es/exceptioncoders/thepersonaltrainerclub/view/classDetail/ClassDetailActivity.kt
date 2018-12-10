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

        //TODO: Get model from intent
        //model = intent.extras.get(MODEL_CLASS_KEY) as ClassModel

        //TODO: Remove mock
        model = Gson().fromJson("{\n" +
                "                \"location\": {\n" +
                "                    \"type\": \"Point\",\n" +
                "                    \"coordinates\": [\n" +
                "                        -16.250426,\n" +
                "                        28.475385\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"type\": \"class\",\n" +
                "                \"registered\": 1,\n" +
                "                \"_id\": \"5c072a3b4d8e218c090e1f87\",\n" +
                "                \"instructor\": {\n" +
                "                    \"_id\": \"5c072a374d8e218c090e1f73\",\n" +
                "                    \"name\": \"Jimena\",\n" +
                "                    \"lastname\": \"Molina Gaos\",\n" +
                "                    \"thumbnail\": \"https://thepersonaltrainerclubcdn-dev.azureedge.net/thumbnails/5c072a374d8e218c090e1f73-8248500224965729-user-profile.jpg\"\n" +
                "                },\n" +
                "                \"sport\": {\n" +
                "                    \"_id\": \"5c072a354d8e218c090e1f5c\",\n" +
                "                    \"name\": \"natacion\",\n" +
                "                    \"category\": \"5c072a334d8e218c090e1f51\",\n" +
                "                    \"icon\": \"https://thepersonaltrainerclubcdn-dev.azureedge.net/activities/natacion.png\"\n" +
                "                },\n" +
                "                \"duration\": 45,\n" +
                "                \"price\": 30,\n" +
                "                \"description\": \"Clases desde aprende a nadar hasta sacate el titulo de socorrista\",\n" +
                "                \"maxusers\": 50,\n" +
                "                \"place\": \"Casa del deporte\",\n" +
                "                \"date\": \"2018-12-05T01:30:35.551Z\",\n" +
                "                \"createdAt\": \"2018-12-05T01:30:35.552Z\",\n" +
                "                \"updatedAt\": \"2018-12-05T01:30:36.707Z\",\n" +
                "                \"__v\": 0\n" +
                "            }", ClassModel::class.java)

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
