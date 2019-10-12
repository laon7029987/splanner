package com.timeisall.mstudyplanner.registration.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.timeisall.mstudyplanner.registration.database.StudyDbSchema.UserTable;

public class StudyDbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "studyBase.db";

    public StudyDbHelper(Context context) {

        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserTable.NAME + "(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserTable.Cols.USERID + ", " +
                UserTable.Cols.USERPASSWORD + ", " +
                UserTable.Cols.USERGENDER + ", " +
                UserTable.Cols.USERGROUP + ", " +
                UserTable.Cols.USEREMAIL +
                        ")"
        );
                     /*
        db.execSQL("CREATE TABLE Wisdom (content text);");

        db.execSQL("CREATE TABLE cal(date text, content text);");

        db.execSQL("CREATE TABLE  subject( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "subject text, content text);");

        db.execSQL("CREATE TABLE mypage( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name text, category text, schhool text, goal text);");
        db.execSQL("CREATE TABLE mypage( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name text, category text, schhool text);");

        db.execSQL("CREATE TABLE  kor( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id, title, ddaydate, content);");

        db.execSQL("CREATE TABLE  memo( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id, num, content);");
        db.execSQL("CREATE TABLE  tasks( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id, lseb, sseb, regisDate);");
        db.execSQL("CREATE TABLE  today( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id, num, today, subject, task);");
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserTable.NAME);
        //db.execSQL("DROP TABLE IF EXISTS Wisdom");
        //db.execSQL("DROP TABLE IF EXISTS Dday");
        //db.execSQL("DROP TABLE IF EXISTS Dday");
        //db.execSQL("DROP TABLE IF EXISTS Dday");

        onCreate(db);
    }

}
