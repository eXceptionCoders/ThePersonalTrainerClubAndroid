package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.utils.DateUtils
import kotlinx.android.synthetic.main.activity_class_row.view.*

class ClassStripViewAdapter(private val openClases: List<ClassModel>, val mContext: Context): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val openClass = openClases[position]

        val layoutInflater = LayoutInflater.from(mContext)
        val classView = layoutInflater.inflate(R.layout.activity_class_row, null)
        val classDate = DateUtils.convertStringToLocalDateTime(openClass.date)

        // Class mapping
        classView.text_price_class.text = "${openClass.price} €"
        classDate?.let {
           classView.text_date_class.text = DateUtils.convertDateToString(it, DateUtils.DATETIME_FORMAT)
        }
        classView.text_location_class.text = openClass.place
        classView.text_name_sport.text = openClass.sport.name
        classView.text_duration_class.text = "${openClass.duration} min"
        classView.text_number_pupils_class.text = "${openClass.registered} / ${openClass.maxusers}"
        openClass.sport.icon?.let {
            Picasso.get().load(it).into(classView.image_sport_class)
        }

        // Intructor mapping
        classView.text_name_coach_class.text = openClass.instructor.name
        classView.text_lastname_coach_class.text = openClass.instructor.lastname
        openClass.instructor.thumbnail.let {
            Picasso.get().load(it).into(classView.image_coach_class)
        }

        return classView
    }

    override fun getItem(position: Int): Any {
        return openClases[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return openClases.size
    }
}