package com.example.safsaf.habittracker.data;

/**
 * Created by Safsaf on 8/10/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.safsaf.habittracker.data.HabitContract.habitEntry;


/**
 * Database helper for habit tracker app. Manages database creation and version management.
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habit.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitContract}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + habitEntry.TABLE_NAME + " ("
                + habitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + habitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + habitEntry.COLUMN_HABIT_TIMES + " INTEGER NOT NULL DEFAULT 0);";
        Log.v(LOG_TAG, SQL_CREATE_HABITS_TABLE);

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}