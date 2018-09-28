package com.example.yunwen.myapplication.dao;

/**
 * Created by Yunwen on 2/15/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class XModuleRepo {
    private XModuleHelper XModuleHelper;

    public XModuleRepo(Context context){
        XModuleHelper =new XModuleHelper(context);
    }

    public int insert(XModule XModule){
        //Open Db，write data
        SQLiteDatabase db= XModuleHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(XModule.KEY_time, XModule.time);
        values.put(XModule.KEY_content, XModule.content);
        values.put(XModule.KEY_topic, XModule.topic);
        values.put(XModule.KEY_lectureId, XModule.lectureId);
        values.put(XModule.KEY_title, XModule.title);
        values.put(XModule.KEY_coverImageUri, XModule.coverImageUri);
        values.put(XModule.KEY_teacher, XModule.teacher);
        //
        long algorithm_Id=db.insert(XModule.TABLE,null,values);
        db.close();
        return (int)algorithm_Id;
    }

    public void delete(int algorithm_Id){
        SQLiteDatabase db= XModuleHelper.getWritableDatabase();
        db.delete(XModule.TABLE, XModule.KEY_ID+"=?", new String[]{String.valueOf(algorithm_Id)});
        db.close();
    }
    public void update(XModule XModule){
        SQLiteDatabase db= XModuleHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(XModule.KEY_time, XModule.time);
        values.put(XModule.KEY_content, XModule.content);
        values.put(XModule.KEY_topic, XModule.topic);
        values.put(XModule.KEY_lectureId, XModule.lectureId);
        values.put(XModule.KEY_title, XModule.title);
        values.put(XModule.KEY_coverImageUri, XModule.coverImageUri);
        values.put(XModule.KEY_teacher, XModule.teacher);
        db.update(XModule.TABLE, values, XModule.KEY_ID + "=?", new String[]{String.valueOf(XModule.dbId)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAlgorithmList(){
        SQLiteDatabase db= XModuleHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModule.KEY_ID+","+
                XModule.KEY_content+","+
                XModule.KEY_topic+","+
                XModule.KEY_lectureId+","+
                XModule.KEY_title+","+
                XModule.KEY_coverImageUri+","+
                XModule.KEY_teacher+","+
                XModule.KEY_time +" FROM "+ XModule.TABLE;
        ArrayList<HashMap<String,String>> algorithmList=new ArrayList<>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> algorithm=new HashMap<>();
                algorithm.put("id",cursor.getString(cursor.getColumnIndex(XModule.KEY_ID)));
                algorithm.put("topic",cursor.getString(cursor.getColumnIndex(XModule.KEY_topic)));
                algorithm.put("lectureId",cursor.getString(cursor.getColumnIndex(XModule.KEY_lectureId)));
                algorithm.put("title",cursor.getString(cursor.getColumnIndex(XModule.KEY_title)));
                algorithm.put("coverImageUri",cursor.getString(cursor.getColumnIndex(XModule.KEY_coverImageUri)));
                algorithm.put("teacher",cursor.getString(cursor.getColumnIndex(XModule.KEY_teacher)));
                algorithm.put("content",cursor.getString(cursor.getColumnIndex(XModule.KEY_content)));
                algorithmList.add(algorithm);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithmList;
    }

    public XModule getColumnById(int Id){
        SQLiteDatabase db= XModuleHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModule.KEY_ID + "," +
                XModule.KEY_content + "," +
                XModule.KEY_topic + "," +
                XModule.KEY_lectureId + "," +
                XModule.KEY_title + "," +
                XModule.KEY_coverImageUri + "," +
                XModule.KEY_teacher + "," +
                XModule.KEY_time +
                " FROM " + XModule.TABLE
                + " WHERE " +
                XModule.KEY_ID + "=?";
        int iCount=0;
        XModule mefavoriteHistory =new XModule();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                mefavoriteHistory.dbId =cursor.getInt(cursor.getColumnIndex(XModule.KEY_ID));
                mefavoriteHistory.content =cursor.getString(cursor.getColumnIndex(XModule.KEY_content));
                mefavoriteHistory.topic  =cursor.getString(cursor.getColumnIndex(XModule.KEY_topic));
                mefavoriteHistory.lectureId  =cursor.getString(cursor.getColumnIndex(XModule.KEY_lectureId));
                mefavoriteHistory.title  =cursor.getString(cursor.getColumnIndex(XModule.KEY_title));
                mefavoriteHistory.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModule.KEY_coverImageUri));
                mefavoriteHistory.teacher  =cursor.getString(cursor.getColumnIndex(XModule.KEY_teacher));
                mefavoriteHistory.time =cursor.getInt(cursor.getColumnIndex(XModule.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mefavoriteHistory;
    }

    public XModule getValueByKey(int Id){
        SQLiteDatabase db= XModuleHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModule.KEY_ID + "," +
                XModule.KEY_content + "," +
                XModule.KEY_topic + "," +
                XModule.KEY_lectureId + "," +
                XModule.KEY_title + "," +
                XModule.KEY_coverImageUri + "," +
                XModule.KEY_teacher + "," +
                XModule.KEY_time +
                " FROM " + XModule.TABLE
                + " WHERE " +
                XModule.KEY_ID + "=?";

        int iCount=0;

        XModule XModule =new XModule();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                XModule.dbId =cursor.getInt(cursor.getColumnIndex(XModule.KEY_ID));
                XModule.content =cursor.getString(cursor.getColumnIndex(XModule.KEY_content));
                XModule.topic  =cursor.getString(cursor.getColumnIndex(XModule.KEY_topic));
                XModule.lectureId  =cursor.getString(cursor.getColumnIndex(XModule.KEY_lectureId));
                XModule.title  =cursor.getString(cursor.getColumnIndex(XModule.KEY_title));
                XModule.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModule.KEY_coverImageUri));
                XModule.teacher  =cursor.getString(cursor.getColumnIndex(XModule.KEY_teacher));
                XModule.time =cursor.getInt(cursor.getColumnIndex(XModule.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return XModule;
    }

    public XModule getColumnByTopic(String topic){
        SQLiteDatabase db= XModuleHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModule.KEY_ID + "," +
                XModule.KEY_content + "," +
                XModule.KEY_topic + "," +
                XModule.KEY_lectureId + "," +
                XModule.KEY_title + "," +
                XModule.KEY_coverImageUri + "," +
                XModule.KEY_teacher + "," +
                XModule.KEY_time +
                " FROM " + XModule.TABLE
                + " WHERE " +
                XModule.KEY_topic + "=?";
        int iCount=0;
        XModule searchHistory =new XModule();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(topic)});
        if(cursor.moveToFirst()){
            do{
                searchHistory.dbId =cursor.getInt(cursor.getColumnIndex(XModule.KEY_ID));
                searchHistory.content =cursor.getString(cursor.getColumnIndex(XModule.KEY_content));
                searchHistory.topic  =cursor.getString(cursor.getColumnIndex(XModule.KEY_topic));
                searchHistory.lectureId  =cursor.getString(cursor.getColumnIndex(XModule.KEY_lectureId));
                searchHistory.title  =cursor.getString(cursor.getColumnIndex(XModule.KEY_title));
                searchHistory.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModule.KEY_coverImageUri));
                searchHistory.teacher  =cursor.getString(cursor.getColumnIndex(XModule.KEY_teacher));
                searchHistory.time =cursor.getInt(cursor.getColumnIndex(XModule.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return searchHistory;
    }
}
