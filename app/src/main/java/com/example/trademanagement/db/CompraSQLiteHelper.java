package com.example.trademanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompraSQLiteHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Productos";
    static final int VERSION_DB = 2;


    static final String CREATE_TABLE_COMPRAS =
            "CREATE TABLE "
                    + CompraContract.CompraEntry.TABLE_NAME +
                    "( " +
                    CompraContract.CompraEntry.COLUMN_CODIGO
                    + " INTEGER PRIMARY KEY ," +
                    CompraContract.CompraEntry.COLUMN_NOMBRE +
                    " TEXT NOT NULL," +
                    CompraContract.CompraEntry.COLUMN_PROVEEDOR +
                    " TEXT NOT NULL," +
                    CompraContract.CompraEntry.COLUMN_CANTIDAD +
                    " NUMBER NOT NULL," +
                    CompraContract.CompraEntry.COLUMN_PRECIO+
                    " NUMBER NOT NULL," +
                    CompraContract.CompraEntry.COLUMN_TOTAL+
                    " TEXT NOT NULL);";



    public CompraSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COMPRAS);
        //TODO CARGA INICIAL
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                CompraContract.CompraEntry.TABLE_NAME);
        onCreate(db);
    }
}
