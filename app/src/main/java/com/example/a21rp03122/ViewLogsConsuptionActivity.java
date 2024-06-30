package com.example.a21rp03122;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewLogsConsuptionActivity extends AppCompatActivity {
    private TextView logsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_logs_consuption);

        // Initialize views
        logsTextView = findViewById(R.id.logsTextView);

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        Cursor cursor = databaseHelper.getAllData();

        if (cursor.getCount() == 0) {
            logsTextView.setText("No data found");
            return;
        }

        StringBuilder logs = new StringBuilder();
        double totalPrice = 0.0;

        while (cursor.moveToNext()) {
            logs.append("ID: ").append(cursor.getString(0)).append("\n")
                    .append("Type: ").append(cursor.getString(1)).append("\n")
                    .append("Quantity: ").append(cursor.getString(2)).append("\n")
                    .append("Unit Price: ").append(cursor.getString(3)).append("\n")
                    .append("Date: ").append(cursor.getString(4)).append("\n").append("Total Price").append(cursor.getDouble(2) * cursor.getDouble(3)).append("\n\n");

            totalPrice += cursor.getDouble(2) * cursor.getDouble(3);
        }

        logs.append("Total Price: ").append(totalPrice).append(" RWF");
        logsTextView.setText(logs.toString());

        // Close cursor after use
        cursor.close();
    }
}