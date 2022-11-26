//package com.example.milkaggregatorapplication.db;
//
//import android.content.Context;
//
//import androidx.room.Database;
////import androidx.room.Room;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = {Userx.class}, version  = 1,exportSchema = false)
//public abstract class AppDatabase extends RoomDatabase {
//
//    public abstract UserDao userDao();
//
//    private static AppDatabase INSTANCE;
//
//    public synchronized  static AppDatabase getInstance(Context context) {
//
//        if(INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build();
//
//        }
//        return INSTANCE;
//    }
//}