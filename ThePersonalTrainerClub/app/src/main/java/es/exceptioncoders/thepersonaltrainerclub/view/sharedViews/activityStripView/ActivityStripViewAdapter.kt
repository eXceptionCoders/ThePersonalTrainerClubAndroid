package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import kotlinx.android.synthetic.main.activity_strip_cell.view.*

class ActivityStripViewAdapter(private val activities: Array<SportModel>, private val currentActivities: Array<SportModel>, private val mContext: Context): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val activity = activities[position]

        val layoutInflater = LayoutInflater.from(mContext)
        val cv = layoutInflater.inflate(R.layout.activity_strip_cell, null)

        activity.icon?.let {
            Picasso.get().load(it).into(cv.sport_image)
        }

        cv.sport_name.text = activity.name

        if (isCurrentActivity(activity)) {
            cv.background = mContext.resources.getDrawable(R.drawable.rounded_orange_border)
        }

        return cv
    }

    override fun getItem(position: Int): Any {
        return activities[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return activities.size
    }

    private fun isCurrentActivity(activity: SportModel): Boolean {
        for (a in currentActivities) {
            if (a.name == activity.name) {
                return true
            }
        }
        return false
    }
}