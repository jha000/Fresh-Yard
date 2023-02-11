package com.fresh.milkaggregatorapplication


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.roundToInt

class selection : AppCompatActivity(), PaymentResultListener {

    var a: TextView? = null
    var r: TextView? = null
    var s: TextView? = null
    var product: TextView? = null
    var add: TextView? = null
    var quantity: TextView? = null
    var email: TextView? = null
    var st: TextView? = null
    var x: TextView? = null
    var st1: TextView? = null
    var cg: ImageView?=null
    var dec: ImageView?=null
    var inc: ImageView?=null
    var count: TextView? =null
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
    var i=1

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

        email = findViewById(R.id.email)
        pinEdit = findViewById(R.id.pinEdit)
        check = findViewById(R.id.check1)
        message = findViewById(R.id.message)

        check!!.setOnClickListener(View.OnClickListener { view ->
            val imm =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            val value = pinEdit!!.getText().toString().trim { it <= ' ' }
            val sharedPref = applicationContext.getSharedPreferences("myKey", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("pin", value)
            editor.apply()
            if (pinEdit!!.length() != 0) {
                val s = "CHANGE"
                check!!.setText(s)
            }
            if (pinEdit!!.getText().toString() == pin || pinEdit!!.getText()
                    .toString() == pin1 || pinEdit!!.getText().toString() == pin2
            ) {
                message!!.setText("proceed with your order")
                message!!.setTextColor(Color.rgb(7, 94, 84))
                message!!.setVisibility(View.VISIBLE)
            } else {
                message!!.setText("Hey, Glad to know that you miss us.We will be there at your place soon.")
                message!!.setTextColor(Color.RED)
                message!!.setVisibility(View.VISIBLE)
            }
        })
        val sharedPreferences = applicationContext.getSharedPreferences("myKey", MODE_PRIVATE)
        val valueg = sharedPreferences.getString("pin", "")
        pinEdit!!.setText(valueg)

        val value2 = sharedPreferences.getString("value2", "")
        email!!.text = value2

        amountEdt = findViewById(R.id.edit1)
        product = findViewById(R.id.whole)
        quantity = findViewById(R.id.quantity)
        back = findViewById(R.id.backm)

        back!!.setOnClickListener(View.OnClickListener {
            finish()
        })

        x = findViewById<View>(R.id.edit1) as TextView
        inc = findViewById<View>(R.id.inc) as ImageView
        dec = findViewById<View>(R.id.dec) as ImageView
        count = findViewById<View>(R.id.count) as TextView
        cg = findViewById<View>(R.id.ceggs) as ImageView

        inc!!.setOnClickListener (View.OnClickListener {
            i++
            count!!.text=i.toString()

            val mul = quantity!!.text.toString()

            x!!.text = (mul.toFloat() * i).roundToInt().toString()
        })

        dec!!.setOnClickListener {

            if(i>1) {
                i--
                count!!.text = i.toString()

                val mul = quantity!!.text.toString()

                x!!.text = (mul.toFloat() * i).roundToInt().toString()
            }
        }
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
        val courseDesc = count!!.text.toString()
        val courseDuration = amountEdt!!.text.toString()
        if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
            return
        }

        saveCourse(courseName, courseDesc, courseDuration)
        val getName = name!!.text.toString()
        val getEmail = mobile!!.text.toString()
        val getAddress = address!!.text.toString()
        val getProduct = product!!.text.toString()
        val getAmount = amountEdt!!.text.toString()
        val getQty = count!!.text.toString()

        val hashMap = HashMap<String, Any>()
        hashMap["Name"] = getName
        hashMap["Mobile"] = getEmail
        hashMap["Address"] = getAddress
        hashMap["Product"] = getProduct
        hashMap["Amount"] = getAmount
        hashMap["Quantity"] = getQty

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase!!.reference
        val userId = databaseReference!!.push().key
        databaseReference!!.child("My Orders")
            .child(userId!!)
            .setValue(hashMap)


        databaseReference!!.child("Country Eggs")
            .child(userId!!)
            .setValue(hashMap)


        st!!.setOnClickListener {
            myDialog!!.dismiss()
            paymentDialog!!.dismiss()
            super@selection.onBackPressed()
        }

    }

    fun statusError(v: View?) {
        myDialog!!.setContentView(R.layout.dialogfai)
        st1 = myDialog!!.findViewById<View>(R.id.statusError) as TextView?
        val value = name!!.text.toString().trim { it <= ' ' }
        val value1 = mobile!!.text.toString().trim { it <= ' ' }
        val value2 = address!!.text.toString().trim { it <= ' ' }
        val sharedPref = getSharedPreferences("myKey", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("value", value)
        editor.putString("x", value1)
        editor.putString("y", value2)
        editor.apply()

        st1!!.setOnClickListener {
            myDialog!!.dismiss()
            FailureDialog!!.dismiss()
        }

    }

    fun ShowPopup(v: View?) {
        if (pinEdit!!.length() == 0) {
            pinEdit!!.error = "Enter Pincode to proceed"
            pinEdit!!.requestFocus()
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

                // on below line we are getting
                // amount that is entered by user.
                val samount = amountEdt!!.text.toString()


                // rounding off the amount.
                val amount = (samount.toFloat() * 100).roundToInt()

                val smobile = mobile!!.text.toString()

//                val mail = email!!.text.toString()

                // initialize Razorpay account.
                val checkout = Checkout()

                // set your id as below
                checkout.setKeyID("rzp_live_3B17Ixk1cDL2Zz")

                // set image
                checkout.setImage(R.mipmap.ic_launcher)

                // initialize json object
                val `object` = JSONObject()
                try {
                    // to put name
                    `object`.put("name", "Fresh Yard")

                    // put description
                    `object`.put("description", "Fresh Yard")

                    // to set theme color
                    `object`.put("theme.color", "#075E54")

                    // put the currency
                    `object`.put("currency", "INR")

                    // put amount
                    `object`.put("amount", amount)

                    // put mobile number
                    `object`.put("prefill.contact", smobile)

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
        status(v = null)
    }

    override fun onPaymentError(i: Int, s: String) {
        statusError(v = null)
    }

    private fun saveCourse(courseName: String, courseDescription: String, courseDuration: String) {

        val data = Intent()
        data.putExtra(EXTRA_COURSE_NAME, courseName)
        data.putExtra(EXTRA_DESCRIPTION, courseDescription)
        data.putExtra(EXTRA_DURATION, courseDuration)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
        }
        setResult(RESULT_OK, data)
    }

    companion object {
        const val EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID"
        const val EXTRA_COURSE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME"
        const val EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION"
        const val EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION"
    }
}