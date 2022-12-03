package com.example.milkaggregatorapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class orderDetails extends AppCompatActivity {


    private ImageSlider imageSlider;

    TextView tvFeedback,name,phone,address,call;
    RatingBar rbStars;
    Button sent;

    ImageView help,back;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        imageSlider=findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels =new ArrayList<>();



        slideModels.add(new SlideModel("https://img.freepik.com/premium-vector/milk-carton-boxes-icon-illustration-isolated_385450-85.jpg?w=2000", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://img.freepik.com/free-vector/carton-milk-with-happy-face_1308-107979.jpg?w=2000", ScaleTypes.FIT));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        input = findViewById(R.id.input);


        tvFeedback = findViewById(R.id.tvFeedback);
        rbStars = findViewById(R.id.rbStars);
        sent = findViewById(R.id.btnSend);

        name = findViewById(R.id.namex);
        phone = findViewById(R.id.phonex);
        address = findViewById(R.id.addressx);

        back = findViewById(R.id.backm);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderDetails.super.onBackPressed();


            }
        });

        help=findViewById(R.id.help);

        call = findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:6300686049"));
                startActivity(callIntent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:6300686049"));
                startActivity(callIntent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        name.setText(value);

        String value1 = sharedPreferences.getString("x","");
        phone.setText(value1);

        String value2 = sharedPreferences.getString("y","");
        address.setText(value2);



        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating==0)
                {
                    tvFeedback.setText("Very Dissatisfied");
                }
                else if(rating==1 || rating==1.5)
                {
                    tvFeedback.setText("Dissatisfied");
                }
                else if(rating==2 || rating==3 || rating==2.5 )
                {
                    tvFeedback.setText("OK");
                }
                else if(rating==4 || rating==3.5)
                {
                    tvFeedback.setText("Satisfied");
                }
                else if(rating==5 || rating==4.5)
                {
                    tvFeedback.setText("Very Satisfied");
                }


            }
        });

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(orderDetails.this,"Feedback Received",Toast.LENGTH_SHORT).show();

                String getName = name.getText().toString();
                String getRating = tvFeedback.getText().toString();
                String getInput = input.getText().toString();
                String userId = databaseReference.push().getKey();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Name", getName);
                hashMap.put("rating", getRating);
                hashMap.put("input", getInput);

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference();


                databaseReference.child("Feedbacks")
                        .child(userId)
                        .child((getRating))
                        .setValue(hashMap);



            }
        });
    }
}