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

public class ModificarProductosAlmacen extends AppCompatActivity {
    EditText etCodigo;
    EditText etNombre;
    EditText etProveedor;
    EditText etCantidad;
    EditText etPrecioCompra;
    EditText etTotal;
    ProductosPersistencia productosP;

    Button btnBuscar;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_productos_almacen);
        etCodigo = findViewById(R.id.etCodigoV);
        etNombre = findViewById(R.id.etNombreM);
        etProveedor = findViewById(R.id.etProveedorM);
        etCantidad = findViewById(R.id.etCantidadM);
        etPrecioCompra = findViewById(R.id.etPrecioCompraM);
        etTotal = findViewById(R.id.etTotalM);

        btnBuscar = findViewById(R.id.btnBuscarV);
        btnGuardar = findViewById(R.id.btnGuardarM);
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

                habilitar(true);
            }else {
                Toast.makeText(this, R.string.no_producto, Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, CompraActivity.class);
                startActivity(i);
            }
        }
    }

    private void habilitar(boolean b) {
        etCodigo.setEnabled(b);

        etNombre.setEnabled(b);
        etProveedor.setEnabled(b);
        etCantidad.setEnabled(b);
        etPrecioCompra.setEnabled(b);
        etTotal.setEnabled(b);

        btnGuardar.setEnabled(b);
    }

    public void actualizar(View view) {
        String codigo = etCodigo.getText().toString().trim();
        String nombre = etNombre.getText().toString().trim();
        String proveedor = etProveedor.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String precioCompra = etPrecioCompra.getText().toString().trim();
        String total = etTotal.getText().toString().trim();

        if(codigo.isEmpty() || nombre.isEmpty() || proveedor.isEmpty() || cantidad.isEmpty()|| precioCompra.isEmpty()|| total.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else {

            Producto compra = new Producto(codigo, nombre, proveedor, cantidad, precioCompra, total);
            compra.setCodigo(codigo);
           productosP.actualizarAlmacen(compra);

            Toast.makeText(this, getString(R.string.update_ok),
                    Toast.LENGTH_LONG).show();

        }


    }
}
