package com.anderson.qatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtCrear;
    EditText etUsuario, etPassword;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });
        txtCrear = findViewById(R.id.txtCrear);
        txtCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registrarse.class);
                startActivity(i);
            }
        });
    }
    public void ingresar() {
        QatuSQLOpenHelper db = new QatuSQLOpenHelper(MainActivity.this);
        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();
        try {
            if (usuario.equals(null)&& password.equals(null)){
                Toast.makeText(MainActivity.this, "Ingrese su Usuario y Contraseña", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Admin.class);
            }
            if (usuario.equals("admin") && password.equals("12345")) {
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Admin.class);
                limpiar();
                startActivity(i);
            } else if (db.verificarCliente(usuario, password)) {
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Cliente.class);
                startActivity(i);
                limpiar();
            } else if (db.registrarVendedor(usuario, password)) {
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Vendedor.class);
                startActivity(i);
                limpiar();
            } else {
                Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Error" +e, Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiar() {
        etUsuario.setText(null);
        etPassword.setText(null);
    }
}