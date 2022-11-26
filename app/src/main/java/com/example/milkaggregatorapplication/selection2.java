package com.example.milkaggregatorapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class selection2 extends AppCompatActivity implements PaymentResultListener {



    private ImageSlider imageSlider;

    TextView a,b,c,d,p,q,r,s,product,add,quantity,st;
    TextView x,y;
    TextView m,n,o,k;




    private TextView amountEdt;

    EditText name,mobile,address;

    ImageView back;


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Dialog paymentDialog,FailureDialog;
    Dialog myDialog;


    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_COURSE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";
    public static final String EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";









//    TextView PayStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection2);


//        Checkout.preload(getApplicationContext());





        myDialog = new Dialog(this);

        paymentDialog = new Dialog(this);
        paymentDialog.setContentView(R.layout.dialog);


        paymentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        FailureDialog = new Dialog(this);
        FailureDialog.setContentView(R.layout.dialogfai);

        FailureDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));










        amountEdt = findViewById(R.id.edit1);

        product = findViewById(R.id.whole);
        quantity = findViewById(R.id.quantity);

        back=findViewById(R.id.backm);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




//        name = findViewById(R.id.namefb);
//        mobile = findViewById(R.id.nofb);
//        address = findViewById(R.id.addressfb);
//
//
//        Intent intent=getIntent();
//        String new_name=intent.getStringExtra(profileFragment.EXTRA_NAME);
//        String new_mobile=intent.getStringExtra(profileFragment.EXTRA_NUMBER);
//        String new_address=intent.getStringExtra(profileFragment.EXTRA_ADDRESS);
//
//        name.setText(new_name);
//        mobile.setText(new_mobile);
//        address.setText(new_address);




        final ProgressBar progressBar =  findViewById(R.id.progressbarforotp);
//
//        payBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),PaymentGateway.class);
//                startActivity(intent);
//
//            }
//
//
//        });

//        payBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                progressBar.setVisibility(View.VISIBLE);
//                payBtn.setVisibility(View.INVISIBLE);
//            }
//        });

        imageSlider=findViewById(R.id.imageSlider);
        a=(TextView)findViewById(R.id.one);
        b=(TextView)findViewById(R.id.two);
//        c=(TextView)findViewById(R.id.three);
//        d=(TextView)findViewById(R.id.four);

        p=(TextView)findViewById(R.id.dam1);
        q=(TextView)findViewById(R.id.dam2);
//        r=(TextView)findViewById(R.id.dam3);
//        s=(TextView)findViewById(R.id.dam4);

        x=(TextView) findViewById(R.id.edit1);


        m=(TextView) findViewById(R.id.dam1);
        n=(TextView) findViewById(R.id.dam2);
//        o=(TextView) findViewById(R.id.dam3);
//        k=(TextView) findViewById(R.id.dam4);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setTextColor(Color.rgb(4, 148, 62));
                q.setTextColor(Color.GRAY);
//                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);

                a.setTextColor(Color.rgb(4, 148, 62));
                b.setTextColor(Color.BLACK);
//                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);

                String s=m.getText().toString();
                x.setText(s);




            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setTextColor(Color.GRAY);
                q.setTextColor(Color.rgb(4, 148, 62));
//                r.setTextColor(Color.GRAY);
//                s.setTextColor(Color.GRAY);


                a.setTextColor(Color.BLACK);
                b.setTextColor(Color.rgb(4, 148, 62));
//                c.setTextColor(Color.BLACK);
//                d.setTextColor(Color.BLACK);

                String t=n.getText().toString();
                x.setText(t);



            }
        });

//        c.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p.setTextColor(Color.GRAY);
//                q.setTextColor(Color.GRAY);
////                r.setTextColor(Color.rgb(4, 148, 62));
////                s.setTextColor(Color.GRAY);
//
//
//                a.setTextColor(Color.BLACK);
//                b.setTextColor(Color.BLACK);
////                c.setTextColor(Color.rgb(4, 148, 62));
////                d.setTextColor(Color.BLACK);
//
//                String u=o.getText().toString();
//                x.setText(u);
//
//            }
//        });

