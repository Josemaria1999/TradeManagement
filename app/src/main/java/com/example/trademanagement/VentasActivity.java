package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trademanagement.db.ProductosPersistencia;
import com.example.trademanagement.db.VentaPersistencia;
import com.example.trademanagement.model.Producto;

public class VentasActivity extends AppCompatActivity {
    EditText etCodigoV;
    EditText etNombreV;
    EditText etProveedorV;
    EditText etCantidadV;
    EditText etPrecioCompra;
    EditText etTotalV;
    EditText etCantidadVent;
    EditText etPrecioVenta;
    EditText etTotalVent;
    EditText etDiferencia;

    VentaPersistencia vp;
    ProductosPersistencia cp;
    Button btnBuscarV;
    Button btnVender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        etCodigoV = findViewById(R.id.etCodigoV);
        etNombreV = findViewById(R.id.etNombreV);
        etProveedorV = findViewById(R.id.etProveedorV);
        etCantidadV = findViewById(R.id.etCantidadV);
        etPrecioCompra = findViewById(R.id.etPrecioVentaV);
        etTotalV = findViewById(R.id.etTotalV);
        etCantidadVent = findViewById(R.id.etCantidadVendida);
        etPrecioVenta = findViewById(R.id.etPrecioVenta);
        etTotalVent = findViewById(R.id.etTotalVendido);
        etDiferencia = findViewById(R.id.etDiferencia);


        etDiferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularDiferencia();
            }
        });
        etTotalVent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalVentas();
            }
        });
        etCantidadV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarStock();
            }
        });

        btnBuscarV = findViewById(R.id.btnBuscarV);
        btnVender = findViewById(R.id.btnVender);


        vp = new VentaPersistencia(this);
        cp = new ProductosPersistencia(this);
    }
    private void actualizarStock() {
        String cantidadInicial = etCantidadV.getText().toString().trim();
        String cantidadVendida = etCantidadVent.getText().toString().trim();
        int canInicialInt = Integer.parseInt(cantidadInicial);
        int canVendidaInt = Integer.parseInt(cantidadVendida);

        int stockActual = canInicialInt - canVendidaInt;

        String sumaTotalS = Integer.toString(stockActual);

        etCantidadV.setText(sumaTotalS);
    }
    private void calcularDiferencia() {
        String totalG = etTotalV.getText().toString().trim();
        String totalV = etTotalVent.getText().toString().trim();
        int totalGInt = Integer.parseInt(totalG);
        int totalVInt = Integer.parseInt(totalV);

        int result = totalVInt - totalGInt;

        String sumaTotalS = Integer.toString(result);

        etDiferencia.setText(sumaTotalS);
    }

    private void totalVentas() {
        String cantidadX = etCantidadVent.getText().toString().trim();
        String precioX = etPrecioVenta.getText().toString().trim();
        int cantidadInt = Integer.parseInt(cantidadX);
        int precioInt = Integer.parseInt(precioX);

        int sumaTotal = cantidadInt * precioInt;

        String sumaTotalS = Integer.toString(sumaTotal);

        etTotalVent.setText(sumaTotalS);
    }

    public void buscarV(View view) {
        String sCodigoV = etCodigoV.getText().toString().trim();

        if (sCodigoV.isEmpty()) {
            Toast.makeText(this, R.string.no_codigo, Toast.LENGTH_LONG).show();

        } else {
            int codigoV = Integer.parseInt(sCodigoV);
            Producto venta = cp.leerCompraa(codigoV);

            if (venta != null) {
                etNombreV.setText(venta.getNombre());
                etProveedorV.setText(venta.getProveedor());
                etCantidadV.setText(venta.getCantidad());
                etPrecioCompra.setText(venta.getPrecio());
                etTotalV.setText(venta.getTotal());
                etPrecioVenta.setText(venta.getPrecioVenta());
                etTotalVent.setText(venta.getTotalVenta());
                etDiferencia.setText(venta.getDiferencia());
                habilitar(true);
            } else {
                Toast.makeText(this, R.string.no_producto, Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, CompraActivity.class);
                startActivity(i);
            }

        }
    }

        public void reiniciarV (View view){
        etCodigoV.setText("");
        etNombreV.setText("");
        etProveedorV.setText("");
        etCantidadV.setText("");
        etPrecioCompra.setText("");
        etTotalV.setText("");
        etCantidadVent.setText("");
        etPrecioVenta.setText("");
        etTotalVent.setText("");
        etDiferencia.setText("");
        }

        public void venderACliente(View view){
            String codigo = etCodigoV.getText().toString().trim();
            String nombre = etNombreV.getText().toString().trim();
            String proveedor = etProveedorV.getText().toString().trim();
            String cantidad = etCantidadV.getText().toString().trim();
            String precioCompra = etPrecioCompra.getText().toString().trim();
            String total = etTotalV.getText().toString().trim();
            String cantidadVendida = etCantidadVent.getText().toString().trim();
            String precioVenta = etPrecioVenta.getText().toString().trim();
            String totalVendido = etTotalVent.getText().toString().trim();
            String diferencia = etDiferencia.getText().toString().trim();


            if(codigo.isEmpty() || nombre.isEmpty() || proveedor.isEmpty() || cantidad.isEmpty()|| precioCompra.isEmpty()|| total.isEmpty()){
                Toast.makeText(this, getString(R.string.no_datos),
                        Toast.LENGTH_LONG).show();
            }else {

                Producto venta = new Producto(codigo, nombre, proveedor, cantidad, precioCompra, total, cantidadVendida, precioVenta, totalVendido, diferencia);
                venta.setCodigo(codigo);
                cp.actualizarAlmacen(venta);

                Toast.makeText(this, getString(R.string.update_ok),
                        Toast.LENGTH_LONG).show();

            }


        }


        private void habilitar ( boolean b){
            etCodigoV.setEnabled(b);

            etNombreV.setEnabled(!b);
            etProveedorV.setEnabled(!b);
            etCantidadV.setEnabled(b);
            etPrecioCompra.setEnabled(!b);
            etTotalV.setEnabled(!b);


            //VENTA
            etCantidadVent.setEnabled(b);
            etPrecioVenta.setEnabled(b);
            etTotalVent.setEnabled(b);
            etDiferencia.setEnabled(b);

            btnVender.setEnabled(b);
        }


}

