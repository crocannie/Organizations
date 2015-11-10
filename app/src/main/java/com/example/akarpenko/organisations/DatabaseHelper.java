package com.example.akarpenko.organisations;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "org.db";
    public static final int SCHEMA = 1;
    static final String TABLE = "org";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_MAIL = "mail";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE org ("+ COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + COLUMN_NAME
                + " TEXT, " + COLUMN_PHONE+ " TEXT, "+COLUMN_ADDRESS+" TEXT, "+COLUMN_MAIL+" TEXT "+ ");");
        // добавление начальных данных
        db.execSQL("INSERT INTO " + TABLE + " (" +COLUMN_ID +", " + COLUMN_NAME + ", " +
                COLUMN_PHONE+", " +COLUMN_ADDRESS+", "+COLUMN_MAIL+ ")" +
                " VALUES ('42','Company name','9999999', 'google.com','empty@mail');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
