package com.example.milkaggregatorapplication

import com.denzcoskun.imageslider.ImageSlider.setImageList
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.PaymentResultListener
import com.denzcoskun.imageslider.ImageSlider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import android.graphics.drawable.ColorDrawable
import android.app.Activity
import android.content.SharedPreferences
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.example.milkaggregatorapplication.selection
import com.razorpay.Checkout
import org.json.JSONObject
import org.json.JSONException
import java.util.ArrayList
import java.util.HashMap

class selection : AppCompatActivity(), PaymentResultListener {
    private var imageSlider: ImageSlider? = null
    var a: TextView? = null
    var b: TextView? = null
    var c: TextView? = null
    var d: TextView? = null
    var p: TextView? = null
    var q: TextView? = null
    var r: TextView? = null
    var s: TextView? = null
    var product: TextView? = null
    var add: TextView? = null
    var quantity: TextView? = null
    var st: TextView? = null
    var x: TextView? = null
    var y: TextView? = null
    var m: TextView? = null
    var n: TextView? = null
    var o: TextView? = null
    var k: TextView? = null
    var pinEdit: EditText? = null
    var check: Button? = null
    var message: TextView? = null
    private var amountEdt: TextView? = null
    var name: EditText? = null
    var mobile: EditText? = null
    var address: EditText? = null
    var back: ImageView? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    var paymentDialog: BottomSheetDialog? = null
    var FailureDialog: BottomSheetDialog? = null
    var myDialog: BottomSheetDialog? = null
    var pin = "784028"
    var pin1 = "781001"
    var pin2 = "781002"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        myDialog = BottomSheetDialog(this)
        paymentDialog = BottomSheetDialog(this)
        paymentDialog!!.setContentView(R.layout.dialog)
        paymentDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        FailureDialog = BottomSheetDialog(this)
        FailureDialog!!.setContentView(R.layout.dialogfai)
        FailureDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pinEdit = findViewById(R.id.pinEdit)
        check = findViewById(R.id.check1)
        message = findViewById(R.id.message)
        check.setOnClickListener(View.OnClickListener { view ->
            val imm =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            val value = pinEdit.getText().toString().trim { it <= ' ' }
            val sharedPref = applicationContext.getSharedPreferences("myKey", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("pin", value)
            editor.apply()
            if (pinEdit.length() != 0) {
                val s = "CHANGE"
                check.setText(s)
            }
            if (pinEdit.getText().toString() == pin || pinEdit.getText()
                    .toString() == pin1 || pinEdit.getText().toString() == pin2
            ) {
                message.setText("proceed with your order")
                message.setTextColor(Color.rgb(30, 215, 96))
                message.setVisibility(View.VISIBLE)
            } else {
                message.setText("Hey, Glad to know that you miss us.We will be there at your place soon.")
                message.setTextColor(Color.RED)
                message.setVisibility(View.VISIBLE)
            }
        })
        val sharedPreferences = applicationContext.getSharedPreferences("myKey", MODE_PRIVATE)
        val valueg = sharedPreferences.getString("pin", "")
        pinEdit.setText(valueg)
        amountEdt = findViewById(R.id.edit1)
        product = findViewById(R.id.whole)
        quantity = findViewById(R.id.quantity)
        back = findViewById(R.id.backm)
        back.setOnClickListener(View.OnClickListener { finish() })
        val progressBar = findViewById<ProgressBar>(R.id.progressbarforotp)
        imageSlider = findViewById(R.id.imageSlider)
        a = findViewById<View>(R.id.one) as TextView
        b = findViewById<View>(R.id.two) as TextView
        //        c=(TextView)findViewById(R.id.three);
//        d=(TextView)findViewById(R.id.four);
        p = findViewById<View>(R.id.dam1) as TextView
        q = findViewById<View>(R.id.dam2) as TextView
        //        r=(TextView)findViewById(R.id.dam3);
//        s=(TextView)findViewById(R.id.dam4);
        x = findViewById<View>(R.id.edit1) as TextView
        m = findViewById<View>(R.id.dam1) as TextView
        n = findViewById<View>(R.id.dam2) as TextView
        //        o=(TextView) findViewById(R.id.dam3);
//        k=(TextView) findViewById(R.id.dam4);
        a!!.setOnClickListener {
            p!!.setTextColor(Color.rgb(4, 148, 62))
            q!!.setTextColor(Color.GRAY)
            //                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);
            p!!.setBackgroundResource(R.drawable.box)
            q!!.setBackgroundResource(R.drawable.blackradius)
            a!!.setTextColor(Color.rgb(4, 148, 62))
            b!!.setTextColor(Color.BLACK)
            //                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);
            val s = m!!.text.toString()
            x!!.text = s
        }
        p!!.setOnClickListener {
            p!!.setTextColor(Color.rgb(4, 148, 62))
            q!!.setTextColor(Color.GRAY)
            //                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);
            p!!.setBackgroundResource(R.drawable.box)
            q!!.setBackgroundResource(R.drawable.blackradius)
            a!!.setTextColor(Color.rgb(4, 148, 62))
            b!!.setTextColor(Color.BLACK)
            //                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);
            val s = m!!.text.toString()
            x!!.text = s
        }
        b!!.setOnClickListener {
            p!!.setTextColor(Color.GRAY)
            q!!.setTextColor(Color.rgb(4, 148, 62))
            //                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);
            q!!.setBackgroundResource(R.drawable.box)
            p!!.setBackgroundResource(R.drawable.blackradius)
            a!!.setTextColor(Color.BLACK)
            b!!.setTextColor(Color.rgb(4, 148, 62))
            //                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);
            val t = n!!.text.toString()
            x!!.text = t
        }
        q!!.setOnClickListener {
            p!!.setTextColor(Color.GRAY)
            q!!.setTextColor(Color.rgb(4, 148, 62))
            //                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);
            q!!.setBackgroundResource(R.drawable.box)
            p!!.setBackgroundResource(R.drawable.blackradius)
            a!!.setTextColor(Color.BLACK)
            b!!.setTextColor(Color.rgb(4, 148, 62))
            //                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);
            val t = n!!.text.toString()
            x!!.text = t
        }
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
    }

