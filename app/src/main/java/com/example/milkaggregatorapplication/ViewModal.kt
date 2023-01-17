package com.example.milkaggregatorapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.milkaggregatorapplication.CourseRepository
import androidx.lifecycle.LiveData
import com.example.milkaggregatorapplication.CourseModal

class ViewModal(application: Application) : AndroidViewModel(application) {
    // creating a new variable for course repository.
    private val repository: CourseRepository

    // below method is to get all the courses in our list.
    // below line is to create a variable for live
    // data where all the courses are present.
    val allCourses: LiveData<List<CourseModal>>

    // constructor for our view modal.
    init {
        repository = CourseRepository(application)
        allCourses = repository.allCourses
    }

    // below method is use to insert the data to our repository.
    fun insert(model: CourseModal?) {
        repository.insert(model)
    }

    // below line is to update data in our repository.
    fun update(model: CourseModal?) {
        repository.update(model)
    }

    // below line is to delete the data in our repository.
    fun delete(model: CourseModal?) {
        repository.delete(model)
    }

    // below method is to delete all the courses in our list.
    fun deleteAllCourses() {
        repository.deleteAllCourses()
    }
}