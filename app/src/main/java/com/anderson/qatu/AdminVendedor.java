package com.anderson.qatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminVendedor extends AppCompatActivity {
Button btnTRegistrarV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vendedor);
        btnTRegistrarV = findViewById(R.id.btnRegistrarV);
        btnTRegistrarV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminVendedor.this, RegistrarVendedor.class);
                startActivity(i);
            }
        });
    }
}