package com.example.safsaf.habittracker;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.safsaf.habittracker.data.HabitContract.habitEntry;
import com.example.safsaf.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    /** Database helper that will provide us access to the database */
    private HabitDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();

    }

    /**
     *      * Temporary helper method to display information in the onscreen TextView about the state of
     *      * the habits database.
     *
     */
    private void displayDatabaseInfo() {


        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                habitEntry._ID,
                habitEntry.COLUMN_HABIT_NAME,
                habitEntry.COLUMN_HABIT_TIMES
        };

        // Perform a query on the habits table
        Cursor cursor = db.query(
               habitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The habits table contains <number of rows in Cursor> pets.
            // _id - name - times
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append(habitEntry._ID + " - " +
                    habitEntry.COLUMN_HABIT_NAME + " - " +
                    habitEntry.COLUMN_HABIT_TIMES + "\n");
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(habitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(habitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursor.getColumnIndex(habitEntry.COLUMN_HABIT_TIMES);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentTime));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
