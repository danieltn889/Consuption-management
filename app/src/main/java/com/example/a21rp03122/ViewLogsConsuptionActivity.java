package com.example.a21rp03122;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewLogsConsuptionActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logs_consuption);

        // Initialize views
        tableLayout = findViewById(R.id.tableLayout);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Cursor cursor = databaseHelper.getAllData();

        if (cursor.getCount() == 0) {
            TableRow noDataRow = new TableRow(this);
            TextView noDataTextView = new TextView(this);
            noDataTextView.setText("No data found");
            noDataRow.addView(noDataTextView);
            tableLayout.addView(noDataRow);
            return;
        }

        double totalPrice = 0.0;

        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);

            TextView idTextView = new TextView(this);
            idTextView.setText(cursor.getString(0));
            row.addView(idTextView);

            TextView typeTextView = new TextView(this);
            typeTextView.setText(cursor.getString(1));
            row.addView(typeTextView);

            TextView quantityTextView = new TextView(this);
            quantityTextView.setText(cursor.getString(2));
            row.addView(quantityTextView);

            TextView unitPriceTextView = new TextView(this);
            unitPriceTextView.setText(cursor.getString(3));
            row.addView(unitPriceTextView);

            TextView dateTextView = new TextView(this);
            dateTextView.setText(cursor.getString(4));
            row.addView(dateTextView);

            double totalRowPrice = cursor.getDouble(2) * cursor.getDouble(3);
            TextView totalPriceTextView = new TextView(this);
            totalPriceTextView.setText(String.valueOf(totalRowPrice));
            row.addView(totalPriceTextView);

            Button editButton = new Button(this);
            editButton.setText("Edit");
            editButton.setOnClickListener(view -> {
                Intent intent = new Intent(ViewLogsConsuptionActivity.this, EditRecordActivity.class);
                intent.putExtra("ID", cursor.getInt(0));
                startActivity(intent);
            });
            row.addView(editButton);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(view -> {
                int id = cursor.getInt(0);
                databaseHelper.deleteData(id);
                Toast.makeText(ViewLogsConsuptionActivity.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                recreate(); // Refresh the activity to reflect the changes
            });
            row.addView(deleteButton);

            tableLayout.addView(row);

            totalPrice += totalRowPrice;
        }

        totalPriceTextView.setText("Total Price: " + totalPrice + " RWF");

        // Close cursor after use
        cursor.close();
    }
}
