package com.shubhamkhuva.sqlliteimagebitmapstore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    static final String TABLE_NAME = "cif_images";

    // Table columns
    static final String _ID = "_id";
    static final String APPLICATIONID = "applicationid";
    static final String PROFILE = "profile";
    static final String PRIMARY1 = "primary1";
    static final String PRIMARY2 = "primary2";
    static final String SECONDARY1 = "secondary1";
    static final String SECONDARY2 = "secondary2";
    static final String ADDRESS = "address";

    // Database Information
    private static final String DB_NAME = "DBSample";

    // database version
    private static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + APPLICATIONID + " TEXT NOT NULL, "
            + PROFILE + " BLOB, "
            + PRIMARY1 + " BLOB, "
            + PRIMARY2 + " BLOB, "
            + SECONDARY1 + " BLOB, "
            + SECONDARY2 + " BLOB, "
            + ADDRESS + " BLOB);";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
