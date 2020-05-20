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
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_CODIGO,
                compra.getCodigo());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_TOTAL,
                compra.getTotal());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA,
                compra.getCantidadVendida());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA,
                compra.getPrecioVenta());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS,
                compra.getTotalVenta());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_DIFERENCIA,
                compra.getDiferencia());


        long codigo = database.insert(ProductosContract.ProductoEntry.TABLE_NAME,
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
                + ProductosContract.ProductoEntry.COLUMN_CODIGO
                + ", " + ProductosContract.ProductoEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.ProductoEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS
                + ", " + ProductosContract.ProductoEntry.COLUMN_DIFERENCIA
                + " FROM " + ProductosContract.ProductoEntry.TABLE_NAME;

        Cursor cursor = database.rawQuery(query, null);

        Producto producto = null;

        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;
        String cantidadVendida;
        String precioVenta;
        String totalVentas;
        String diferncia;

        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_TOTAL));
                cantidadVendida = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA));
                precioVenta = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA));
                totalVentas = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS));
                diferncia = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_DIFERENCIA));

                producto = new Producto(codigo, nombre, proveedor, cantidad, precio, total, cantidadVendida, precioVenta, totalVentas, diferncia);
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
                + ProductosContract.ProductoEntry.COLUMN_CODIGO
                + ", " + ProductosContract.ProductoEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.ProductoEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS
                + ", " + ProductosContract.ProductoEntry.COLUMN_DIFERENCIA
                + " FROM " + ProductosContract.ProductoEntry.TABLE_NAME
                + " WHERE " + ProductosContract.ProductoEntry.COLUMN_NOMBRE
                + " LIKE '%" + productoB + "%'";

        Cursor cursor = database.rawQuery(query, null);

        Producto compra = null;
        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;
        String cantidadVendida;
        String precioVenta;
        String totalVentas;
        String diferencia;
        if (cursor.moveToFirst()) {
            do {
                codigo = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CODIGO));
                nombre = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_NOMBRE));
                proveedor = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PROVEEDOR));
                cantidad = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CANTIDAD));
                precio = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PRECIO));
                total = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_TOTAL));
                cantidadVendida = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA));
                precioVenta = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA));
                totalVentas = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS));
                diferencia = cursor.getString(cursor.getColumnIndex(
                        ProductosContract.ProductoEntry.COLUMN_DIFERENCIA));

                compra = new Producto(codigo, nombre, proveedor, cantidad, precio, total, cantidadVendida, precioVenta, totalVentas, diferencia);
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
                + ProductosContract.ProductoEntry.COLUMN_CODIGO
                + ", " + ProductosContract.ProductoEntry.COLUMN_NOMBRE
                + ", " + ProductosContract.ProductoEntry.COLUMN_PROVEEDOR
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL
                + ", " + ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA
                + ", " + ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA
                + ", " + ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS
                + ", " + ProductosContract.ProductoEntry.COLUMN_DIFERENCIA
                + " FROM " + ProductosContract.ProductoEntry.TABLE_NAME
                + " WHERE "
                + ProductosContract.ProductoEntry.COLUMN_CODIGO + " = ?";
        String[] whereArgs = {String.valueOf(sCodigo)};

        Cursor cursor = database.rawQuery(query, whereArgs);

        String codigo;
        String nombre;
        String proveedor;
        String cantidad;
        String precio;
        String total;
        String cantidadVentas;
        String precioVenta;
        String totalVendido;
        String diferencia;
        if (cursor.moveToFirst()) {
            codigo = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_CODIGO));
            nombre = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_NOMBRE));
            proveedor = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_PROVEEDOR));
            cantidad = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_CANTIDAD));
            precio = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_PRECIO));
            total = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_TOTAL));
            cantidadVentas = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA));
            precioVenta = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA));
            totalVendido = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS));
            diferencia = cursor.getString(cursor.getColumnIndex(
                    ProductosContract.ProductoEntry.COLUMN_DIFERENCIA));


            compra = new Producto(codigo, nombre, proveedor, cantidad, precio, total, cantidadVentas, precioVenta, totalVendido, diferencia);
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
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_NOMBRE,
                compra.getNombre());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PROVEEDOR,
                compra.getProveedor());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_CANTIDAD,
                compra.getCantidad());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PRECIO,
                compra.getPrecio());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_TOTAL,
                compra.getTotal());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_CANTIDAD_VENDIDA,
                compra.getCantidadVendida());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_PRECIO_VENTA,
                compra.getPrecioVenta());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_TOTAL_GANANCIAS,
                compra.getTotalVenta());
        compraValues.put(ProductosContract.ProductoEntry.COLUMN_DIFERENCIA,
                compra.getDiferencia());

        String [] whereArgs = { String.valueOf(compra.getCodigo()) };
        database.update(ProductosContract.ProductoEntry.TABLE_NAME,
                compraValues,
                ProductosContract.ProductoEntry.COLUMN_CODIGO + " = ?",
                whereArgs);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }



}