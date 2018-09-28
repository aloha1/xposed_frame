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

public class XModuleLogRepo {
    private XModuleLogHelper XModuleLogHelper;

    public XModuleLogRepo(Context context){
        XModuleLogHelper =new XModuleLogHelper(context);
    }

    public int insert(XModuleLog XModuleLog){
        //Open Db，write data
        SQLiteDatabase db= XModuleLogHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(XModuleLog.KEY_time, XModuleLog.time);
        values.put(XModuleLog.KEY_content, XModuleLog.content);
        values.put(XModuleLog.KEY_topic, XModuleLog.topic);
        values.put(XModuleLog.KEY_lectureId, XModuleLog.lectureId);
        values.put(XModuleLog.KEY_title, XModuleLog.title);
        values.put(XModuleLog.KEY_coverImageUri, XModuleLog.coverImageUri);
        values.put(XModuleLog.KEY_teacher, XModuleLog.teacher);
        //
        long algorithm_Id=db.insert(XModuleLog.TABLE,null,values);
        db.close();
        return (int)algorithm_Id;
    }

    public void delete(int algorithm_Id){
        SQLiteDatabase db= XModuleLogHelper.getWritableDatabase();
        db.delete(XModuleLog.TABLE, XModuleLog.KEY_ID+"=?", new String[]{String.valueOf(algorithm_Id)});
        db.close();
    }
    public void update(XModuleLog XModuleLog){
        SQLiteDatabase db= XModuleLogHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(XModuleLog.KEY_time, XModuleLog.time);
        values.put(XModuleLog.KEY_content, XModuleLog.content);
        values.put(XModuleLog.KEY_topic, XModuleLog.topic);
        values.put(XModuleLog.KEY_lectureId, XModuleLog.lectureId);
        values.put(XModuleLog.KEY_title, XModuleLog.title);
        values.put(XModuleLog.KEY_coverImageUri, XModuleLog.coverImageUri);
        values.put(XModuleLog.KEY_teacher, XModuleLog.teacher);
        db.update(XModuleLog.TABLE, values, XModuleLog.KEY_ID + "=?", new String[]{String.valueOf(XModuleLog.dbId)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAlgorithmList(){
        SQLiteDatabase db= XModuleLogHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModuleLog.KEY_ID+","+
                XModuleLog.KEY_content+","+
                XModuleLog.KEY_topic+","+
                XModuleLog.KEY_lectureId+","+
                XModuleLog.KEY_title+","+
                XModuleLog.KEY_coverImageUri+","+
                XModuleLog.KEY_teacher+","+
                XModuleLog.KEY_time +" FROM "+ XModuleLog.TABLE;
        ArrayList<HashMap<String,String>> algorithmList=new ArrayList<>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> algorithm=new HashMap<>();
                algorithm.put("id",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_ID)));
                algorithm.put("topic",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_topic)));
                algorithm.put("lectureId",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_lectureId)));
                algorithm.put("title",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_title)));
                algorithm.put("coverImageUri",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_coverImageUri)));
                algorithm.put("teacher",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_teacher)));
                algorithm.put("content",cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_content)));
                algorithmList.add(algorithm);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithmList;
    }

    public XModuleLog getColumnById(int Id){
        SQLiteDatabase db= XModuleLogHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModuleLog.KEY_ID + "," +
                XModuleLog.KEY_content + "," +
                XModuleLog.KEY_topic + "," +
                XModuleLog.KEY_lectureId + "," +
                XModuleLog.KEY_title + "," +
                XModuleLog.KEY_coverImageUri + "," +
                XModuleLog.KEY_teacher + "," +
                XModuleLog.KEY_time +
                " FROM " + XModuleLog.TABLE
                + " WHERE " +
                XModuleLog.KEY_ID + "=?";
        int iCount=0;
        XModuleLog mefavoriteHistory =new XModuleLog();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                mefavoriteHistory.dbId =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_ID));
                mefavoriteHistory.content =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_content));
                mefavoriteHistory.topic  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_topic));
                mefavoriteHistory.lectureId  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_lectureId));
                mefavoriteHistory.title  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_title));
                mefavoriteHistory.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_coverImageUri));
                mefavoriteHistory.teacher  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_teacher));
                mefavoriteHistory.time =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mefavoriteHistory;
    }

    public XModuleLog getValueByKey(int Id){
        SQLiteDatabase db= XModuleLogHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModuleLog.KEY_ID + "," +
                XModuleLog.KEY_content + "," +
                XModuleLog.KEY_topic + "," +
                XModuleLog.KEY_lectureId + "," +
                XModuleLog.KEY_title + "," +
                XModuleLog.KEY_coverImageUri + "," +
                XModuleLog.KEY_teacher + "," +
                XModuleLog.KEY_time +
                " FROM " + XModuleLog.TABLE
                + " WHERE " +
                XModuleLog.KEY_ID + "=?";

        int iCount=0;

        XModuleLog XModuleLog =new XModuleLog();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                XModuleLog.dbId =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_ID));
                XModuleLog.content =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_content));
                XModuleLog.topic  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_topic));
                XModuleLog.lectureId  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_lectureId));
                XModuleLog.title  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_title));
                XModuleLog.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_coverImageUri));
                XModuleLog.teacher  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_teacher));
                XModuleLog.time =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return XModuleLog;
    }

    public XModuleLog getColumnByTopic(String topic){
        SQLiteDatabase db= XModuleLogHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                XModuleLog.KEY_ID + "," +
                XModuleLog.KEY_content + "," +
                XModuleLog.KEY_topic + "," +
                XModuleLog.KEY_lectureId + "," +
                XModuleLog.KEY_title + "," +
                XModuleLog.KEY_coverImageUri + "," +
                XModuleLog.KEY_teacher + "," +
                XModuleLog.KEY_time +
                " FROM " + XModuleLog.TABLE
                + " WHERE " +
                XModuleLog.KEY_topic + "=?";
        int iCount=0;
        XModuleLog searchHistory =new XModuleLog();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(topic)});
        if(cursor.moveToFirst()){
            do{
                searchHistory.dbId =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_ID));
                searchHistory.content =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_content));
                searchHistory.topic  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_topic));
                searchHistory.lectureId  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_lectureId));
                searchHistory.title  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_title));
                searchHistory.coverImageUri  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_coverImageUri));
                searchHistory.teacher  =cursor.getString(cursor.getColumnIndex(XModuleLog.KEY_teacher));
                searchHistory.time =cursor.getInt(cursor.getColumnIndex(XModuleLog.KEY_time));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return searchHistory;
    }
}
