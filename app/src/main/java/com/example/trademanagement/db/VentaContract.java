package com.example.trademanagement.db;

import android.provider.BaseColumns;

public class VentaContract {
    public static abstract class VentaEntry implements BaseColumns {
        public static final String COLUMN_CODIGOV = BaseColumns._ID;
        public static final String COLUMN_CLIENTE = "CLIENTE";
        public static final String COLUMN_NOMBREV = "NOMBRE";
        public static final String COLUMN_PROVEEDORV = "PROVEEDOR";
        public static final String COLUMN_CANTIDADV = "CANTIDAD";
        public static final String COLUMN_PRECIOV = "PRECIO";
        public static final String COLUMN_TOTALV = "TOTAL";
        public static final String TABLE_NAME_V = "VENTA";
    }
}
