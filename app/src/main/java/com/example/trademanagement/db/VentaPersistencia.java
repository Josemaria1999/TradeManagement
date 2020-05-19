package com.example.trademanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trademanagement.model.Venta;

import java.util.ArrayList;

public class VentaPersistencia {
    private Context contextoV;
    private VentaSQLiteHelper ventaH;


    public VentaPersistencia(Context contextoV) {
        this.contextoV = contextoV;
        ventaH = new VentaSQLiteHelper(contextoV);
    }

    public SQLiteDatabase openReadable() {
        return ventaH.getReadableDatabase();
    }

    public SQLiteDatabase openWriteable() {
        return ventaH.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }

    public long registrarVenta(Venta venta) {

        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues ventaValues = new ContentValues();
        ventaValues.put(VentaContract.VentaEntry.COLUMN_CODIGOV,
                venta.getCodigoV());
        ventaValues.put(VentaContract.VentaEntry.COLUMN_NOMBREV,
                venta.getNombreV());
        ventaValues.put(VentaContract.VentaEntry.COLUMN_PROVEEDORV,
                venta.getProveedorV());
        ventaValues.put(VentaContract.VentaEntry.COLUMN_CANTIDADV,
                venta.getCantidadV());
        ventaValues.put(VentaContract.VentaEntry.COLUMN_PRECIOV,
                venta.getPrecioV());
        ventaValues.put(VentaContract.VentaEntry.COLUMN_TOTALV,
                venta.getTotalV());
        long codigoV = database.insert(VentaContract.VentaEntry.TABLE_NAME_V,
                null, ventaValues);

        if (codigoV != -1) {
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        return codigoV;
    }

    public Venta leerVentas(long sCodigoV){
        Venta venta = null;
        SQLiteDatabase databaseV = openReadable();

        String query = "SELECT "
                + VentaContract.VentaEntry.COLUMN_CODIGOV
                + ", " + VentaContract.VentaEntry.COLUMN_NOMBREV
                + ", " + VentaContract.VentaEntry.COLUMN_PROVEEDORV
                + ", " + VentaContract.VentaEntry.COLUMN_CANTIDADV
                + ", " + VentaContract.VentaEntry.COLUMN_PRECIOV
                + ", " + VentaContract.VentaEntry.COLUMN_TOTALV
                + " FROM " + VentaContract.VentaEntry.TABLE_NAME_V
                + " WHERE "
                + VentaContract.VentaEntry.COLUMN_CODIGOV + " = ?";

        String[] whereArgs ={String.valueOf(sCodigoV)};

        Cursor cursorV = databaseV.rawQuery(query, whereArgs);


        String codigoV;
        String nombreV;
        String proveedorV;
        String cantidadV;
        String precioV;
        String totalV;
        if (cursorV.moveToFirst()){
            codigoV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_CODIGOV));
            nombreV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_NOMBREV));
            proveedorV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_PROVEEDORV));
            cantidadV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_CANTIDADV));
            precioV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_PRECIOV));
            totalV = cursorV.getString(cursorV.getColumnIndex(
                    VentaContract.VentaEntry.COLUMN_TOTALV));

            venta = new Venta(codigoV, nombreV, proveedorV, cantidadV, precioV, totalV);
            venta.setCodigoV(codigoV);

        }
        cursorV.close();
        close(databaseV);
        return venta;
    }


    public ArrayList<Venta> leerVenta(String cod) {
        ArrayList<Venta> listaVenta = new ArrayList<Venta>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + VentaContract.VentaEntry.COLUMN_CODIGOV
                + ", " + VentaContract.VentaEntry.COLUMN_CLIENTE
                + ", " + VentaContract.VentaEntry.COLUMN_NOMBREV
                + ", " + VentaContract.VentaEntry.COLUMN_PROVEEDORV
                + ", " + VentaContract.VentaEntry.COLUMN_CANTIDADV
                + ", " + VentaContract.VentaEntry.COLUMN_PRECIOV
                + ", " + VentaContract.VentaEntry.COLUMN_TOTALV
                + " FROM " + VentaContract.VentaEntry.TABLE_NAME_V
                + " WHERE "+ VentaContract.VentaEntry.COLUMN_CODIGOV
                + " LIKE '%" + cod + "%'";

        Cursor cursorV = database.rawQuery(query, null);

        Venta venta = null;

        String codigoV;
        String clienteV;
        String nombreV;
        String proveedorV;
        String cantidadV;
        String precioV;
        String totalV;

        if (cursorV.moveToFirst()) {
            do {
                codigoV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_CODIGOV));
                clienteV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_CLIENTE));
                nombreV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_NOMBREV));
                proveedorV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_PROVEEDORV));
                cantidadV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_CANTIDADV));
                precioV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_PRECIOV));
                totalV = cursorV.getString(cursorV.getColumnIndex(
                        VentaContract.VentaEntry.COLUMN_TOTALV));

             //   venta = new Compra(codigoV, clienteV, nombreV, proveedorV, cantidadV, precioV, totalV);
              //  venta.setCodigo(codigoV);

                listaVenta.add(venta);
            } while (cursorV.moveToNext());
        }

        close(database);
        return listaVenta;
    }

    public ArrayList<Venta> leerVentaPorProducto(String producto) {
        return null;
    }
}


