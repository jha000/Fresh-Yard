package com.example.milkaggregatorapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ImageSlider imageSlider;
    public static final String EXTRA_NAME="name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(MainActivity.this, dashboard.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        imageSlider=findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels =new ArrayList<>();

        slideModels.add(new SlideModel("https://img.freepik.com/premium-vector/cartoon-cow-milk-vector_47570-54.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://media.istockphoto.com/vectors/milking-vector-id483839016?k=20&m=483839016&s=612x612&w=0&h=5BziE9t6TWrJ2jM60LLlOX6e-rYuAPyHxFJjrrDRgik=", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://miro.medium.com/max/806/1*LdYGcRXPUUe34d-rkwFQpw.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t4.ftcdn.net/jpg/03/90/08/41/360_F_390084175_OjGLvc32uWOhUPh56LBn5ZX9HQhjjNWx.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://www.dairyreporter.com/var/wrbm_gb_food_pharma/storage/images/publications/food-beverage-nutrition/dairyreporter.com/news/manufacturers/strong-demand-boosts-mueller-uk-farmgate-price/10876576-1-eng-GB/Strong-demand-boosts-Mueller-UK-farmgate-price.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);


         EditText inputmobile = findViewById(R.id.editTextPhone3);
         Button buttongetotp = findViewById(R.id.button2);



        final ProgressBar progressBar =  findViewById(R.id.progressbarforotp);

        buttongetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//

                if (!inputmobile.getText().toString().trim().isEmpty()){
                    if ((inputmobile.getText().toString().trim()).length() == 10){

//                         #2
                        progressBar.setVisibility(View.VISIBLE);
                        buttongetotp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + inputmobile.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressBar.setVisibility(View.GONE);
                                        buttongetotp.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        buttongetotp.setVisibility(View.VISIBLE);
                                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String verficationid, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        buttongetotp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(getApplicationContext(),ReceiveOtp.class);
                                        String name= inputmobile.getText().toString();
                                        intent.putExtra(EXTRA_NAME,name);
                                        intent.putExtra("mobile",inputmobile.getText().toString());
                                        intent.putExtra("verfication",verficationid);
                                        startActivity(intent);
                                    }
                                }

                        );



                    }else {
                        Toast.makeText(MainActivity.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Enter Mobile number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}