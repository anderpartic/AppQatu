package com.anderson.qatu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QatuSQLOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_Version = 5;
    public static final String TABLE_Vendedor = "t_Vendedor";
    public static final String TABLE_Cliente = "t_Cliente";
    public static final String TABLE_Productos = "t_Productos";
    public static final String DATABASE_Nombre = "dbQatu";

    public QatuSQLOpenHelper(@Nullable Context context) {
        super(context, DATABASE_Nombre, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_Cliente + "(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "usuario text NOT NULL," +
                "password text NOT NULL);");

        db.execSQL("create table " + TABLE_Vendedor + "(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "usuarioV text NOT NULL," +
                "passwordV text NOT NULL);");

        db.execSQL("create table " + TABLE_Productos + "(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "nombreP text NOT NULL," +
                "precioP text NOT NULL," +
                "stockP text NOT NULL);");
         }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Cliente);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Vendedor);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Productos);
        onCreate(db);
    }

    public boolean verificarCliente(String usuario, String password) {

        SQLiteDatabase bd = getReadableDatabase();

        String[] projection = {
                "usuario",
                "password"
        };

        String selection = "usuario = ? AND password = ?";
        String[] selectionArgs = {usuario, password};

        Cursor cursor = bd.query(
                TABLE_Cliente,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean credencialesCorrectas = cursor.moveToFirst();
        cursor.close();
        bd.close();

        return credencialesCorrectas;
    }
    public boolean registrarVendedor(String usuarioV, String passwordV){

        SQLiteDatabase bd = getReadableDatabase();

        String[] projection = {
                "usuarioV",
                "passwordV"
        };

        String selection = "usuarioV = ? AND passwordV = ?";
        String[] selectionArgs = {usuarioV, passwordV};

        Cursor cursor = bd.query(
                TABLE_Vendedor,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean vendedorR = cursor.moveToFirst();
        cursor.close();
        bd.close();

        return vendedorR;
    }

}