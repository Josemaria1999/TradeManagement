package com.example.trademanagement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VentaSQLiteHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "VENTAS_DB";
    static final int VERSION_DB = 2;

    static final String CREATE_TABLE_VENTAS =
            "CREATE TABLE "
                    + VentaContract.VentaEntry.TABLE_NAME_V +
                    "( " +
                    VentaContract.VentaEntry.COLUMN_CODIGOV
                    + " INTEGER PRIMARY KEY ," +
                    VentaContract.VentaEntry.COLUMN_CLIENTE +
                    " TEXT NOT NULL," +
                    VentaContract.VentaEntry.COLUMN_NOMBREV +
                    " TEXT NOT NULL," +
                    VentaContract.VentaEntry.COLUMN_PROVEEDORV +
                    " TEXT NOT NULL," +
                    VentaContract.VentaEntry.COLUMN_CANTIDADV +
                    " NUMBER NOT NULL," +
                    VentaContract.VentaEntry.COLUMN_PRECIOV+
                    " NUMBER NOT NULL," +
                    VentaContract.VentaEntry.COLUMN_TOTALV+
                    " TEXT NOT NULL);";

    public VentaSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VENTAS);
        //TODO CARGA INICIAL
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +
                VentaContract.VentaEntry.TABLE_NAME_V);
        onCreate(db);

    }


}
