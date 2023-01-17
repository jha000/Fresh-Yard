package com.example.milkaggregatorapplication

import androidx.lifecycle.ViewModelProvider.get
import com.example.milkaggregatorapplication.CourseDatabase
import com.example.milkaggregatorapplication.ViewModal
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.milkaggregatorapplication.R
import com.example.milkaggregatorapplication.CourseRVAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milkaggregatorapplication.CourseModal

class orderFragment : Fragment() {
    //    UserListAdapter userListAdapter;
    //
    //
    //    ArrayList<Userx> userList;
    //    RecyclerView recyclerView;
    //
    var db: CourseDatabase? = null

    //////    DatabaseReference database;
    //    MyAdapter myAdapter;
    //    ArrayList<User> list;
    //    Button rb;
    private var coursesRV: RecyclerView? = null
    private var viewmodal: ViewModal? = null

    //    Dialog myDialog;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        coursesRV = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        coursesRV!!.layoutManager = LinearLayoutManager(activity)
        coursesRV!!.setHasFixedSize(true)

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
        val adapter = CourseRVAdapter()

        // setting adapter class for recycler view.
        coursesRV!!.adapter = adapter
        //
//        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal::class.java)

        // below line is use to get all the courses from view modal.
        viewmodal!!.allCourses.observe(activity!!) { models -> // when the data is changed in our models we are
            // adding that list to our adapter class.
            adapter.submitList(models)
        }

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
        return view
    }

    companion object {
        private const val ADD_COURSE_REQUEST = 1
        private const val EDIT_COURSE_REQUEST = 2
    }
}