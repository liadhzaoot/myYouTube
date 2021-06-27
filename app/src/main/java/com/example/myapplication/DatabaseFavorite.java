package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
//package com.example.myapplication;



    public class DatabaseFavorite extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 2;
        private static final String DATABASE_NAME = "favorite";
        private static final String TABLE_NAME = "favorite";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_FAVORITE = "favorite";
        private Cursor cursor;
        private SQLiteDatabase db;
        //String CREATE_ITEM_TABLE;

        public DatabaseFavorite(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Creating Tables
        @Override
        public void onCreate(SQLiteDatabase db) {
// Category table create query
            String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_FAVORITE + " TEXT  PRIMARY KEY, " +
                    COLUMN_EMAIL + " TEXT " +
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
        public String insertFavorite(Favorite favorite) {
            //check if the song already exist
            if (isRowExist(favorite.getUriPath()))
                return "The user is Exist";

            db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_EMAIL, favorite.getEmail());//column name, column value
            values.put(COLUMN_FAVORITE, favorite.getUriPath());//column name, column value
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
        public boolean isRowExist(String favorite) {
            /**
             * worked
             */
            int numOfRows = 0;
            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FAVORITE + " = '" + favorite + "';";
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

            if (cursor.moveToFirst()) {
                do {
                    numOfRows++;
                } while (cursor.moveToNext());

            }

            if (numOfRows >= 1)
                return true;

            else
                return false;
        }

        public Favorite getFavorite(String favorite) {
            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_FAVORITE + " = '" + favorite + "';";
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
            Favorite favorite1 = new Favorite();
            if (cursor.moveToFirst()) {
                do {
                    favorite1.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                    favorite1.setUriPath(cursor.getString(cursor.getColumnIndex(COLUMN_FAVORITE)));
                } while (cursor.moveToNext());

            }
            return favorite1;
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

