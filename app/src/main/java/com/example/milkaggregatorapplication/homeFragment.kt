package com.example.milkaggregatorapplication

import androidx.lifecycle.ViewModelProvider.get
import com.denzcoskun.imageslider.ImageSlider.setImageList
import com.denzcoskun.imageslider.ImageSlider
import android.content.Intent
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.milkaggregatorapplication.ViewModal
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import com.example.milkaggregatorapplication.selection
import com.example.milkaggregatorapplication.selection2
import com.example.milkaggregatorapplication.homeFragment
import com.example.milkaggregatorapplication.CourseRVAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.milkaggregatorapplication.CourseModal
import android.widget.Toast
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import android.app.Activity
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import java.util.ArrayList

class homeFragment : Fragment() {
    var imageSlider: ImageSlider? = null
    var intent: Intent? = null
    var intent2: Intent? = null
    var intent3: Intent? = null
    var refer: TextView? = null
    var card1: CardView? = null
    var card2: CardView? = null
    var int11: CardView? = null
    var int22: CardView? = null
    private var viewmodal: ViewModal? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


//
//
//        intent3 = new Intent(getActivity(), MainActivity2.class);
//        final TextView refer = (TextView) view.findViewById(R.id.re);
        intent = Intent(activity, selection::class.java)
        val button = view.findViewById<View>(R.id.buy1) as Button
        intent2 = Intent(activity, selection2::class.java)
        val button2 = view.findViewById<View>(R.id.small) as Button


//        refer.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(intent3);
//            }
//        });
        card1 = view.findViewById<View>(R.id.card1) as CardView
        card2 = view.findViewById<View>(R.id.card2) as CardView
        int11 = view.findViewById<View>(R.id.int11) as CardView
        int22 = view.findViewById<View>(R.id.int22) as CardView
        button.setOnClickListener { startActivityForResult(intent, ADD_COURSE_REQUEST) }
        card1!!.setOnClickListener { startActivityForResult(intent, ADD_COURSE_REQUEST) }
        val adapter = CourseRVAdapter()
        viewmodal = ViewModelProviders.of(this).get(ViewModal::class.java)

        // below line is use to get all the courses from view modal.
        viewmodal!!.allCourses.observe(activity!!) { models -> // when the data is changed in our models we are
            // adding that list to our adapter class.
            adapter.submitList(models)
        }
        button2.setOnClickListener { startActivityForResult(intent2, ADD_COURSE_REQUEST) }
        card2!!.setOnClickListener { startActivityForResult(intent2, ADD_COURSE_REQUEST) }
        int11!!.setOnClickListener {
            Toast.makeText(
                activity!!.applicationContext,
                "Thank You for the response",
                Toast.LENGTH_SHORT
            ).show()
        }
        int22!!.setOnClickListener {
            Toast.makeText(
                activity!!.applicationContext,
                "Thank You for the response",
                Toast.LENGTH_SHORT
            ).show()
        }
        imageSlider = view.findViewById(R.id.imageSlider)
        val slideModels = ArrayList<SlideModel>()
        slideModels.add(
            SlideModel(
                "https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg",
                ScaleTypes.FIT
            )
        )
        imageSlider.setImageList(slideModels, ScaleTypes.FIT)
        return view
    }

    //
    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_COURSE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val courseName = data!!.getStringExtra(selection.EXTRA_COURSE_NAME)
                val courseDescription = data.getStringExtra(selection.EXTRA_DESCRIPTION)
                val courseDuration = data.getStringExtra(selection.EXTRA_DURATION)
                //                String namex = data.getStringExtra(selection.EXTRA_N);
                val model = CourseModal(courseName, courseDescription, courseDuration)
                viewmodal!!.insert(model)
            }
        }
    }

    companion object {
        private const val EDIT_COURSE_REQUEST = 2
        private const val ADD_COURSE_REQUEST = 1
    }
}