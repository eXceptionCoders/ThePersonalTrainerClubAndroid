package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.LayoutInflater
import es.exceptioncoders.thepersonaltrainerclub.R
import kotlinx.android.synthetic.main.activity_strip_cell.view.*

class ActivityStripViewAdapter(val activities: Array<String>, val mContext: Context): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val activity = activities[position]

        //if (convertView == null) {
            val layoutInflater = LayoutInflater.from(mContext)
            val cv = layoutInflater.inflate(R.layout.activity_strip_cell, null)
        //}

        // 3
        /*val imageView = convertView.findViewById(R.id.imageview_cover_art) as ImageView
        val nameTextView = convertView.findViewById(R.id.textview_book_name) as TextView
        val authorTextView = convertView.findViewById(R.id.textview_book_author) as TextView
        val imageViewFavorite = convertView.findViewById(R.id.imageview_favorite) as ImageView

        // 4
        cv.sport_image.setImageResource(book.getImageResource())
        cv.sport_name.setText(mContext.getString(book.getName()))*/

        return cv
    }

    override fun getItem(position: Int): Any {
        return Any()
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return activities.size
    }
}