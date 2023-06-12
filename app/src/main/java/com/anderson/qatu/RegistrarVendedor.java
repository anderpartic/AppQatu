package com.anderson.qatu;


import static com.anderson.qatu.QatuSQLOpenHelper.TABLE_Vendedor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarVendedor extends AppCompatActivity {
    EditText etNomUsu, etPass, etConPass;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_vendedor);
        etNomUsu = findViewById(R.id.etNomUsu);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarV();
            }
        });
    }

    public void registrarV() {
        QatuSQLOpenHelper dbVendedor = new QatuSQLOpenHelper(RegistrarVendedor.this);
        SQLiteDatabase db = dbVendedor.getWritableDatabase();
        String usuarioV = etNomUsu.getText().toString();
        String passwordV = etPass.getText().toString();
        String conPassV = etConPass.getText().toString();

        if (usuarioV.equals("")) {
            Toast.makeText(this, "Debes ingresar un usuario", Toast.LENGTH_LONG).show();
        } else if (passwordV.equals("")) {
            Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
        } else if (conPassV.equals("")) {
            Toast.makeText(this, "Debes volver a ingresar tu contraseña", Toast.LENGTH_LONG).show();
        } else if (!passwordV.equals(conPassV)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        } else {
            String query = "SELECT usuarioV FROM " + TABLE_Vendedor + " WHERE usuarioV = ?";
            Cursor cursor = db.rawQuery(query, new String[]{usuarioV});
            if (cursor.moveToFirst()) {
                // El usuario ya existe, mostrar mensaje de error
                Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_LONG).show();
            } else {
                // Guardar los datos en la base de datos
                ContentValues datosVendedor = new ContentValues();
                datosVendedor.put("usuarioV", usuarioV);
                datosVendedor.put("passwordV", passwordV);

                try {
                    db.insert(TABLE_Vendedor, null, datosVendedor);
                    db.close();
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    limpiar();
                } catch (Exception e) {
                    Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    public void limpiar() {
        etNomUsu.setText(null);
        etPass.setText(null);
        etConPass.setText(null);
    }

}