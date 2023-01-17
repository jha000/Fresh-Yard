package com.example.milkaggregatorapplication

import com.denzcoskun.imageslider.ImageSlider.setImageList
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import com.example.milkaggregatorapplication.R
import android.content.Intent
import com.example.milkaggregatorapplication.MainActivity
import com.denzcoskun.imageslider.models.SlideModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.example.milkaggregatorapplication.model.RequestModel
import com.example.milkaggregatorapplication.ReceiveOtp
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.example.milkaggregatorapplication.IApiService
import com.example.milkaggregatorapplication.RetrofitInstance
import com.example.milkaggregatorapplication.model.MsgObj
import com.example.milkaggregatorapplication.dashboard
import com.example.milkaggregatorapplication.model.Language
import com.example.milkaggregatorapplication.model.Template
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.TimeUnit

class ReceiveOtp : AppCompatActivity() {
    private var imageSlider: ImageSlider? = null

    //
    //    private EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6;
    private var inputotp: EditText? = null
    private var verificationid: String? = null
    var text: TextView? = null
    var change: TextView? = null
    var userno: TextView? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_otp)
        text = findViewById(R.id.notaken)
        //        change=findViewById(R.id.change);
        userno = findViewById(R.id.notaken)
        inputotp = findViewById(R.id.inputotp)

//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
        val intent = intent
        val new_name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        text.setText(new_name)
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


//        TextView textView = findViewById(R.id.textmobile);
//        textView.setText(String.format(
//                "+91-%s", getIntent().getStringExtra("mobile")
//        ));


//        inputcode1 = findViewById(R.id.inputotp1);
//        inputcode2 = findViewById(R.id.inputotp2);
//        inputcode3 = findViewById(R.id.inputotp3);
//        inputcode4 = findViewById(R.id.inputotp4);
//        inputcode5 = findViewById( R.id.inputotp5);
//        inputcode6 = findViewById(R.id.inputotp6);
//
//        setupotpinput();

//        inputcode1.addTextChangedListener(new GenericTextWatcher(inputcode2, inputcode1));
//        inputcode2.addTextChangedListener(new GenericTextWatcher(inputcode3, inputcode1));
//        inputcode3.addTextChangedListener(new GenericTextWatcher(inputcode4, inputcode2));
//        inputcode4.addTextChangedListener(new GenericTextWatcher(inputcode5, inputcode3));
//        inputcode5.addTextChangedListener(new GenericTextWatcher(inputcode6, inputcode4));
//        inputcode6.addTextChangedListener(new GenericTextWatcher(inputcode6, inputcode5));
        val progressBar = findViewById<ProgressBar>(R.id.Progressbarverifyotp)
        val buttonverify = findViewById<Button>(R.id.buttonverify)
        val language = Language()
        language.code = "en_US"
        val template = Template()
        template.name = "milk_aggregator"
        template.language = language
        val requestModel = RequestModel()
        requestModel.messagingProduct = "whatsapp"
        requestModel.to = ""
        requestModel.type = "template"
        requestModel.template = template
        Log.d(TAG, "onCreate: $requestModel")
        verificationid = getIntent().getStringExtra("verfication")
        buttonverify.setOnClickListener { v ->
            val imm =
                applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
            if (!inputotp.getText().toString().trim { it <= ' ' }.isEmpty()) {
                val code = inputotp.getText().toString()
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
                                val apiService = RetrofitInstance.getRetrofitClient().create(
                                    IApiService::class.java
                                )
                                val call = apiService.sendMessage("107858972133060", requestModel)
                                call.enqueue(object : Callback<MsgObj?> {
                                    override fun onResponse(
                                        call: Call<MsgObj?>,
                                        response: Response<MsgObj?>
                                    ) {
                                        Log.d(TAG, "onResponse: $response")
                                    }

                                    override fun onFailure(call: Call<MsgObj?>, t: Throwable) {
                                        Log.d(TAG, "onFailure: " + t.message)
                                    }
                                })
                                val getNo = userno.getText().toString()
                                val hashMap = HashMap<String, Any>()
                                hashMap["Number"] = getNo
                                firebaseDatabase = FirebaseDatabase.getInstance()
                                databaseReference = firebaseDatabase!!.reference
                                databaseReference!!.child("Users")
                                    .child(getNo)
                                    .setValue(hashMap)
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


//
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
    } //    private void setupotpinput() {

    //        inputcode1.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                if (!s.toString().trim().isEmpty()) {
    //                    inputcode2.requestFocus();
    //                }
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //        inputcode2.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //
    //
    //
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //
    //                if (!s.toString().trim().isEmpty()) {
    //                    inputcode3.requestFocus();
    //                }
    //                else {
    //                    inputcode1.requestFocus();
    //
    //                }
    //
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //
    //        inputcode3.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                if (!s.toString().trim().isEmpty()) {
    //                    inputcode4.requestFocus();
    //                }
    //                else {
    //                    inputcode2.requestFocus();
    //
    //                }
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //
    //        inputcode4.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                if (!s.toString().trim().isEmpty()) {
    //                    inputcode5.requestFocus();
    //                }
    //                else {
    //                    inputcode3.requestFocus();
    //
    //                }
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //
    //        inputcode5.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                if (!s.toString().trim().isEmpty()) {
    //                    inputcode6.requestFocus();
    //                }
    //                else {
    //                    inputcode4.requestFocus();
    //
    //                }
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //
    //        inputcode6.addTextChangedListener(new TextWatcher() {
    //            @Override
    //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //
    //            }
    //
    //            @Override
    //            public void onTextChanged(CharSequence s, int start, int before, int count) {
    //                if (s.toString().trim().isEmpty()) {
    //                    inputcode5.requestFocus();
    //                }
    //
    //            }
    //
    //            @Override
    //            public void afterTextChanged(Editable s) {
    //
    //
    //
    //            }
    //        });
    //
    //
    //    }
    //    public class GenericTextWatcher implements TextWatcher {
    //        private EditText etPrev;
    //        private EditText etNext;
    //
    //        public GenericTextWatcher(EditText etNext, EditText etPrev) {
    //            this.etPrev = etPrev;
    //            this.etNext = etNext;
    //        }
    //
    //        @Override
    //        public void afterTextChanged(Editable editable) {
    //            String text = editable.toString();
    //            if (text.length() == 1)
    //                etNext.requestFocus();
    //            else if (text.length() == 0)
    //                etPrev.requestFocus();
    //        }
    //
    //        @Override
    //        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    //        }
    //
    //        @Override
    //        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    //        }
    //    }
    companion object {
        private const val TAG = "ReceiveOtp"
    }
}