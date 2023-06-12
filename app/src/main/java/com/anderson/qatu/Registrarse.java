package com.anderson.qatu;

import static com.anderson.qatu.QatuSQLOpenHelper.TABLE_Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {
    EditText etNomUsu, etPass, etConPass;
    Button btnCrear;
    TextView txtIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        etNomUsu = findViewById(R.id.etNomUsu);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });
        txtIniciar = findViewById(R.id.txtIniciar);
        txtIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registrarse.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void ingresar() {
        QatuSQLOpenHelper dbCliente = new QatuSQLOpenHelper(Registrarse.this);
        SQLiteDatabase db = dbCliente.getWritableDatabase();
        String usuario = etNomUsu.getText().toString();
        String password = etPass.getText().toString();
        String conPass = etConPass.getText().toString();

        if (usuario.equals("")) {
            Toast.makeText(this, "Debes ingresar tu usuario", Toast.LENGTH_LONG).show();
        } else if (password.equals("")) {
            Toast.makeText(this, "Debes ingresar una contraseña", Toast.LENGTH_LONG).show();
        } else if (conPass.equals("")) {
            Toast.makeText(this, "Debes volver a ingresar tu contraseña", Toast.LENGTH_LONG).show();
        } else if (!password.equals(conPass)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        } else {
            String query = "SELECT usuario FROM " + TABLE_Cliente + " WHERE usuario = ?";
            Cursor cursor = db.rawQuery(query, new String[]{usuario});
            if (cursor.moveToFirst()) {
                // El usuario ya existe, mostrar mensaje de error
                Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_LONG).show();
            } else {
                // Guardar los datos en la base de datos
                ContentValues datosCliente = new ContentValues();
                datosCliente.put("usuario", usuario);
                datosCliente.put("password", password);

                try {
                    db.insert(TABLE_Cliente, null, datosCliente);
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