package com.example.trademanagement.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.trademanagement.model.Producto;

import java.util.ArrayList;

public class ProductosPersistencia {

    private Context contexto;
    private ProductosSQLiteHelper compraH;


    public ProductosPersistencia(Context contexto) {
        this.contexto = contexto;
        compraH = new ProductosSQLiteHelper(contexto);
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

    public void añadir(View view) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

    }
    public long insertarCompra(Producto compra) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues compraValues = new ContentValues();
        compraValues.put(ProductosContract.CompraEntry.COLUMN_CODIGO,
                compra.getCodigo());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_TOTAL,
                compra.getTotal());

        long codigo = database.insert(ProductosContract.CompraEntry.TABLE_NAME,
                null, compraValues);

        if (codigo != -1) {
            database.setTransactionSuccessful();
        }
        database.endTransaction();
        close(database);

        return codigo;

    }


    public ArrayList<Producto> leerCompra() {
        ArrayList<Producto> listaCompra = new ArrayList<Producto>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ProductosContract.CompraEntry.COLUMN_CODIGO
                + ", " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.CompraEntry.COLUMN_PRECIO
                + ", " + ProductosContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + ProductosContract.CompraEntry.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        Producto producto = null;

        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_TOTAL));

                producto = new Producto(codigo, nombre, proveedor, cantidad, precio, total);
                producto.setCodigo(codigo);

                listaCompra.add(producto);
            } while (cursor.moveToNext());
        }

        close(database);
        return listaCompra;
    }


    public ArrayList<Producto> leerProductoPorCodigo(String productoB) {
        ArrayList<Producto> lista = new ArrayList<Producto>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ProductosContract.CompraEntry.COLUMN_CODIGO
                + ", " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.CompraEntry.COLUMN_PRECIO
                + ", " + ProductosContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + ProductosContract.CompraEntry.TABLE_NAME
                + " WHERE " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + " LIKE '%" + productoB + "%'";

        Cursor cursor = database.rawQuery(query, null);

        Producto compra = null;
        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_TOTAL));

                compra = new Producto(codigo, nombre, proveedor, cantidad, precio, total);
                compra.setNombre(nombre);

                lista.add(compra);

            } while (cursor.moveToNext());
        }

        close(database);
        return lista;

    }

/*

    public ArrayList<ProductoVenta> leerProductoPorCodigo(String productoB) {
        ArrayList<ProductoVenta> lista = new ArrayList<ProductoVenta>();
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ProductosContract.CompraEntry.COLUMN_CODIGO
                + ", " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.CompraEntry.COLUMN_PRECIO
                + ", " + ProductosContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + ProductosContract.CompraEntry.TABLE_NAME
                + " WHERE " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + " LIKE '%" + productoB + "%'";

        Cursor cursor = database.rawQuery(query, null);

        Producto registro = null;
        Long codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getLong(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.CompraEntry.COLUMN_TOTAL));

                registro = new ProductoVenta(nombre, proveedor, cantidad, precio, total);
                registro.setCodigo(codigo);

                lista.add(registro);

            } while (cursor.moveToNext());
        }

        close(database);
        return lista;

    }
 */

    public Producto leerCompraa(long sCodigo) {
        Producto compra = null;
        SQLiteDatabase database = openReadable();

        String query = "SELECT "
                + ProductosContract.CompraEntry.COLUMN_CODIGO
                + ", " + ProductosContract.CompraEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.CompraEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.CompraEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.CompraEntry.COLUMN_PRECIO
                + ", " + ProductosContract.CompraEntry.COLUMN_TOTAL
                + " FROM " + ProductosContract.CompraEntry.TABLE_NAME
                + " WHERE "
                + ProductosContract.CompraEntry.COLUMN_CODIGO + " = ?";
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
                    ProductosContract.CompraEntry.COLUMN_CODIGO));
            nombre = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.CompraEntry.COLUMN_NOMBRE));
            proveedor = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.CompraEntry.COLUMN_PROVEEDOR));
            cantidad = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.CompraEntry.COLUMN_CANTIDAD));
            precio = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.CompraEntry.COLUMN_PRECIO));
            total = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.CompraEntry.COLUMN_TOTAL));

            compra = new Producto(codigo, nombre, proveedor, cantidad, precio, total);
            compra.setCodigo(codigo);


        }
        cursor.close();
        close(database);
        return compra ;

    }

    public void actualizarAlmacen(Producto compra) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();

        ContentValues compraValues = new ContentValues();
        compraValues.put(ProductosContract.CompraEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(ProductosContract.CompraEntry.COLUMN_TOTAL,
                compra.getTotal());

        String [] whereArgs = { String.valueOf(compra.getCodigo()) };
        database.update(ProductosContract.CompraEntry.TABLE_NAME,
                compraValues,
                ProductosContract.CompraEntry.COLUMN_CODIGO + " = ?",
                whereArgs);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }



}