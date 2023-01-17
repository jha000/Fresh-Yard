package com.example.milkaggregatorapplication

import com.denzcoskun.imageslider.ImageSlider.setImageList
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.milkaggregatorapplication.dashboard
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import android.widget.EditText
import android.widget.ProgressBar
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.FirebaseException
import android.widget.Toast
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.example.milkaggregatorapplication.ReceiveOtp
import com.example.milkaggregatorapplication.MainActivity
import java.util.ArrayList
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private var imageSlider: ImageSlider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            // User is signed in
            val i = Intent(this@MainActivity, dashboard::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        imageSlider = findViewById(R.id.imageSlider)
        val slideModels = ArrayList<SlideModel>()
        slideModels.add(
            SlideModel(
                "https://img.freepik.com/premium-vector/cartoon-cow-milk-vector_47570-54.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://media.istockphoto.com/vectors/milking-vector-id483839016?k=20&m=483839016&s=612x612&w=0&h=5BziE9t6TWrJ2jM60LLlOX6e-rYuAPyHxFJjrrDRgik=",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://miro.medium.com/max/806/1*LdYGcRXPUUe34d-rkwFQpw.png",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://t4.ftcdn.net/jpg/03/90/08/41/360_F_390084175_OjGLvc32uWOhUPh56LBn5ZX9HQhjjNWx.jpg",
                ScaleTypes.FIT
            )
        )
        slideModels.add(
            SlideModel(
                "https://www.dairyreporter.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/dairyreporter.com/news/manufacturers/strong-demand-boosts-mueller-uk-farmgate-price/10876576-1-eng-GB/Strong-demand-boosts-Mueller-UK-farmgate-price.jpg",
                ScaleTypes.FIT
            )
        )
        imageSlider.setImageList(slideModels, ScaleTypes.FIT)
        val inputmobile = findViewById<EditText>(R.id.editTextPhone3)
        val buttongetotp = findViewById<Button>(R.id.button2)
        val progressBar = findViewById<ProgressBar>(R.id.progressbarforotp)
        buttongetotp.setOnClickListener { v ->
            val imm =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)


//
            if (!inputmobile.text.toString().trim { it <= ' ' }.isEmpty()) {
                if (inputmobile.text.toString().trim { it <= ' ' }.length == 10) {

//                         #2
                    progressBar.visibility = View.VISIBLE
                    buttongetotp.visibility = View.INVISIBLE
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + inputmobile.text.toString(),
                        60,
                        TimeUnit.SECONDS,
                        this@MainActivity,
                        object : OnVerificationStateChangedCallbacks() {
                            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                                progressBar.visibility = View.GONE
                                buttongetotp.visibility = View.VISIBLE
                            }

                            override fun onVerificationFailed(e: FirebaseException) {
                                progressBar.visibility = View.GONE
                                buttongetotp.visibility = View.VISIBLE
                                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT)
                                    .show()
                            }

                            override fun onCodeSent(
                                verficationid: String,
                                forceResendingToken: ForceResendingToken
                            ) {
                                progressBar.visibility = View.GONE
                                buttongetotp.visibility = View.VISIBLE
                                val intent = Intent(applicationContext, ReceiveOtp::class.java)
                                val name = inputmobile.text.toString()
                                intent.putExtra(EXTRA_NAME, name)
                                intent.putExtra("mobile", inputmobile.text.toString())
                                intent.putExtra("verfication", verficationid)
                                startActivity(intent)
                            }
                        }
                    )
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter correct number",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this@MainActivity, "Enter Mobile number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val EXTRA_NAME = "name"
    }
}