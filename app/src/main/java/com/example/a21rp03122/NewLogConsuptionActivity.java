package com.example.a21rp03122;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class NewLogConsuptionActivity extends AppCompatActivity {
    EditText editQunatity,editUnitPrice;
    Spinner spinnerConsuption;
    Button addConsuption;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_log_consuption);
        editQunatity=findViewById(R.id.editQuantity);
        editUnitPrice=findViewById(R.id.editUnityPrice);
        addConsuption=findViewById(R.id.addConsuptionButton);
        spinnerConsuption=findViewById(R.id.editType);
        addConsuption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.consuption,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerConsuption.setAdapter(adapter);


            }
        });


    }
}