//        d.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                p.setTextColor(Color.GRAY);
//                q.setTextColor(Color.GRAY);
////                r.setTextColor(Color.GRAY);
////                s.setTextColor(Color.rgb(4, 148, 62));
//
//
//                a.setTextColor(Color.BLACK);
//                b.setTextColor(Color.BLACK);
////                c.setTextColor(Color.BLACK);
////                d.setTextColor(Color.rgb(4, 148, 62));
//
//                String v=k.getText().toString();
//                x.setText(v);
//
//            }
//        });


        ArrayList<SlideModel> slideModels =new ArrayList<>();



        slideModels.add(new SlideModel("https://img.freepik.com/premium-vector/milk-carton-boxes-icon-illustration-isolated_385450-85.jpg?w=2000", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://img.freepik.com/free-vector/carton-milk-with-happy-face_1308-107979.jpg?w=2000", ScaleTypes.FIT));


        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

//    @Override
//    public void onBackPressed()
//    {
//        // code here to show dialog
//        super.onBackPressed();  // optional depending on your needs
//    }

//    public void pay(View v) {
//        TextView txtclose, add2;
//        ProgressBar progress;
//        ImageView back;
//
//        Button btnFollow;
//        paymentDialog.setContentView(R.layout.dialog);
//
//        paymentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        paymentDialog.show();
//
//    }


    public void status(View v) {


        myDialog.setContentView(R.layout.dialog);

        st =(TextView) myDialog.findViewById(R.id.status);

        String value = name.getText().toString().trim();
        String value1 = mobile.getText().toString().trim();
        String value2 = address.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("value", value);
        editor.putString("x", value1);

        editor.putString("y", value2);
        editor.apply();



        String courseName = product.getText().toString();
        String courseDesc = quantity.getText().toString();
        String courseDuration = amountEdt.getText().toString();
        if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {

            return;
        }

        // calling a method to save our course.
        saveCourse(courseName, courseDesc, courseDuration);

        String getName =name.getText().toString();
        String getEmail=mobile.getText().toString();
        String getAddress =address.getText().toString();
        String getProduct=product.getText().toString();
        String getAmount=amountEdt.getText().toString();


        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("Name",getName);
        hashMap.put("Mobile",getEmail);
        hashMap.put("Address",getAddress);
        hashMap.put("Product",getProduct);
        hashMap.put("Amount",getAmount);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        String userId = databaseReference.push().getKey();





        databaseReference.child("Users")
                .child((userId))
                .setValue(hashMap);

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                paymentDialog.dismiss();
                selection2.super.onBackPressed();


            }
        });

    }



//
//    public void statusError(View v) {
//        TextView stEr;
//
//        myDialog.setContentView(R.layout.dialogfai);
//
//        stEr =(TextView) myDialog.findViewById(R.id.statusError);
//
//        stEr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDialog.dismiss();
//                FailureDialog.dismiss();
//                selection.super.onBackPressed();
//            }
//        });
//
//    }









    public void ShowPopup(View v) {
        TextView txtclose,add2,cod;
        ProgressBar progress;
        ImageView back;

        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);

        back =(ImageView) myDialog.findViewById(R.id.arrow);
        cod =(TextView) myDialog.findViewById(R.id.cod);

//        cod.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(getApplicationContext(),successScreen.class);
////
////                startActivity(intent);
//
//                myDialog.dismiss();
//
//                paymentDialog.show();
//
////                String courseName = product.getText().toString();
////                String courseDesc = quantity.getText().toString();
////                String courseDuration = amountEdt.getText().toString();
////                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
////
////                    return;
////                }
////
////                // calling a method to save our course.
////                saveCourse(courseName, courseDesc, courseDuration);
//
//
//
//            }
//
//
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });



        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        progress =(ProgressBar) myDialog.findViewById(R.id.progressbar);

        add2 = (TextView) myDialog.findViewById(R.id.textadd);
//        amountEdt1 = (EditText) myDialog.findViewById(R.id.edit1);
//

        String no= amountEdt.getText().toString();
        add2.setText(no);

        name = (EditText) myDialog.findViewById(R.id.entername);



        mobile = (EditText) myDialog.findViewById(R.id.entern0);

        address = (EditText) myDialog.findViewById(R.id.enteradd);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            product.setText(intent.getStringExtra(EXTRA_COURSE_NAME));
            quantity.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            amountEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }





        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                progress.setVisibility(View.VISIBLE);
                txtclose.setVisibility(View.GONE);




