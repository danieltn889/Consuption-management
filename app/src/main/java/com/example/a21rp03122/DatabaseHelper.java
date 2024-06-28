package com.example.a21rp03122;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "expensetrack.db";
    private static final String TABLE_NAME = "expenses";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TYPE = "TYPE";
    private static final String COLUMN_QUANTITY= "QUANTITY";
    private static final String COLUMN_UNIT_PRICE = "UNIT_PRICE";
    private static final String COLUMN_DATE = "DATE";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "DATABASE_NAME", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, QUANTITY REAL, UNIT_PRICE REAL, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
