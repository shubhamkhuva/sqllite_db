package com.shubhamkhuva.sqlliteimagebitmapstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertCIFApplication(String applicationID) {
        byte[] image = null;
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.APPLICATIONID, applicationID);
        contentValue.put(DatabaseHelper.PROFILE, image);
        contentValue.put(DatabaseHelper.PRIMARY1, image);
        contentValue.put(DatabaseHelper.PRIMARY2, image);
        contentValue.put(DatabaseHelper.SECONDARY1, image);
        contentValue.put(DatabaseHelper.SECONDARY2, image);
        contentValue.put(DatabaseHelper.ADDRESS, image);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    byte[] fetchCIFApplicationPrimary1(String applicationID) {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.APPLICATIONID, DatabaseHelper.PRIMARY1};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, DatabaseHelper.APPLICATIONID + " = '" + applicationID+"'", null, null, null, null);
        byte[] primary1 = new byte[0];
        if (cursor.moveToFirst()){
            primary1 = cursor.getBlob(cursor.getColumnIndex("primary1"));
        }
        return primary1;
    }

    public int updateCIFApplicationPrimary1(String applicationID,byte[] primary1) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PRIMARY1, primary1);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.APPLICATIONID + " = '" + applicationID+"'", null);
        return i;
    }

    public void deleteCIFApplication(String applicationID) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.APPLICATIONID + "=" + applicationID, null);
    }

    public boolean checkIfMyTitleExists(String applicationID) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,null,DatabaseHelper.APPLICATIONID + "='" + applicationID+"'", null, null, null, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();

        return true;
    }

}
