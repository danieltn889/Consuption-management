package com.example.a21rp03122;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewLogConsuptionActivity extends AppCompatActivity {
    EditText editQunatity, editUnitPrice;
    Spinner spinnerConsuption;
    Button addConsuption;
    ArrayAdapter<String> adapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_log_consuption);

        editQunatity = findViewById(R.id.editQuantity);
        editUnitPrice = findViewById(R.id.editUnityPrice);
        addConsuption = findViewById(R.id.addConsuptionButton);
        spinnerConsuption = findViewById(R.id.editType);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.consuption));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConsuption.setAdapter(adapter);
        databaseHelper=new DatabaseHelper(getApplicationContext());

        addConsuption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  type=spinnerConsuption.getSelectedItem().toString();
                String  qty=editQunatity.getText().toString();
                String  price=editUnitPrice.getText().toString();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                double quantity=Double.parseDouble(qty);
                double unit_Price=Double.parseDouble(price);
                Boolean result=databaseHelper.insertData(type,quantity,unit_Price,date);
                if (result){
                    Toast.makeText(getApplicationContext(),"Consumption Saved",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Consumption Failed to save",Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
