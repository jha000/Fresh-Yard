package com.example.milkaggregatorapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// below line is for setting table name.
@Entity(tableName = "course_table")
class CourseModal     // below line we are creating constructor class.
// inside constructor class we are not passing
// our id because it is incrementing automatically
    (// on below line we are creating
    // getter and setter methods.
    // below line is a variable
    // for course name.
    var courseName: String, // below line is use for
    // course description.
    var courseDescription: String, // below line is use
    // for course duration.
    var courseDuration: String
) {
    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true) // variable for our id.
    var id = 0

}