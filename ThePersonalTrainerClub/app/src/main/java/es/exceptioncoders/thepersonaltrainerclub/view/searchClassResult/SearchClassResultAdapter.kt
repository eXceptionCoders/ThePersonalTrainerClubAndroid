package es.exceptioncoders.thepersonaltrainerclub.view.searchClassResult

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.ClassModel
import es.exceptioncoders.thepersonaltrainerclub.utils.DateUtils
import kotlinx.android.synthetic.main.cell_class_found.view.*

class SearchClassResultAdapter(val data: Array<ClassModel>):  RecyclerView.Adapter<SearchClassResultAdapter.SearchClassResultViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchClassResultViewHolder {
        context = parent.context!!
        return SearchClassResultViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.cell_class_found, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SearchClassResultViewHolder, position: Int) = holder.bind(data[position])

    inner class SearchClassResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ClassModel) = with(itemView) {
            val classDate = DateUtils.convertStringToLocalDateTime(item.date)

            // Class mapping
            text_price_class.text = "${item.price} €"
            classDate?.let {
                text_date_class.text = DateUtils.convertDateToString(it, DateUtils.DATETIME_FORMAT)
            }
            text_location_class.text = item.place
            text_name_sport.text = item.sport.name
            text_duration_class.text = "${item.duration} min"
            text_number_pupils_class.text = "${item.registered} / ${item.maxusers}"
            item.sport.icon?.let {
                Picasso.get().load(it).into(image_sport_class)
            }

            // Intructor mapping
            text_name_coach_class.text = item.instructor.name
            text_lastname_coach_class.text = item.instructor.lastname
            item.instructor.thumbnail.let {
                Picasso.get().load(it).into(image_coach_class)
            }
        }
    }
}