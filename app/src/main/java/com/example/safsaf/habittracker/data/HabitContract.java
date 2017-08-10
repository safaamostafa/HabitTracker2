package com.example.safsaf.habittracker.data;

/**
 * Created by Safsaf on 8/10/2017.
 */

import android.provider.BaseColumns;

/**
 * API Contract for the Habit Tracker app.
 */
public final class HabitContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for the habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class habitEntry implements BaseColumns {

        /**
         * Name of database table for habits
         */
        public final static String TABLE_NAME = "habits";

        /**
         * Unique ID number for the habit (only for use in the database table).
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the habit.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_HABIT_NAME = "name";

        /**
         * Weight of the habit.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_HABIT_TIMES = "times";
    }
}