    fun status(v: View?) {
        myDialog!!.setContentView(R.layout.dialog)
        st = myDialog!!.findViewById<View>(R.id.status) as TextView?
        val value = name!!.text.toString().trim { it <= ' ' }
        val value1 = mobile!!.text.toString().trim { it <= ' ' }
        val value2 = address!!.text.toString().trim { it <= ' ' }
        val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("value", value)
        editor.putString("x", value1)
        editor.putString("y", value2)
        editor.apply()
        val courseName = product!!.text.toString()
        val courseDesc = quantity!!.text.toString()
        val courseDuration = amountEdt!!.text.toString()
        if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
            return
        }

        // calling a method to save our course.
        saveCourse(courseName, courseDesc, courseDuration)
        val getName = name!!.text.toString()
        val getEmail = mobile!!.text.toString()
        val getAddress = address!!.text.toString()
        val getProduct = product!!.text.toString()
        val getAmount = amountEdt!!.text.toString()
        val hashMap = HashMap<String, Any>()
        hashMap["Name"] = getName
        hashMap["Mobile"] = getEmail
        hashMap["Address"] = getAddress
        hashMap["Product"] = getProduct
        hashMap["Amount"] = getAmount
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference
        val userId = databaseReference!!.push().key
        databaseReference!!.child("Orders")
            .child(userId!!)
            .setValue(hashMap)
        st!!.setOnClickListener {
            myDialog!!.dismiss()
            paymentDialog!!.dismiss()
            super@selection.onBackPressed()
        }
    }

    fun ShowPopup(v: View?) {
        if (pinEdit!!.length() == 0) {
            pinEdit!!.error = "Enter Pincode to proceed"
        } else if (pinEdit!!.text.toString() == pin || pinEdit!!.text.toString() == pin1 || pinEdit!!.text.toString() == pin2) {
            val txtclose: TextView?
            val add2: TextView?
            val cod: TextView?
            val progress: ProgressBar?
            val back: ImageView?
            var btnFollow: Button
            myDialog!!.setContentView(R.layout.custompopup)
            back = myDialog!!.findViewById<View>(R.id.arrow) as ImageView?
            cod = myDialog!!.findViewById<View>(R.id.cod) as TextView?
            back!!.setOnClickListener { myDialog!!.dismiss() }
            txtclose = myDialog!!.findViewById<View>(R.id.txtclose) as TextView?
            progress = myDialog!!.findViewById<View>(R.id.progressbar) as ProgressBar?
            add2 = myDialog!!.findViewById<View>(R.id.textadd) as TextView?
            //        amountEdt1 = (EditText) myDialog.findViewById(R.id.edit1);
//
            val no = amountEdt!!.text.toString()
            add2!!.text = no
            name = myDialog!!.findViewById<View>(R.id.entername) as EditText?
            mobile = myDialog!!.findViewById<View>(R.id.entern0) as EditText?
            address = myDialog!!.findViewById<View>(R.id.enteradd) as EditText?
            val sharedPreferences = applicationContext.getSharedPreferences("myKey", MODE_PRIVATE)
            val nameSave = sharedPreferences.getString("value", "")
            name!!.setText(nameSave)
            val mobileSave = sharedPreferences.getString("x", "")
            mobile!!.setText(mobileSave)
            val addSave = sharedPreferences.getString("y", "")
            address!!.setText(addSave)
            val intent = intent
            if (intent.hasExtra(EXTRA_ID)) {
                // if we get id for our data then we are
                // setting values to our edit text fields.
                product!!.text = intent.getStringExtra(EXTRA_COURSE_NAME)
                quantity!!.text = intent.getStringExtra(EXTRA_DESCRIPTION)
                amountEdt!!.text = intent.getStringExtra(EXTRA_DURATION)
            }
            txtclose!!.setOnClickListener { v ->
                val imm =
                    applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                progress!!.visibility = View.VISIBLE
                txtclose.visibility = View.INVISIBLE


//

                // on below line we are getting
                // amount that is entered by user.
                val samount = amountEdt!!.text.toString()


                // rounding off the amount.
                val amount = Math.round(samount.toFloat() * 100)

                // initialize Razorpay account.
                val checkout = Checkout()

                // set your id as below
                checkout.setKeyID("rzp_live_3B17Ixk1cDL2Zz")


                // set image
                checkout.setImage(R.drawable.logo1)

                // initialize json object
                val `object` = JSONObject()
                try {
                    // to put name
                    `object`.put("name", "Milk Aggregator")

                    // put description
                    `object`.put("description", "Test payment")

                    // to set theme color
                    `object`.put("theme.color", "#23a847")

                    // put the currency
                    `object`.put("currency", "INR")

                    // put amount
                    `object`.put("amount", amount)

                    // put mobile number
                    `object`.put("prefill.contact", "")

                    // put email
                    `object`.put("prefill.email", "")


                    // open razorpay to checkout activity
                    checkout.open(this@selection, `object`)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            myDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog!!.show()
        } else Toast.makeText(
            this,
            "Please select different location to proceed",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onPaymentSuccess(s: String) {
        val progressBar = findViewById<ProgressBar>(R.id.progressbarforotp)


//
//
        myDialog!!.dismiss()


//
        paymentDialog!!.show()
        val value = name!!.text.toString().trim { it <= ' ' }
        val value1 = mobile!!.text.toString().trim { it <= ' ' }
        val value2 = address!!.text.toString().trim { it <= ' ' }
        val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("value", value)
        editor.putString("x", value1)
        editor.putString("y", value2)
        editor.apply()
        val courseName = product!!.text.toString()
        val courseDesc = quantity!!.text.toString()
        val courseDuration = amountEdt!!.text.toString()
        if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
            return
        }

        // calling a method to save our course.
        saveCourse(courseName, courseDesc, courseDuration)
        val getName = name!!.text.toString()
        val getEmail = mobile!!.text.toString()
        val getAddress = address!!.text.toString()
        val getProduct = product!!.text.toString()
        val getAmount = amountEdt!!.text.toString()
        val hashMap = HashMap<String, Any>()
        hashMap["Name"] = getName
        hashMap["Mobile"] = getEmail
        hashMap["Address"] = getAddress
        hashMap["Product"] = getProduct
        hashMap["Amount"] = getAmount
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference
        val userId = databaseReference!!.push().key
        databaseReference!!.child("Orders")
            .child(userId!!)
            .setValue(hashMap)
    }

    override fun onPaymentError(i: Int, s: String) {
        val progressBar = findViewById<ProgressBar>(R.id.progressbarforotp)
        //
//
        myDialog!!.dismiss()
        FailureDialog!!.show()
    }

    private fun saveCourse(courseName: String, courseDescription: String, courseDuration: String) {
        // inside this method we are passing
        // all the data via an intent.
        val data = Intent()

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_COURSE_NAME, courseName)
        data.putExtra(EXTRA_DESCRIPTION, courseDescription)
        data.putExtra(EXTRA_DURATION, courseDuration)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id)
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data)

        // displaying a toast message after adding the data
    }

    companion object {
        const val EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID"
        const val EXTRA_COURSE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME"
        const val EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION"
        const val EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION"
    }
}