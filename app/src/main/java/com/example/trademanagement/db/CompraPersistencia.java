package com.example.trademanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trademanagement.model.Compra;
import com.example.trademanagement.model.Venta;

import java.util.ArrayList;

public class CompraPersistencia {

    private Context contexto;
    private CompraSQLiteHelper compraH;


    public CompraPersistencia(Context contexto) {
        this.contexto = contexto;
        compraH = new CompraSQLiteHelper(contexto);
    }

    public SQLiteDatabase openReadable() {
        return compraH.getReadableDatabase();
    }

    public SQLiteDatabase openWriteable() {
        return compraH.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }

    public long insertarCompra(Compra compra) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues compraValues = new ContentValues();
        compraValues.put(CompraContract.CompraEntry.COLUMN_CODIGO,
                compra.getCodigo());
        compraValues.put(CompraContract.CompraEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(CompraContract.CompraEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(CompraContract.CompraEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(CompraContract.CompraEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(CompraContract.CompraEntry.COLUMN_TOTAL,
                compra.getTotal());

        long codigo = database.insert(CompraContract.CompraEntry.TABLE_NAME,
                null, compraValues);

        if (codigo != -1) {
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        return codigo;

    }


    public ArrayList<Compra> leerCompra() {
        ArrayList<Compra> listaCompra = new ArrayList<Compra>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + CompraContract.CompraEntry.COLUMN_CODIGO
                + ", " + CompraContract.CompraEntry.COLUMN_NOMBRE
                + ", " + CompraContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + CompraContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + CompraContract.CompraEntry.COLUMN_PRECIO
                + ", " + CompraContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + CompraContract.CompraEntry.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        Compra compra = null;

        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_TOTAL));

                compra = new Compra(codigo, nombre, proveedor, cantidad, precio, total);
                compra.setCodigo(codigo);

                listaCompra.add(compra);
            } while (cursor.moveToNext());
        }

        close(database);
        return listaCompra;
    }

    public ArrayList<Compra> leerCompraPorProducto(String productoB) {
        ArrayList<Compra> lista = new ArrayList<Compra>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + CompraContract.CompraEntry.COLUMN_CODIGO
                + ", " + CompraContract.CompraEntry.COLUMN_NOMBRE
                + ", " + CompraContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + CompraContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + CompraContract.CompraEntry.COLUMN_PRECIO
                + ", " + CompraContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + CompraContract.CompraEntry.TABLE_NAME
                + " WHERE " + CompraContract.CompraEntry.COLUMN_NOMBRE
                + " LIKE '%" + productoB + "%'";

        Cursor cursor = database.rawQuery(query, null);

        Compra compra = null;
        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        CompraContract.CompraEntry.COLUMN_TOTAL));

                compra = new Compra(codigo, nombre, proveedor, cantidad, precio, total);
                compra.setNombre(nombre);

                lista.add(compra);

            } while (cursor.moveToNext());
        }

        close(database);
        return lista;

    }

    public Compra leerCompraa(long sCodigo) {
        Compra compra = null;
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + CompraContract.CompraEntry.COLUMN_CODIGO
                + ", " + CompraContract.CompraEntry.COLUMN_NOMBRE
                + ", " + CompraContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + CompraContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + CompraContract.CompraEntry.COLUMN_PRECIO
                + ", " + CompraContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + CompraContract.CompraEntry.TABLE_NAME
                + " WHERE "
                + CompraContract.CompraEntry.COLUMN_CODIGO + " = ?";
        String[] whereArgs = {String.valueOf(sCodigo)};

        Cursor cursor = database.rawQuery(query, whereArgs);

        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;
        if (cursor.moveToFirst()) {
            codigo = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_CODIGO));
            nombre = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_NOMBRE));
            proveedor = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_PROVEEDOR));
            cantidad = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_CANTIDAD));
            precio = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_PRECIO));
            total = cursor.getString(cursor.getColumnIndex(
                    CompraContract.CompraEntry.COLUMN_TOTAL));

            compra = new Compra(codigo, nombre, proveedor, cantidad, precio, total);
            compra.setCodigo(codigo);


        }
        cursor.close();
        close(database);
        return compra ;

    }

    public void actualizarAlmacen(Compra compra) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues compraValues = new ContentValues();
        compraValues.put(CompraContract.CompraEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(CompraContract.CompraEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(CompraContract.CompraEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(CompraContract.CompraEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(CompraContract.CompraEntry.COLUMN_TOTAL,
                compra.getTotal());

        String [] whereArgs = { String.valueOf(compra.getCodigo()) };
        database.update(CompraContract.CompraEntry.TABLE_NAME,
                compraValues,
                CompraContract.CompraEntry.COLUMN_CODIGO + " = ?",
                whereArgs);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }


}