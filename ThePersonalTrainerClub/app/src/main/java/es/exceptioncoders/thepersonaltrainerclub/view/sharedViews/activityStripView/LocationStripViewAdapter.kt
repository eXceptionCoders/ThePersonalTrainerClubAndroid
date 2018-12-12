package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.LocationModel
import kotlinx.android.synthetic.main.activity_location_row.view.*

class LocationStripViewAdapter(val enableSelection: Boolean = false, var locations: List<LocationModel>, val mContext: Context): BaseAdapter() {
    var itemSelected: LocationModel? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val location = locations[position]

        val layoutInflater = LayoutInflater.from(mContext)
        val cv = layoutInflater.inflate(R.layout.activity_location_row, null)

        cv.locationName.text = location.description

        itemSelected?.let {locationSelected ->
            if (enableSelection) {
                if (locationSelected == location) {
                    cv.background = mContext.resources.getDrawable(R.drawable.rounded_orange_border)
                } else {
                    cv.setBackgroundColor(mContext.resources.getColor(R.color.lightGray))
                }
            }
        } ?: run {
            cv.setBackgroundColor(mContext.resources.getColor(R.color.lightGray))
        }

        return cv
    }

    override fun getItem(position: Int): Any {
        return locations[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return locations.size
    }
}