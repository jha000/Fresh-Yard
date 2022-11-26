package com.example.milkaggregatorapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class orderFragment extends Fragment {

//    UserListAdapter userListAdapter;
//
//
//    ArrayList<Userx> userList;
//    RecyclerView recyclerView;
//
CourseDatabase db;
//////    DatabaseReference database;
//    MyAdapter myAdapter;
//    ArrayList<User> list;
//    Button rb;

    private RecyclerView coursesRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private ViewModal viewmodal;

//    Dialog myDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_order, container, false);

        coursesRV = (RecyclerView) view.findViewById(R.id.recyclerView);


        coursesRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        coursesRV.setHasFixedSize(true);

//        myDialog = new Dialog(getActivity());



//        FloatingActionButton fab =(FloatingActionButton) view.findViewById(R.id.idFABAdd);

//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // starting a new activity for adding a new course
//                // and passing a constant value in it.
//                Intent intent = new Intent(getActivity(), selection.class);
//                startActivityForResult(intent, ADD_COURSE_REQUEST);
//            }
//        });

        // initializing adapter for recycler view.
        final CourseRVAdapter adapter = new CourseRVAdapter();

        // setting adapter class for recycler view.
        coursesRV.setAdapter(adapter);
//
//        // passing a data from view modal.
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

//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        CourseDatabase db = CourseDatabase.getInstance(getContext());
//
//
//
//        List<CourseModal> userList =db.Dao().getAllUsers();
//
//        userList = new ArrayList<>();

//        userListAdapter = new UserListAdapter(getActivity());
//
//        recyclerView.setAdapter(userListAdapter);
//
////
//
//
//        userListAdapter.setUserList(userList);
//
//


        return view;

    }


}