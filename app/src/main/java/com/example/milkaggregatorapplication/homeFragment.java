package com.example.milkaggregatorapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;
import java.util.List;


public class homeFragment extends Fragment {

    ImageSlider imageSlider;
    Intent intent,intent2,intent3;
    Button button,button2,button3,button4;
    TextView refer;
    private ViewModal viewmodal;
    private static final int EDIT_COURSE_REQUEST = 2;
    private static final int ADD_COURSE_REQUEST = 1;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);


//
//
//        intent3 = new Intent(getActivity(), MainActivity2.class);
//        final TextView refer = (TextView) view.findViewById(R.id.re);

        intent = new Intent(getActivity(), selection.class);
        final Button button = (Button) view.findViewById(R.id.buy1);


        intent2 = new Intent(getActivity(), selection2.class);
        final Button button2 = (Button) view.findViewById(R.id.small);


        final Button button3 = (Button) view.findViewById(R.id.int1);


        final Button button4 = (Button) view.findViewById(R.id.int2);

//        refer.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivity(intent3);
//            }
//        });



        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivityForResult(intent, ADD_COURSE_REQUEST);


            }
        });

        final CourseRVAdapter adapter = new CourseRVAdapter();

        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCourses().observe(getActivity(), new Observer<List<CourseModal>>() {
            @Override
            public void onChanged(List<CourseModal> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(intent2, ADD_COURSE_REQUEST);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Thank You for the response", Toast.LENGTH_SHORT).show();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(),"Thank You for the response", Toast.LENGTH_SHORT).show();

            }
        });

        imageSlider=view.findViewById(R.id.imageSlider);

        ArrayList<SlideModel> slideModels =new ArrayList<>();

        slideModels.add(new SlideModel("https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://t3.ftcdn.net/jpg/01/63/11/76/360_F_163117648_dau3cqbA1wg0RpH9Bw2F9ZGDMQQ4yKdR.jpg", ScaleTypes.FIT));



        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        return view;
    }
//
//
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_COURSE_REQUEST ) {

            if (resultCode == Activity.RESULT_OK){

            String courseName = data.getStringExtra(selection.EXTRA_COURSE_NAME);
            String courseDescription = data.getStringExtra(selection.EXTRA_DESCRIPTION);
            String courseDuration = data.getStringExtra(selection.EXTRA_DURATION);
//                String namex = data.getStringExtra(selection.EXTRA_N);
            CourseModal model = new CourseModal(courseName, courseDescription, courseDuration);
            viewmodal.insert(model);}


    }}


}