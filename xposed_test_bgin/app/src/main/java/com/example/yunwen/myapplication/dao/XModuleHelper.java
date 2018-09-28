package com.example.yunwen.myapplication.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yunwen on 2/15/2016.
 */
public class XModuleHelper extends SQLiteOpenHelper {
    //Db version
    private static final int DATABASE_VERSION = 1;
    //name of the db
    private static final String DATABASE_NAME = "me.xmodule.db";

    public XModuleHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ XModule.TABLE+"("
                + XModule.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + XModule.KEY_topic+" TEXT, "
                + XModule.KEY_time +" INTEGER, "
                + XModule.KEY_lectureId+" TEXT, "
                + XModule.KEY_title+" TEXT, "
                + XModule.KEY_coverImageUri+" TEXT, "
                + XModule.KEY_teacher+" TEXT, "
                + XModule.KEY_content+" TEXT)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if the table exist, delete it
        db.execSQL("DROP TABLE IF EXISTS "+ XModule.TABLE);
        //recreate the table
        onCreate(db);
    }
}
