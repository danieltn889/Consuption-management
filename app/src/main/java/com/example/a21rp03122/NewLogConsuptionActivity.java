package com.example.a21rp03122;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewLogConsuptionActivity extends AppCompatActivity {
    EditText editQunatity,editUnitPrice;
    Spinner spinnerConsuption;
    Button addConsuption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_log_consuption);
        editQunatity=findViewById(R.id.editQuantity);
        editUnitPrice=findViewById(R.id.editUnityPrice);
        addConsuption=findViewById(R.id.addConsuptionButton);
        addConsuption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}