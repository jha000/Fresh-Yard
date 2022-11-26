package com.example.milkaggregatorapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.milkaggregatorapplication.model.Language;
import com.example.milkaggregatorapplication.model.MsgObj;
import com.example.milkaggregatorapplication.model.RequestModel;
import com.example.milkaggregatorapplication.model.Template;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiveOtp extends AppCompatActivity {

    private static final String TAG = "ReceiveOtp";

    private ImageSlider imageSlider;

    private EditText inputcode1, inputcode2, inputcode3, inputcode4, inputcode5, inputcode6;

    private String verificationid;
    TextView text,change,userno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_otp);

        text=findViewById(R.id.notaken);
//        change=findViewById(R.id.change);
        userno=findViewById(R.id.notaken);

//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

        Intent intent=getIntent();
        String new_name=intent.getStringExtra(MainActivity.EXTRA_NAME);
        text.setText(new_name);

        imageSlider=findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels =new ArrayList<>();

        slideModels.add(new SlideModel("https://img.freepik.com/premium-vector/cartoon-cow-milk-vector_47570-54.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://media.istockphoto.com/vectors/milking-vector-id483839016?k=20&m=483839016&s=612x612&w=0&h=5BziE9t6TWrJ2jM60LLlOX6e-rYuAPyHxFJjrrDRgik=", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://miro.medium.com/max/806/1*LdYGcRXPUUe34d-rkwFQpw.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t4.ftcdn.net/jpg/03/90/08/41/360_F_390084175_OjGLvc32uWOhUPh56LBn5ZX9HQhjjNWx.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.dairyreporter.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/dairyreporter.com/news/manufacturers/strong-demand-boosts-mueller-uk-farmgate-price/10876576-1-eng-GB/Strong-demand-boosts-Mueller-UK-farmgate-price.jpg", ScaleTypes.FIT));


        imageSlider.setImageList(slideModels,ScaleTypes.FIT);




//        TextView textView = findViewById(R.id.textmobile);
//        textView.setText(String.format(
//                "+91-%s", getIntent().getStringExtra("mobile")
//        ));


        inputcode1 = findViewById(R.id.inputotp1);
        inputcode2 = findViewById(R.id.inputotp2);
        inputcode3 = findViewById(R.id.inputotp3);
        inputcode4 = findViewById(R.id.inputotp4);
        inputcode5 = findViewById( R.id.inputotp5);
        inputcode6 = findViewById(R.id.inputotp6);

        setupotpinput();

        final ProgressBar progressBar = findViewById(R.id.Progressbarverifyotp);

        final Button buttonverify = findViewById(R.id.buttonverify);



        Language language=new Language();
        language.setCode("en_US");

        Template template =new Template();
        template.setName("milk_aggregator");
        template.setLanguage(language);





        RequestModel requestModel=new RequestModel();
        requestModel.setMessagingProduct("whatsapp");
        requestModel.setTo("");
        requestModel.setType("template");
        requestModel.setTemplate(template);

        Log.d(TAG, "onCreate: "+requestModel.toString());





        verificationid = getIntent().getStringExtra("verfication");

        buttonverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputcode1.getText().toString().trim().isEmpty()
                        && !inputcode2.getText().toString().trim().isEmpty()
                        && !inputcode3.getText().toString().trim().isEmpty()
                        && !inputcode4.getText().toString().trim().isEmpty()
                        && !inputcode5.getText().toString().trim().isEmpty()
                        && !inputcode6.getText().toString().trim().isEmpty()){

                    String code = inputcode1.getText().toString() +
                            inputcode2.getText().toString() +
                            inputcode3.getText().toString() +
                            inputcode4.getText().toString() +
                            inputcode5.getText().toString() +
                            inputcode6.getText().toString();

                    if (verificationid != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        buttonverify.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                verificationid, code
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        buttonverify.setVisibility(View.VISIBLE);
                                        if (task.isSuccessful()) {

                                            IApiService apiService =RetrofitInstance.getRetrofitClient().create(IApiService.class);

                                            Call<MsgObj> call=apiService.sendMessage("107858972133060",requestModel);

                                            call.enqueue(new Callback<MsgObj>() {
                                                @Override
                                                public void onResponse(Call<MsgObj> call, Response<MsgObj> response) {
                                                    Log.d(TAG, "onResponse: "+response.toString());
                                                }

                                                @Override
                                                public void onFailure(Call<MsgObj> call, Throwable t) {
                                                    Log.d(TAG, "onFailure: "+t.getMessage());

                                                }
                                            });


                                            Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            Toast.makeText(ReceiveOtp.this, "otp received", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ReceiveOtp.this, "Enter the Correct otp", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }else {
                    Toast.makeText(ReceiveOtp.this, "Please enter the otp", Toast.LENGTH_SHORT).show();
                }
            }
        });




//





        findViewById(R.id.textresendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        ReceiveOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {



                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(ReceiveOtp.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationid = newVerification;
                                Toast.makeText(ReceiveOtp.this, "OTP sent again", Toast.LENGTH_SHORT).show();
                            }
                        }

                );
            }
        });



    }

    private void setupotpinput() {
        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}