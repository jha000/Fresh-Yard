package com.example.milkaggregatorapplication.model

import com.example.milkaggregatorapplication.CourseModal.id
import com.example.milkaggregatorapplication.CourseModal.courseName
import com.example.milkaggregatorapplication.CourseModal.courseDescription
import com.example.milkaggregatorapplication.CourseModal.courseDuration
import com.example.milkaggregatorapplication.Dao.allCourses
import com.example.milkaggregatorapplication.Dao.insert
import com.example.milkaggregatorapplication.Dao.update
import com.example.milkaggregatorapplication.Dao.delete
import com.example.milkaggregatorapplication.Dao.deleteAllCourses
import com.example.milkaggregatorapplication.model.MessagesItem
import com.example.milkaggregatorapplication.model.ContactsItem
import androidx.room.Database
import com.example.milkaggregatorapplication.CourseModal
import androidx.room.RoomDatabase
import com.example.milkaggregatorapplication.CourseDatabase
import android.os.AsyncTask
import kotlin.jvm.Synchronized
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.milkaggregatorapplication.CourseDatabase.PopulateDbAsyncTask
import com.example.milkaggregatorapplication.CourseRVAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.milkaggregatorapplication.R
import android.content.Intent
import com.example.milkaggregatorapplication.orderDetails
import android.widget.TextView
import android.app.Application
import androidx.lifecycle.LiveData
import com.example.milkaggregatorapplication.CourseRepository.InsertCourseAsyncTask
import com.example.milkaggregatorapplication.CourseRepository.UpdateCourseAsyncTask
import com.example.milkaggregatorapplication.CourseRepository.DeleteCourseAsyncTask
import com.example.milkaggregatorapplication.CourseRepository.DeleteAllCoursesAsyncTask

class MsgObj {
    var messagingProduct: String? = null
    var messages: List<MessagesItem>? = null
    var contacts: List<ContactsItem>? = null
}