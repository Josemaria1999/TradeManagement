package com.example.trademanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductosSQLiteHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "Productos";
    static final int VERSION_DB = 2;


    static final String CREATE_TABLE_COMPRAS =
            "CREATE TABLE "
                    + ProductosContract.ProductoEntry.TABLE_NAME +
                    "( " +
                    ProductosContract.ProductoEntry.COLUMN_CODIGO
                    + " INTEGER PRIMARY KEY ," +
                    ProductosContract.ProductoEntry.COLUMN_NOMBRE +
                    " TEXT NOT NULL," +
                    ProductosContract.ProductoEntry.COLUMN_PROVEEDOR +
                    " TEXT NOT NULL," +
                    ProductosContract.ProductoEntry.COLUMN_CANTIDAD +
                    " NUMBER NOT NULL," +
                    ProductosContract.ProductoEntry.COLUMN_PRECIO+
                    " NUMBER NOT NULL," +
                    ProductosContract.ProductoEntry.COLUMN_TOTAL+
                    " TEXT NOT NULL,"+
                   ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA+
                    " TEXT NOT NULL,"+
                    ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA+
                    " NUMBER NOT NULL," +
                    ProductosContract.ProductoEntry.COLUMN_DIFERENCIA +
                    " NUMBER NOT NULL,"+
                    ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS+
                    " TEXT NOT NULL);";



    public ProductosSQLiteHelper(Context context) {
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
                ProductosContract.ProductoEntry.TABLE_NAME);
        onCreate(db);
    }
}
