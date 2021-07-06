package com.example.covaxin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class IDproofDatabaseOperation extends SQLiteOpenHelper {
    public static final String PERSON_DETAILS = "person_details";
    public static final String P_ID = "p_id";
    public static final String P_NAME = "p_name";
    public static final String ID_NUMBER = "ID_number";
    public static final String ID_TYPE = "ID_type";
    public static final String YEAR = "year";
    public static final String GENDER = "Gender";
    public static  final String APPOINMENT_PERSON="Appoinment_person";
    public static final String A_ID="A_id";
    public static final String MOBILE="Mobile";
    public static final String TOKEN_NO="Token_no";
    String appoinmenttable = "CREATE TABLE " + APPOINMENT_PERSON + "(" +
            A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MOBILE + " TEXT NOT NULL," +
            TOKEN_NO + "TEXT  NOT NULL" +
            ");";
    String createtableStatement = "CREATE TABLE " + PERSON_DETAILS + "(" +
            P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            P_NAME + " TEXT NOT NULL," +
            ID_NUMBER + "TEXT  NOT NULL," +
            ID_TYPE + " TEXT NOT NULL," +
            YEAR + " TEXT NOT NULL," +
            GENDER + " TEXT NOT NULL" +
            ");";

    public IDproofDatabaseOperation(@Nullable Context context) {
        super(context, "vaccine.db", null, 1);
    }
    @Override
    //this is called at the first time when the database has been created .
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(appoinmenttable);
        db.execSQL(createtableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Appoinment_person");
        db.execSQL("drop table if exists person_details");
    }
    public boolean addUsers(appoinmentclass appoinment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOBILE,appoinment.getMobile());
        contentValues.put(TOKEN_NO,appoinment.getToken_no());
        long result = db.insert(APPOINMENT_PERSON,null,contentValues);
        if(result == -1){
            return false;
        }
        return true;


    }
    public boolean addOne(String ID_name,String ID_num,String type,int year, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(P_NAME, ID_name);
        contentValues.put(ID_NUMBER, ID_num);
        contentValues.put(ID_TYPE, type);
        contentValues.put(YEAR, year);
        contentValues.put(GENDER, gender);
        long result = db.insert(PERSON_DETAILS, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }

}
