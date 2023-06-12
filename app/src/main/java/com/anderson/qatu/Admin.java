package com.anderson.qatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Admin extends AppCompatActivity {
    ImageView imgVendedores, imgProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        imgVendedores = findViewById(R.id.imgVendedores);
        imgProductos = findViewById(R.id.imgProductos);
        imgProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, AdminProducto.class);
                startActivity(i);
            }
        });
        imgVendedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, AdminVendedor.class);
                startActivity(i);
            }
        });
    }
}