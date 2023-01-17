package com.example.milkaggregatorapplication

import com.example.milkaggregatorapplication.CourseModal.id
import com.example.milkaggregatorapplication.CourseModal.courseName
import com.example.milkaggregatorapplication.CourseModal.courseDescription
import com.example.milkaggregatorapplication.CourseModal.courseDuration
import com.example.milkaggregatorapplication.CourseModal
import com.example.milkaggregatorapplication.CourseRVAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.milkaggregatorapplication.R
import android.content.Intent
import android.view.View
import com.example.milkaggregatorapplication.orderDetails
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CourseRVAdapter  // creating a constructor class for our adapter class.
internal constructor() : ListAdapter<CourseModal?, CourseRVAdapter.ViewHolder>(DIFF_CALLBACK) {
    // creating a variable for on item click listener.
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // below line of code is use to set data to
        // each item of our recycler view.
        val model = getCourseAt(position)
        holder.courseNameTV.text = model!!.courseName
        holder.courseDescTV.text = model.courseDescription
        holder.courseDurationTV.text = model.courseDuration

//        String value = holder.courseDurationTV.getText().toString().trim();
//        SharedPreferences sharedPref = holder.itemView.getContext().getSharedPreferences("myKey",holder.itemView.getContext(). MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("z", value);
//        editor.apply();
        holder.itemView.setOnClickListener { view ->
            val intent = Intent(view.context, orderDetails::class.java)
            view.context.startActivity(intent)
        }
    }

    // creating a method to get course modal for a specific position.
    fun getCourseAt(position: Int): CourseModal? {
        return getItem(position)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // view holder class to create a variable for each view.
        var courseNameTV: TextView
        var courseDescTV: TextView
        var courseDurationTV: TextView

        init {
            // initializing each view of our recycler view.
            courseNameTV = itemView.findViewById(R.id.nameid)
            courseDescTV = itemView.findViewById(R.id.tvfirstName)
            courseDurationTV = itemView.findViewById(R.id.tvage)


            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener { // inside on click listener we are passing
                // position to our item of recycler view.
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
        // creating a call back for item of recycler view.
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<CourseModal> =
            object : DiffUtil.ItemCallback<CourseModal>() {
                override fun areItemsTheSame(oldItem: CourseModal, newItem: CourseModal): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CourseModal,
                    newItem: CourseModal
                ): Boolean {
                    // below line is to check the course name, description and course duration.
                    return oldItem.courseName == newItem.courseName && oldItem.courseDescription == newItem.courseDescription && oldItem.courseDuration == newItem.courseDuration
                }
            }
    }
}