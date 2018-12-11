package es.exceptioncoders.thepersonaltrainerclub.view.sharedViews.activityStripView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.exceptioncoders.thepersonaltrainerclub.R
import es.exceptioncoders.thepersonaltrainerclub.model.model.SportModel
import kotlinx.android.synthetic.main.activity_strip_cell.view.*

typealias Click = (SportModel) -> Unit

class ActivityListAdapter(val clickListener: Click):  RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder>() {

    private lateinit var context: Context

    private var data: MutableList<SportModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        context = parent.context!!
        return ActivityViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.activity_strip_cell, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<SportModel>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SportModel) = with(itemView) {
            kotlin.with(itemView) {
                item.icon?.let {
                    Picasso.get().load(it).into(sport_image)
                }

                sport_name.text = item.name
            }
        }
    }
}