package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trademanagement.db.ProductosPersistencia;
import com.example.trademanagement.model.Producto;

public class CompraActivity extends AppCompatActivity {
    EditText etCodigo;
    EditText etNombre;
    EditText etProveedor;
    EditText etCantidad;
    EditText etPrecioCompra;
    EditText etTotal;
    EditText etCantidadVendida;
    EditText etPrecioVenta;
    EditText etTotalVendido;
    EditText etDiferencia;
    ProductosPersistencia productosP;
    Button btnBuscar;
    Button btnGuardar;
    Button btnActualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        etCodigo = findViewById(R.id.etCodigoV);
        etNombre = findViewById(R.id.etNombreM);
        etProveedor = findViewById(R.id.etProveedorM);
        etCantidad = findViewById(R.id.etCantidadM);
        etPrecioCompra = findViewById(R.id.etPrecioCompraM);
        etTotal = findViewById(R.id.etTotalM);
        etCantidadVendida = findViewById(R.id.etCantidadVendida);
        etPrecioVenta = findViewById(R.id.etPrecioVenta);
        etTotalVendido = findViewById(R.id.etTotalVendido);
        etDiferencia = findViewById(R.id.etDiferencia);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnGuardar = findViewById(R.id.btnGuardarM);
        btnActualizar = findViewById(R.id.btnAct);


        productosP = new ProductosPersistencia(this);

        etTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total();
            }
        });
    }
    public void total(){

        String cantidadX = etCantidad.getText().toString().trim();
        String precioX = etPrecioCompra.getText().toString().trim();
        int cantidadInt = Integer.parseInt(cantidadX);
        int precioInt = Integer.parseInt(precioX);

        int sumaTotal = cantidadInt * precioInt;

        String sumaTotalS = Integer.toString(sumaTotal);

        etTotal.setText(sumaTotalS);

    }
    public void guardar(View view) {
        //TODO
        String codigo = etCodigo.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String proveedor = etProveedor.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String precioCompra = etPrecioCompra.getText().toString().trim();
        String total = etTotal.getText().toString().trim();
        String cantidadVendida = etCantidadVendida.getText().toString().trim();
        String precioVenta = etPrecioVenta.getText().toString().trim();
        String totalVendido = etTotalVendido.getText().toString().trim();
        String diferencia = etDiferencia.getText().toString().trim();

        if(codigo.isEmpty() || nombre.isEmpty() || proveedor.isEmpty() || cantidad.isEmpty()|| precioCompra.isEmpty()|| total.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else{
            Producto producto = new Producto(codigo, nombre, proveedor, cantidad, precioCompra, total, cantidadVendida, precioVenta, totalVendido, diferencia);
            long id = productosP.insertarCompra(producto);

            if (id != -1) {
                Toast.makeText(this, getString(R.string.insert_ok),
                        Toast.LENGTH_LONG).show();
                producto.setCodigo(codigo);
            } else {
                Toast.makeText(this, getString(R.string.insert_ko),
                        Toast.LENGTH_LONG).show();




            }

        }


    }

    public void actualizar(View view) {
        String codigo = etCodigo.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String proveedor = etProveedor.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String precioCompra = etPrecioCompra.getText().toString().trim();
        String total = etTotal.getText().toString().trim();
        String cantidadVendida = etCantidadVendida.getText().toString().trim();
        String precioVenta = etPrecioVenta.getText().toString().trim();
        String totalVendido = etTotalVendido.getText().toString().trim();
        String diferencia = etDiferencia.getText().toString().trim();

        if(codigo.isEmpty() || nombre.isEmpty() || proveedor.isEmpty() || cantidad.isEmpty()|| precioCompra.isEmpty()|| total.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else {

            Producto compra = new Producto(codigo, nombre, proveedor, cantidad, precioCompra, total, cantidadVendida, precioVenta, totalVendido, diferencia);
            compra.setCodigo(codigo);
            productosP.actualizarAlmacen(compra);

            Toast.makeText(this, getString(R.string.update_ok),
                    Toast.LENGTH_LONG).show();

        }


    }

    public void buscar(View view) {
        String sCodigo = etCodigo.getText().toString().trim();

        if(sCodigo.isEmpty()){
            Toast.makeText(this, R.string.no_codigo, Toast.LENGTH_LONG).show();

        }else {
            int codigo = Integer.parseInt(sCodigo);
            Producto producto = productosP.leerCompraa(codigo);

            if(producto != null){
                etNombre.setText(producto.getNombre());
                etProveedor.setText(producto.getProveedor());
                etCantidad.setText(producto.getCantidad());
                etPrecioCompra.setText(producto.getPrecio());
                etTotal.setText(producto.getTotal());
                etCantidadVendida.setText(producto.getCantidadVendida());
                etPrecioVenta.setText(producto.getPrecioVenta());
                etTotalVendido.setText(producto.getTotalVenta());
                etDiferencia.setText(producto.getDiferencia());

                habilitar(false);
            }else {
                Toast.makeText(this, R.string.no_producto, Toast.LENGTH_LONG).show();
                habilitar(false);
            }
        }
    }
    private void habilitar(boolean b) {
        etCodigo.setEnabled(b);

        etNombre.setEnabled(!b);
        etProveedor.setEnabled(!b);
        etCantidad.setEnabled(!b);
        etPrecioCompra.setEnabled(!b);
        etTotal.setEnabled(!b);
        etCantidadVendida.setEnabled(!b);
        etPrecioVenta.setEnabled(!b);
        etTotalVendido.setEnabled(!b);
        etDiferencia.setEnabled(!b);

        btnActualizar.setEnabled(!b);
        btnBuscar.setEnabled(!b);
        btnGuardar.setEnabled(!b);

    }

    public void reiniciar(View view) {
        etCodigo.setText("");
        etNombre.setText("");
        etProveedor.setText("");
        etCantidad.setText("");
        etPrecioCompra.setText("");
        etTotal.setText("");
    }
}
