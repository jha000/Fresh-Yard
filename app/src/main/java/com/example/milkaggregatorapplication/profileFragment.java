package com.example.milkaggregatorapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileFragment extends Fragment{

    ImageView cover,nameEdit,phoneEdit,emailEdit,addEdit;
    ImageView fav;
    TextView logout,name,phone,address,email;

    String z;

    BottomSheetDialog myDialog,bphoneDialog,bmailDialog,baddressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        myDialog = new BottomSheetDialog(getActivity());
        bphoneDialog = new BottomSheetDialog(getActivity());
        bmailDialog = new BottomSheetDialog(getActivity());
        baddressDialog = new BottomSheetDialog(getActivity());


        cover = (ImageView) view.findViewById(R.id.profile_image);
        fav = (ImageView) view.findViewById(R.id.floatingActionButton);

        name = (TextView) view.findViewById(R.id.name);
        phone = (TextView) view.findViewById(R.id.number);
        email = (TextView) view.findViewById(R.id.email);
        address = (TextView) view.findViewById(R.id.address);


        nameEdit = (ImageView) view.findViewById(R.id.nameEdit);

        phoneEdit = (ImageView) view.findViewById(R.id.phoneEdit);

        emailEdit = (ImageView) view.findViewById(R.id.emailEdit);

        addEdit = (ImageView) view.findViewById(R.id.addEdit);

        nameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText one;
                Button b1;

                myDialog.setContentView(R.layout.bottom);

                one =(EditText) myDialog.findViewById(R.id.one);
                b1 =(Button) myDialog.findViewById(R.id.b1);
                one.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String x=one.getText().toString();
                        name.setText(x);

                        String value = one.getText().toString().trim();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("myKey", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value", value);
                        editor.apply();

                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        myDialog.dismiss();
                    }
                });

                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

            }
        });




        phoneEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText too;

                Button b2;

                bphoneDialog.setContentView(R.layout.bphone);

                too =(EditText) bphoneDialog.findViewById(R.id.too);
                b2 =(Button) bphoneDialog.findViewById(R.id.b2);
                too.requestFocus();



                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                b2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String x=too.getText().toString();
                        phone.setText(x);

                        String value = too.getText().toString().trim();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("myKey", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value1", value);
                        editor.apply();

                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        bphoneDialog.dismiss();
                    }
                });
//


                bphoneDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bphoneDialog.show();

            }
        });



        emailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText three;
                Button b3;

                bmailDialog.setContentView(R.layout.bemail);

                three =(EditText) bmailDialog.findViewById(R.id.three);
                b3 =(Button) bmailDialog.findViewById(R.id.b3);
                three.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String x=three.getText().toString();
                        email.setText(x);

                        String value = three.getText().toString().trim();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("myKey", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value2", value);
                        editor.apply();

                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        bmailDialog.dismiss();
                    }
                });

                bmailDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bmailDialog.show();

            }
        });


        addEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText four;
                Button b4;

                baddressDialog.setContentView(R.layout.baddress);

                four =(EditText) baddressDialog.findViewById(R.id.four);
                b4 =(Button) baddressDialog.findViewById(R.id.b4);
                four.requestFocus();

                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                b4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String x=four.getText().toString();
                        address.setText(x);

                        String value = four.getText().toString().trim();
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("myKey", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("value3", value);
                        editor.apply();

                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        baddressDialog.dismiss();
                    }
                });

                baddressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                baddressDialog.show();

            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("myKey", getActivity().MODE_PRIVATE);
        String value = sharedPreferences.getString("value","");
        name.setText(value);
        String value1 = sharedPreferences.getString("value1","");
        phone.setText(value1);
        String value2 = sharedPreferences.getString("value2","");
        email.setText(value2);
        String value3 = sharedPreferences.getString("value3","");
        address.setText(value3);


        logout = (TextView) view.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FirebaseAuth user = FirebaseAuth.getInstance();
                user.signOut();
                Intent intent=new Intent(getActivity(),MainActivity.class);
                startActivity(intent);

                Toast.makeText(getActivity().getApplicationContext(),"Logout Successful", Toast.LENGTH_SHORT).show();
            }
        });


        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ImagePicker.with(profileFragment.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri selectedImg = data.getData();

        cover.setImageURI(selectedImg);

    }


}