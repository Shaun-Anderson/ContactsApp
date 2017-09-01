package com.swander.shaun.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by Shaun on 8/26/2017.
 */

public class DatabaseReader extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SContacts.db";
    public static final String _ID = "ID";

    public static final String TABLE_NAME = "Contacts";
    public static final String COLUMN_NAME= "Name";
    public static final String COLUMN_NUMBER = "Number";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_ADDRESS = "Address";
    public static final String COLUMN_TAGS = "Tags";


    public DatabaseReader(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void DeleteDB()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void DB_CreateTable()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_NUMBER + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_ADDRESS + " TEXT," +
                    COLUMN_TAGS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public boolean DB_AddData(String name, String number, String email, String address, String tag)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,name);
        values.put(COLUMN_NUMBER,number);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_ADDRESS,address);
        values.put(COLUMN_TAGS,tag);

        long result = db.insert(TABLE_NAME,null,values);
        if(result == -1) {
            Log.d("BALH", "FAILADDTODB: " + name);
            return false;
        }else{
            Log.d("BALH", "AddContactTODB: " + name);
            return true;
        }
    }

    public boolean DB_UpdateData(String originalName, String name, String number, String email, String address, String tag)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,name);
        values.put(COLUMN_NUMBER,number);
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_ADDRESS,address);
        values.put(COLUMN_TAGS,tag);


        long result = db.update(TABLE_NAME, values, "Name = ?", new String[] {originalName});
        if(result == -1) {
            Log.d("BALH", "FailToUpdate: " + name);
            return false;
        }else{
            Log.d("BALH", "UpdateContactINDB: " + name);
            return true;
        }

    }

    public Cursor DB_ReadData()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }


    public int DB_GetCount()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res.getCount();
    }

    public int DB_DeleteEntry(String name)
    {
        SQLiteDatabase db = getWritableDatabase();
        Log.d("DB", "DELETE: " + name);

        return db.delete(TABLE_NAME, "Name = ?", new String[] {name});
    }

    private static String strSeparator = "__,__";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}