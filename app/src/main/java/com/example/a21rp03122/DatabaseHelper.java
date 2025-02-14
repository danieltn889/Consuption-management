package com.example.a21rp03122;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "expensetrack.db";
    public static final String TABLE_NAME = "expenses";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_UNIT_PRICE = "UNIT_PRICE";
    public static final String COLUMN_DATE = "DATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, QUANTITY REAL, UNIT_PRICE REAL, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String type, double quantity, double unitPrice, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_UNIT_PRICE, unitPrice);
        contentValues.put(COLUMN_DATE, date);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{String.valueOf(id)});
    }

    public boolean updateData(int id, String type, double quantity, double unitPrice, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_QUANTITY, quantity);
        contentValues.put(COLUMN_UNIT_PRICE, unitPrice);
        contentValues.put(COLUMN_DATE, date);
        int result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public Integer deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(id)});
    }
}
