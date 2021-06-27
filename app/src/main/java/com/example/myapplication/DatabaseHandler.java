package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "user";
    private static final String TABLE_NAME = "user";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_FAVORITE = "favorite";
    private Cursor cursor;
    private SQLiteDatabase db;
    //String CREATE_ITEM_TABLE;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
// Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_EMAIL + " TEXT  PRIMARY KEY, " +
                COLUMN_PASS + " TEXT, " +
                COLUMN_FAVORITE + " INTEGER " +
                ");";
        db.execSQL(CREATE_ITEM_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

// Create tables again
        onCreate(db);
    }

    /*
    Inserting new lable into lables table
    */
    public String insertUser(User user){
        //check if the song already exist
        if(isRowExist(user.getEmail()))
            return "The user is Exist";

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());//column name, column value
        values.put(COLUMN_PASS, user.getPass());//column name, column value
        //values.put(COLUMN_FAVORITE, user.getFavoriteList());//column name, column value
// Inserting Row
        db.insert(TABLE_NAME, null, values);
//tableName, nullColumnHack, CotentValues
        db.close();
        return "success insert";
// Closing database connection
    }
//    public void deleteSong(String songName)
//    {
//        db = this.getWritableDatabase();
//        db.delete(TABLE_NAME,COLUMN_SONG_NAME + " = '" + songName + "';",null);
//        db.close();
//    }
    public boolean isRowExist(String email) {
        /**
         * worked
         */
        int numOfRows = 0;
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = '" + email + "';";
        db = this.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (cursor.moveToFirst()) {
            do {
                numOfRows++;
            } while (cursor.moveToNext());

        }

        if(numOfRows >= 1)
            return true;

        else
            return false;
    }
    public User getUser(String email)
    {
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = '" + email + "';";
        db = this.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        User user = new User();
        if (cursor.moveToFirst()) {
            do {
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setPass(cursor.getString(cursor.getColumnIndex(COLUMN_PASS)));
            } while (cursor.moveToNext());

        }
        return user;
    }

//    // getting all songs from DB
//    public ArrayList getAllSongs()
//    {
//        //insertSong("sadsad",100,200,300,40,40);
//        db = getReadableDatabase();
//        ArrayList arrayList;
//        arrayList = new ArrayList<RecycleViewStructure>();
//        //RecycleViewStructure recycleViewStructure = new RecycleViewStructure();
//        String selectQuery = "SELECT " +  COLUMN_SONG_NAME  + ", " + COLUMN_DATE + ", " + COLUMN_IS_FINISHED +" FROM " + TABLE_NAME + "";
//        cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
//
//        //looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                RecycleViewStructure recycleViewStructure = new RecycleViewStructure();
//                recycleViewStructure.setSongName(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_NAME)));
//                recycleViewStructure.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
//                recycleViewStructure.setFinished(intToBool(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FINISHED))));
//                arrayList.add(recycleViewStructure);//adding 2nd column data
//            } while (cursor.moveToNext());
//        }
//
//        //closing
//        cursor.close();
//        db.close();
//
//        return arrayList;
//    }

}