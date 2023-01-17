package com.example.milkaggregatorapplication

import com.denzcoskun.imageslider.ImageSlider.setImageList
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.view.View
import android.widget.*
import android.widget.RatingBar.OnRatingBarChangeListener
import java.util.ArrayList
import java.util.HashMap

class orderDetails : AppCompatActivity() {
    private var imageSlider: ImageSlider? = null
    var tvFeedback: TextView? = null
    var name: TextView? = null
    var phone: TextView? = null
    var address: TextView? = null
    var call: TextView? = null
    var rbStars: RatingBar? = null
    var sent: Button? = null
    var help: ImageView? = null
    var back: ImageView? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    var input: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)
        imageSlider = findViewById(R.id.imageSlider)
        val slideModels = ArrayList<SlideModel>()
        slideModels.add(
            SlideModel(
                "https://img.freepik.com/premium-vector/milk-carton-boxes-icon-illustration-isolated_385450-85.jpg?w=2000",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://img.freepik.com/free-vector/carton-milk-with-happy-face_1308-107979.jpg?w=2000",
                ScaleTypes.FIT
            )
        )
        imageSlider.setImageList(slideModels, ScaleTypes.FIT)
        input = findViewById(R.id.input)
        tvFeedback = findViewById(R.id.tvFeedback)
        rbStars = findViewById(R.id.rbStars)
        sent = findViewById(R.id.btnSend)
        name = findViewById(R.id.namex)
        phone = findViewById(R.id.phonex)
        address = findViewById(R.id.addressx)
        back = findViewById(R.id.backm)
        back.setOnClickListener(View.OnClickListener { super@orderDetails.onBackPressed() })
        help = findViewById(R.id.help)
        call = findViewById(R.id.call)
        call.setOnClickListener(View.OnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:6300686049")
            startActivity(callIntent)
        })
        help.setOnClickListener(View.OnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:6300686049")
            startActivity(callIntent)
        })
        val sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE)
        val value = sharedPreferences.getString("value", "")
        name.setText(value)
        val value1 = sharedPreferences.getString("x", "")
        phone.setText(value1)
        val value2 = sharedPreferences.getString("y", "")
        address.setText(value2)
        rbStars.setOnRatingBarChangeListener(OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (rating == 0f) {
                tvFeedback.setText("Very Dissatisfied")
            } else if (rating == 1f || rating.toDouble() == 1.5) {
                tvFeedback.setText("Dissatisfied")
            } else if (rating == 2f || rating == 3f || rating.toDouble() == 2.5) {
                tvFeedback.setText("OK")
            } else if (rating == 4f || rating.toDouble() == 3.5) {
                tvFeedback.setText("Satisfied")
            } else if (rating == 5f || rating.toDouble() == 4.5) {
                tvFeedback.setText("Very Satisfied")
            }
        })
        sent.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@orderDetails, "Feedback Received", Toast.LENGTH_SHORT).show()
            val getNo = phone.getText().toString()
            val getName = name.getText().toString()
            val getRating = tvFeedback.getText().toString()
            val getInput = input.getText().toString()
            val hashMap = HashMap<String, Any>()
            hashMap["Mobile"] = getNo
            hashMap["Name"] = getName
            hashMap["rating"] = getRating
            hashMap["input"] = getInput
            firebaseDatabase = FirebaseDatabase.getInstance()
            databaseReference = firebaseDatabase!!.reference
            val userId = databaseReference!!.push().key
            databaseReference!!.child("Feedbacks")
                .child(userId!!)
                .setValue(hashMap)
        })
    }
}