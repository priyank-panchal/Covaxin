package com.example.covaxin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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
    public static final String APPOINTMENT_SCHEDULE = "Appointment_Schedule";
    public static final String APP_S_ID = "App_S_Id";
    public static final String NAME = "Name";
    public static final String FEE_TYPE = "Fee_Type";
    public static final String MIN_AGE = "Min_Age";
    public static final String SCHEDULE_DATE = "Schedule_date";
    public static final String AVAILABLE_CAPACITY = "Available_Capacity";
    public static final String AVAILABLE_CAPACITY_DOES_1 =  "Available_Does_1";
    public static final String AVAILABLE_CAPACITY_DOES_2 = "Available_Does_2";
    public static final String DOSE_1_STATUS = "Dose_1_Status";
    public static final String DOSE_2_STATUS = "Dose_2_Status";
    String appoinmenttable = "CREATE TABLE " + APPOINMENT_PERSON + "(" +
            A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            MOBILE + " TEXT NOT NULL," +
            TOKEN_NO + " TEXT NOT NULL" +
            ");";
    String createtableStatement = "CREATE TABLE " + PERSON_DETAILS + "(" +
            P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
            P_NAME + " TEXT NOT NULL," +
            ID_NUMBER + " TEXT  NOT NULL," +
            ID_TYPE + " TEXT NOT NULL," +
            YEAR + " TEXT NOT NULL," +
            GENDER + " TEXT NOT NULL" +
            ");";
    String ScheduleAppoinment = "CREATE TABLE " + APPOINTMENT_SCHEDULE + "(" +
            APP_S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NAME + " TEXT," +
            FEE_TYPE + " TEXT,"+
            MIN_AGE  + " INTEGER," +
            AVAILABLE_CAPACITY + " INTEGER," +
            SCHEDULE_DATE  + " TEXT," +
            AVAILABLE_CAPACITY_DOES_1  + " INTEGER," +
            AVAILABLE_CAPACITY_DOES_2  + " INTEGER," +
            DOSE_1_STATUS  + " TEXT," +
            DOSE_2_STATUS  + " TEXT" +
            ");";
    public IDproofDatabaseOperation(@Nullable Context context) {
        super(context, "vaccine.db", null, 1);
    }
    @Override
    //this is called at the first time when the database has been created .
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(appoinmenttable);
        db.execSQL(createtableStatement);
        db.execSQL(ScheduleAppoinment);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Appoinment_person");
        db.execSQL("drop table if exists person_details");
        db.execSQL("drop table if exists "+APPOINMENT_PERSON);
    }
    public boolean addUsers(appoinmentclass appoinment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MOBILE,appoinment.getMobile());
        contentValues.put(TOKEN_NO,appoinment.getToken_no());
        long result = db.insert(APPOINMENT_PERSON,null,contentValues);
        db.close();
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
        db.close();
        if (result == -1) {
            return false;
        }
        return true;
    }
    public boolean deleteOne(IDProofDetailsdb idProofDetailsdb){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+PERSON_DETAILS + " WHERE " + P_ID +" = " + idProofDetailsdb.getID();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
               cursor.close();
               db.close();
               return true;
        }
        cursor.close();
        db.close();
        return false;
    }
    public List<IDProofDetailsdb> getAllData(){
        List<IDProofDetailsdb> lst = new ArrayList<>();
        String query = "SELECT * FROM " +  PERSON_DETAILS;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String p_name = cursor.getString(1);
                String p_number = cursor.getString(2);
                String p_idtype = cursor.getString(3);
                int p_years = cursor.getInt(4);
                String p_gender = cursor.getString(5);
                IDProofDetailsdb idproof = new IDProofDetailsdb(id,p_name,p_number,p_idtype,p_years,p_gender);
                lst.add(idproof);
            }while(cursor.moveToNext());
        }
        else {
            Log.d("jay meldi ma ", "something goes wrong");
        }
        cursor.close();
        db.close();
        return lst;
    }
    public boolean AddSchedule(VaccineSlots vaccineSlots){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, vaccineSlots.getName());
        contentValues.put(FEE_TYPE,vaccineSlots.getFee_type() );
        contentValues.put(AVAILABLE_CAPACITY,vaccineSlots.getAvailable_capacity());
        contentValues.put(MIN_AGE,vaccineSlots.getMin_age_limit());
        contentValues.put(SCHEDULE_DATE,vaccineSlots.getDate());
        contentValues.put(AVAILABLE_CAPACITY_DOES_1, vaccineSlots.getAvailable_capacity_dose1());
        contentValues.put(AVAILABLE_CAPACITY_DOES_2,vaccineSlots.getAvailable_capacity_dose2());
        contentValues.put(DOSE_1_STATUS,vaccineSlots.getDose_1_status());
        contentValues.put(DOSE_2_STATUS,vaccineSlots.getDose_2_status());
        long result = db.insert(APPOINTMENT_SCHEDULE, null, contentValues);
        db.close();
        if(result == -1) {
            return false;
        }
        return true;
    }
}