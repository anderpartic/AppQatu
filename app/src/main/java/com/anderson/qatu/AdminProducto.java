package com.anderson.qatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProducto extends AppCompatActivity {
Button btnProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_producto);
        btnProducto = findViewById(R.id.btnProducto);
        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminProducto.this, Producto.class);
                startActivity(i);
            }
        });
    }
}