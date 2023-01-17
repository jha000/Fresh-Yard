package com.example.milkaggregatorapplication


import android.app.Application
import androidx.lifecycle.LiveData

import android.os.AsyncTask

class CourseRepository(application: Application?) {
    // below line is the create a variable
    // for dao and list for all courses.
    private val dao: Dao

    // below method is to read all the courses.
    val allCourses: LiveData<List<CourseModal?>?>?

    // creating a constructor for our variables
    // and passing the variables to it.
    init {
        val database = CourseDatabase.getInstance(application)
        dao = database.Dao()
        allCourses = dao.allCourses
    }

    // creating a method to insert the data to our database.
    fun insert(model: CourseModal?) {
        InsertCourseAsyncTask(dao)
    }

    // creating a method to update data in database.
    fun update(model: CourseModal?) {
        UpdateCourseAsyncTask(dao)
    }

    // creating a method to delete the data in our database.
    fun delete(model: CourseModal?) {
        DeleteCourseAsyncTask(dao)
    }

    // below is the method to delete all the courses.
    fun deleteAllCourses() {
        DeleteAllCoursesAsyncTask(dao)
    }

    // we are creating a async task method to insert new course.
    private class InsertCourseAsyncTask(private val dao: Dao) {
        protected  fun doInBackground(vararg model: CourseModal): Void? {
            // below line is use to insert our modal in dao.
            dao.insert(model[0])
            return null
        }
    }

    // we are creating a async task method to update our course.
    private class UpdateCourseAsyncTask(private val dao: Dao)  {
        protected  fun doInBackground(vararg models: CourseModal): Void? {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0])
            return null
        }
    }

    // we are creating a async task method to delete course.
    private class DeleteCourseAsyncTask(private val dao: Dao)  {
        protected  fun doInBackground(vararg models: CourseModal): Void? {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0])
            return null
        }
    }

    // we are creating a async task method to delete all courses.
    private class DeleteAllCoursesAsyncTask(private val dao: Dao) {
        protected  fun doInBackground(vararg voids: Void): Void? {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllCourses()
            return null
        }
    }
}