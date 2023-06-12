package com.anderson.qatu;

import static com.anderson.qatu.QatuSQLOpenHelper.TABLE_Productos;
import static com.anderson.qatu.QatuSQLOpenHelper.TABLE_Vendedor;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Producto extends AppCompatActivity {
    EditText etNomPro, etPrecio, etStock;
    Button btnGuardarP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        etNomPro = findViewById(R.id.etNomPro);
        etPrecio = findViewById(R.id.etPrecio);
        etStock = findViewById(R.id.etStock);
        btnGuardarP = findViewById(R.id.btnGuardarP);
        btnGuardarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarProducto();
            }
        });


    }

    public void GuardarProducto() {
        QatuSQLOpenHelper dbProducto = new QatuSQLOpenHelper(Producto.this);
        SQLiteDatabase db = dbProducto.getWritableDatabase();
        String producto = etNomPro.getText().toString();
        String precio = etPrecio.getText().toString();
        String stock = etStock.getText().toString();
        try {
            if (producto.equals("")) {
                Toast.makeText(this, "Debes ingresar un producto", Toast.LENGTH_LONG).show();
            } else if (precio.equals("")) {
                Toast.makeText(this, "Debes ingresar un precio", Toast.LENGTH_LONG).show();
            } else if (stock.equals("")) {
                Toast.makeText(this, "Debes ingresar el stock", Toast.LENGTH_LONG).show();
            } else {
                String query = "SELECT nombreP FROM " + TABLE_Productos + " WHERE nombreP = ?";
                Cursor cursor = db.rawQuery(query, new String[]{producto});
                if (cursor.moveToFirst()) {
                    // El usuario ya existe, mostrar mensaje de error
                    Toast.makeText(this, "El producto ya existe", Toast.LENGTH_LONG).show();
                } else {
                    // Guardar los datos en la base de datos
                    ContentValues datoProducto = new ContentValues();
                    datoProducto.put("nombreP", producto);
                    datoProducto.put("precioP", precio);
                    datoProducto.put("stockP", stock);

                    try {
                        db.insert(TABLE_Productos, null, datoProducto);
                        db.close();
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                        limpiar();
                    } catch (Exception e) {
                        Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        } catch (Exception e){
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void limpiar() {
        etNomPro.setText(null);
        etPrecio.setText(null);
        etStock.setText(null);
    }

}