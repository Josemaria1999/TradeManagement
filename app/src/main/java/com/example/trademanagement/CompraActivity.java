package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trademanagement.db.CompraPersistencia;
import com.example.trademanagement.model.Compra;

public class CompraActivity extends AppCompatActivity {
    EditText etCodigo;
    EditText etNombre;
    EditText etProveedor;
    EditText etCantidad;
    EditText etPrecioCompra;
    EditText etTotal;
    CompraPersistencia productosP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        etCodigo = findViewById(R.id.etCodigoM);
        etNombre = findViewById(R.id.etNombreM);
        etProveedor = findViewById(R.id.etProveedorM);
        etCantidad = findViewById(R.id.etCantidadM);
        etPrecioCompra = findViewById(R.id.etPrecioCompraM);
        etTotal = findViewById(R.id.etTotalM);

        productosP = new CompraPersistencia(this);

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
            Compra compra = new Compra(codigo, nombre, proveedor, cantidad, precioCompra, total);
            long id = productosP.insertarCompra(compra);

            if (id != -1) {
                Toast.makeText(this, getString(R.string.insert_ok),
                        Toast.LENGTH_LONG).show();
                compra.setCodigo(codigo);
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
