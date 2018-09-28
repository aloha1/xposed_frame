package com.example.yunwen.myapplication.dao;

/**
 * Created by Yunwen on 2/15/2016.
 */

//this is the database for storing favorite list
public class XModuleLog {
    //table name
    public static final String TABLE="XModuleLog";
    //table id
    public static final String KEY_ID="id";
    public static final String KEY_topic="topic";
    public static final String KEY_content="content";
    public static final String KEY_time ="time";

    public static final String KEY_lectureId="lectureId";
    public static final String KEY_title="title";
    public static final String KEY_coverImageUri="coverImageUri";
    public static final String KEY_teacher="teacher";
    //property
    public int dbId;
    public String topic;
    public String content;
    public String title;
    public String lectureId;
    public String teacher;
    public String coverImageUri;
    public int time;
}
