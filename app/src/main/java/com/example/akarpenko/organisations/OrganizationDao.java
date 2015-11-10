package com.example.akarpenko.organisations;

import android.database.Cursor;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class OrganizationDao {

    private Cursor cursor;
    private ContentValues initialValues;
    private  SQLiteDatabase m_db;

    public OrganizationDao(SQLiteDatabase db)
    {
        m_db=db;
    }
    public List<Org> getAllOrganizations()
    {
        List<Org> organizationList=new ArrayList<Org>();
        cursor=m_db.query(DatabaseHelper.TABLE,PROJECTION,null,null,null,null, DatabaseHelper.COLUMN_NAME);

        if (cursor!=null)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Org organization=cursorToEntity(cursor);
                organizationList.add(organization);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return organizationList;
    }

    public  boolean addOrganization (Org organization)
    {
        setContentValue(organization);

        try
        {
                    m_db.delete(DatabaseHelper.TABLE,null,null);
            return   m_db.insert(DatabaseHelper.TABLE,null,getInitialValues())> 0;

        }catch (SQLiteConstraintException ex)
        {
            Log.w("Database",ex.getMessage());
            return false;
        }
    }

    private  static  final String[] PROJECTION=new String[]
            {
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_NAME,
                    DatabaseHelper.COLUMN_PHONE,
                    DatabaseHelper.COLUMN_ADDRESS,
                    DatabaseHelper.COLUMN_MAIL

            };

    protected Org cursorToEntity (Cursor cursor) {
        Org organization = new Org();
        int idIndex;
        int nameIndex;
        int emailIndex;
        int phoneIndex;
        int siteIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(DatabaseHelper.COLUMN_ID) != -1) {
                idIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID);
                organization.id = cursor.getInt(idIndex);
            }

            if (cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME) != -1)

            {
                nameIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME);
                organization.name = cursor.getString(nameIndex);
            }

            if (cursor.getColumnIndex(DatabaseHelper.COLUMN_PHONE) != -1) {
                phoneIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE);
                organization.phone = cursor.getString(phoneIndex);

            }

            if (cursor.getColumnIndex(DatabaseHelper.COLUMN_MAIL) != -1) {
                emailIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MAIL);
                organization.mail = cursor.getString(emailIndex);
            }

            if (cursor.getColumnIndex(DatabaseHelper.COLUMN_ADDRESS) != -1) {
                siteIndex = cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS);
                organization.address = cursor.getString(siteIndex);
            }

        }
        return organization;
    }

    private void setContentValue(Org organization) {
        initialValues=new ContentValues();

        initialValues.put(DatabaseHelper.COLUMN_NAME,organization.name);
        initialValues.put(DatabaseHelper.COLUMN_PHONE, organization.phone);
        initialValues.put(DatabaseHelper.COLUMN_MAIL,organization.mail);
        initialValues.put(DatabaseHelper.COLUMN_ADDRESS, organization.address);

    }
    private ContentValues getInitialValues(){
        return initialValues;
    }
}
