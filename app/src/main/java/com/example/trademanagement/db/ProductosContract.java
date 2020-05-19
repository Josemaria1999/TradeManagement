package com.example.trademanagement.db;

import android.provider.BaseColumns;

public class ProductosContract {
    public static abstract class CompraEntry implements BaseColumns{
        public static final String COLUMN_CODIGO = BaseColumns._ID;
        public static final String COLUMN_NOMBRE = "NOMBRE";
        public static final String COLUMN_PROVEEDOR = "PROVEEDOR";
        public static final String COLUMN_CANTIDAD = "CANTIDAD";
        public static final String COLUMN_PRECIO = "PRECIO";
        public static final String COLUMN_TOTAL = "TOTAL";
        public static final String TABLE_NAME = "COMPRA";

    }
}
