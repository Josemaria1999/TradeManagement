package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    ProductosPersistencia productosP;
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

        if(codigo.isEmpty() || nombre.isEmpty() || proveedor.isEmpty() || cantidad.isEmpty()|| precioCompra.isEmpty()|| total.isEmpty()){
            Toast.makeText(this, getString(R.string.no_datos),
                    Toast.LENGTH_LONG).show();
        }else{
            Producto producto = new Producto(codigo, nombre, proveedor, cantidad, precioCompra, total);
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

    public void reiniciar(View view) {
        etCodigo.setText("");
        etNombre.setText("");
        etProveedor.setText("");
        etCantidad.setText("");
        etPrecioCompra.setText("");
        etTotal.setText("");
    }
}
