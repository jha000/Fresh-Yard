package com.fresh.milkaggregatorapplication


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CourseRVAdapter
internal constructor() : ListAdapter<CourseModal, CourseRVAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = getCourseAt(position)
        holder.courseNameTV.text = model!!.courseName
        holder.courseDescTV.text = model.courseDescription
        holder.courseDurationTV.text = model.courseDuration

        val pin1 = "Country Eggs"
        val pin2 = "Poultry Eggs"
        if (model.courseName.toString() == pin1) {
            holder.productimage.setImageResource(R.drawable.countryeggs)
        } else if (model.courseName.toString() == pin2) {
            holder.productimage.setImageResource(R.drawable.poultry)
        }

        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, orderDetails::class.java)
            intent.putExtra("title", model.courseName)
            intent.putExtra("qty", model.courseDescription)
            intent.putExtra("price", model.courseDuration)
            view.context.startActivity(intent)
        }
    }

    fun getCourseAt(position: Int): CourseModal? {
        return getItem(position)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var courseNameTV: TextView
        var courseDescTV: TextView
        var courseDurationTV: TextView
        var productimage: ImageView

        init {
            courseNameTV = itemView.findViewById(R.id.nameid)
            courseDescTV = itemView.findViewById(R.id.tvfirstName)
            courseDurationTV = itemView.findViewById(R.id.tvage)
            productimage = itemView.findViewById(R.id.productimage)

            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(getItem(position))
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(model: CourseModal?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<CourseModal> =
            object : DiffUtil.ItemCallback<CourseModal>() {
                override fun areItemsTheSame(oldItem: CourseModal, newItem: CourseModal): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CourseModal,
                    newItem: CourseModal
                ): Boolean {
                    return oldItem.courseName == newItem.courseName && oldItem.courseDescription == newItem.courseDescription && oldItem.courseDuration == newItem.courseDuration
                }
            }
    }
}