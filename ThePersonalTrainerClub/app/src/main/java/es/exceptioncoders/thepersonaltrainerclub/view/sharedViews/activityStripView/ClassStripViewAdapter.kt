package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import kotlinx.android.synthetic.main.activity_class_row.view.*

class ClassStripViewAdapter(private val openClases: List<ClassModel>, val mContext: Context): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val openClass = openClases[position]

        val layoutInflater = LayoutInflater.from(mContext)
        val cv = layoutInflater.inflate(R.layout.activity_class_row, null)

        cv.priceTextView.text = "${openClass.price} €"
        cv.locationTextView.text = openClass.location.description
        cv.sportTextView.text = openClass.sport.name

        openClass.sport.icon?.let {
            Picasso.get().load(it).into(cv.sportImageView)
        }
        return cv
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