//                String courseName = name.getText().toString();
//                String courseDesc = product.getText().toString();
//                String courseDuration = amountEdt.getText().toString();
//                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {
//
//                    return;
//                }
//                // calling a method to save our course.
//                saveCourse(courseName, courseDesc, courseDuration);
//                myDialog.dismiss();


                // on below line we are getting
                // amount that is entered by user.
                String samount = amountEdt.getText().toString();

                // rounding off the amount.
                int amount = Math.round(Float.parseFloat(samount) * 100);

                // initialize Razorpay account.
                Checkout checkout = new Checkout();

                // set your id as below
                checkout.setKeyID("rzp_live_3B17Ixk1cDL2Zz");




                // set image
                checkout.setImage(R.drawable.milk1rem);

                // initialize json object
                JSONObject object = new JSONObject();
                try {
                    // to put name
                    object.put("name", "Milk Aggregator");

                    // put description
                    object.put("description", "Test payment");

                    // to set theme color
                    object.put("theme.color", "#23a847");

                    // put the currency
                    object.put("currency", "INR");

                    // put amount
                    object.put("amount", amount);

                    // put mobile number
                    object.put("prefill.contact", "");

                    // put email
                    object.put("prefill.email", "");








                    // open razorpay to checkout activity
                    checkout.open(selection2.this, object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    @Override
    public void onPaymentSuccess(String s) {

        final ProgressBar progressBar =  findViewById(R.id.progressbarforotp);

//        progressBar.setVisibility(View.GONE);
//        payBtn.setVisibility(View.VISIBLE);
//
//
//
//        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        myDialog.dismiss();
//
//        Intent intent = new Intent(getApplicationContext(),successScreen.class);
//
//        startActivity(intent);
        paymentDialog.show();

        //        Intent intent = new Intent(getApplicationContext(),homeFragment.class);
//        startActivity(intent);

//        AppDatabase db  = AppDatabase.getDbInstance(this.getApplicationContext());
//
//        String firstName=name.getText().toString();
//        String lastName=amountEdt.getText().toString();
//
//        Userx user = new Userx();
//        user.firstName = firstName;
//        user.lastName = lastName;
//        db.userDao().insertUser(user);
//
//        finish();

        String value = name.getText().toString().trim();
        String value1 = mobile.getText().toString().trim();
        String value2 = address.getText().toString().trim();
        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("value", value);
        editor.putString("x", value1);

        editor.putString("y", value2);
        editor.apply();

        String courseName = product.getText().toString();
        String courseDesc = quantity.getText().toString();
        String courseDuration = amountEdt.getText().toString();
        if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty()) {

            return;
        }

        // calling a method to save our course.
        saveCourse(courseName, courseDesc, courseDuration);




        String getName =name.getText().toString();
        String getEmail=mobile.getText().toString();
        String getAddress =address.getText().toString();
        String getProduct=product.getText().toString();
        String getAmount=amountEdt.getText().toString();


        HashMap<String,Object>hashMap=new HashMap<>();
        hashMap.put("Name",getName);
        hashMap.put("Mobile",getEmail);
        hashMap.put("Address",getAddress);
        hashMap.put("Product",getProduct);
        hashMap.put("Amount",getAmount);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        String userId = databaseReference.push().getKey();





        databaseReference.child("Users")
                .child((userId))
                .setValue(hashMap);

//        databaseReference.child("User")
//                .child((getName))
//
//                .setValue(hashMap);


    }

    @Override
    public void onPaymentError(int i, String s) {

        final ProgressBar progressBar =  findViewById(R.id.progressbarforotp);
//
//        progressBar.setVisibility(View.GONE);
//        payBtn.setVisibility(View.VISIBLE);
//
//        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
        myDialog.dismiss();
//
//        Intent intent2 = new Intent(getApplicationContext(),failureScreen.class);
//
//        startActivity(intent2);

//

        FailureDialog.show();


    }


    private void saveCourse(String courseName, String courseDescription, String courseDuration) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_COURSE_NAME, courseName);
        data.putExtra(EXTRA_DESCRIPTION, courseDescription);
        data.putExtra(EXTRA_DURATION, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data

    }


}