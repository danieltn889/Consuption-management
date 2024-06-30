package com.example.a21rp03122;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditRecordActivity extends AppCompatActivity {
    private EditText typeEditText, quantityEditText, unitPriceEditText, dateEditText;
    private Button updateButton;
    private DatabaseHelper databaseHelper;
    private int recordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        typeEditText = findViewById(R.id.typeEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        unitPriceEditText = findViewById(R.id.unitPriceEditText);
        dateEditText = findViewById(R.id.dateEditText);
        updateButton = findViewById(R.id.updateButton);

        databaseHelper = new DatabaseHelper(this);
        recordId = getIntent().getIntExtra("ID", -1);

        if (recordId != -1) {
            loadRecordData(recordId);
        }

        updateButton.setOnClickListener(view -> {
            String type = typeEditText.getText().toString();
            double quantity = Double.parseDouble(quantityEditText.getText().toString());
            double unitPrice = Double.parseDouble(unitPriceEditText.getText().toString());
            String date = dateEditText.getText().toString();

            boolean isUpdated = databaseHelper.updateData(recordId, type, quantity, unitPrice, date);
            if (isUpdated) {
                Toast.makeText(EditRecordActivity.this, "Record Updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditRecordActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("Range")
    private void loadRecordData(int id) {
        Cursor cursor = databaseHelper.getDataById(id);
        if (cursor.moveToFirst()) {
            typeEditText.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TYPE)));
            quantityEditText.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_QUANTITY)));
            unitPriceEditText.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UNIT_PRICE)));
            dateEditText.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE)));
        }
        cursor.close();
    }
}
