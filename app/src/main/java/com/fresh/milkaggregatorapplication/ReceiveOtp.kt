package com.fresh.milkaggregatorapplication


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

class ReceiveOtp : AppCompatActivity() {

    private var inputotp: EditText? = null
    private var verificationid: String? = null
    var text: TextView? = null
    var userno: TextView? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_otp)
        text = findViewById(R.id.notaken)
        userno = findViewById(R.id.notaken)
        inputotp = findViewById(R.id.inputotp)

        val intent = intent
        val new_name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        text!!.setText(new_name)

        val progressBar = findViewById<ProgressBar>(R.id.Progressbarverifyotp)
        val buttonverify = findViewById<Button>(R.id.buttonverify)

        verificationid = getIntent().getStringExtra("verfication")
        buttonverify.setOnClickListener { v ->
            val imm =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            if (!inputotp!!.getText().toString().trim { it <= ' ' }.isEmpty()) {
                val code = inputotp!!.getText().toString()
                if (verificationid != null) {
                    progressBar.visibility = View.VISIBLE
                    buttonverify.visibility = View.INVISIBLE
                    val phoneAuthCredential = PhoneAuthProvider.getCredential(
                        verificationid!!, code
                    )
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener { task ->
                            progressBar.visibility = View.GONE
                            buttonverify.visibility = View.VISIBLE
                            if (task.isSuccessful) {

                                val r = Random()
                                val numbers = 100000 + (r.nextFloat() * 899900).toInt()

                                val value = numbers.toString().trim { it <= ' ' }
                                val value2 = userno!!.text.toString().trim { it <= ' ' }
                                val sharedPref =
                                    getSharedPreferences("myKey", AppCompatActivity.MODE_PRIVATE)
                                val editor = sharedPref.edit()
                                editor.putString("saveCode", value)
                                editor.putString("x", value2)
                                editor.apply()

                                val getNo = userno!!.text.toString()
                                val getCode = numbers.toString()
                                val hashMap = java.util.HashMap<String, Any>()
                                hashMap["number"] = getNo
                                hashMap["code"] = getCode
                                val firebaseDatabase = FirebaseDatabase.getInstance()
                                val databaseReference = firebaseDatabase.reference
                                databaseReference.child("Users")
                                    .child(getNo)
                                    .setValue(hashMap)

                                databaseReference.child("Referral code")
                                    .child(getCode)
                                    .setValue(getCode)


                                val intent = Intent(applicationContext, dashboard::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                Toast.makeText(this@ReceiveOtp, "otp received", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                Toast.makeText(
                                    this@ReceiveOtp,
                                    "Enter the Correct otp",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } else {
                Toast.makeText(this@ReceiveOtp, "Please enter the otp", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<View>(R.id.textresendotp).setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + getIntent().getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                this@ReceiveOtp,
                object : OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(this@ReceiveOtp, e.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onCodeSent(
                        newVerification: String,
                        forceResendingToken: ForceResendingToken
                    ) {
                        verificationid = newVerification
                        Toast.makeText(this@ReceiveOtp, "OTP sent again", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    companion object {
        private const val TAG = "ReceiveOtp"
    }
}