package com.example.yunwen.myapplication.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * this is the database helper for storing XModule log list
 * */
public class XModuleLogHelper extends SQLiteOpenHelper {
    //Db version
    private static final int DATABASE_VERSION = 1;
    //name of the db
    private static final String DATABASE_NAME = "me.xmodule.db";

    public XModuleLogHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ XModuleLog.TABLE+"("
                + XModuleLog.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + XModuleLog.KEY_topic+" TEXT, "
                + XModuleLog.KEY_time +" INTEGER, "
                + XModuleLog.KEY_lectureId+" TEXT, "
                + XModuleLog.KEY_title+" TEXT, "
                + XModuleLog.KEY_coverImageUri+" TEXT, "
                + XModuleLog.KEY_teacher+" TEXT, "
                + XModuleLog.KEY_content+" TEXT)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if the table exist, delete it
        db.execSQL("DROP TABLE IF EXISTS "+ XModuleLog.TABLE);
        //recreate the table
        onCreate(db);
    }
}
