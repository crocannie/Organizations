package com.example.akarpenko.organisations;

import android.content.Context;

import java.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;

public class Db {
    private DatabaseHelper mDBHelper;
    private final Context mContext;
    public static OrganizationDao mOrganizationDao;

    public Db (Context context)
    {
        this.mContext=context;
    }

    public Db open() throws SQLException
    {
        mDBHelper=new DatabaseHelper(mContext);
        SQLiteDatabase mDb=mDBHelper.getWritableDatabase();
        mOrganizationDao=new OrganizationDao(mDb);
        return  this;
    }

    public void close()
    {
        mDBHelper.close();
    }

}
