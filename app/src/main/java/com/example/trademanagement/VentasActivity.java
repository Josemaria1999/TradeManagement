package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trademanagement.db.CompraPersistencia;
import com.example.trademanagement.db.VentaPersistencia;
import com.example.trademanagement.model.Venta;

public class VentasActivity extends AppCompatActivity {
    EditText etCodigoV;
    EditText etNombreV;
    EditText etProveedorV;
    EditText etCantidadV;
    EditText etPrecioVenta;
    EditText etTotalV;
    VentaPersistencia vp;
    CompraPersistencia cp;
    Button btnBuscarV;
    Button btnGuardarV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        etCodigoV = findViewById(R.id.etCodigoM);
        etNombreV = findViewById(R.id.etNombreV);
        etProveedorV = findViewById(R.id.etProveedorV);
        etCantidadV = findViewById(R.id.etCantidadV);
        etPrecioVenta = findViewById(R.id.etPrecioVentaV);
        etTotalV = findViewById(R.id.etTotalV);

        btnBuscarV = findViewById(R.id.btnBuscarV);
        btnGuardarV = findViewById(R.id.btnGuardarV);
        vp = new VentaPersistencia(this);
        cp = new CompraPersistencia(this);
    }

    public void buscarV(View view) {
        String sCodigoV = etCodigoV.getText().toString().trim();

        if (sCodigoV.isEmpty()) {
            Toast.makeText(this, R.string.no_codigo, Toast.LENGTH_LONG).show();

        } else {
            int codigoV = Integer.parseInt(sCodigoV);
            Venta venta = vp.leerVentas(codigoV);

            if (venta != null) {
                etNombreV.setText(venta.getNombreV());
                etProveedorV.setText(venta.getProveedorV());
                etCantidadV.setText(venta.getCantidadV());
                etPrecioVenta.setText(venta.getPrecioV());
                etTotalV.setText(venta.getTotalV());

                habilitar(true);
            } else {
                Toast.makeText(this, R.string.no_producto, Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, CompraActivity.class);
                startActivity(i);
            }

        }
    }

        public void reiniciarV (View view){
        }

        public void guardarV (View view){
        }
        private void habilitar ( boolean b){
            etCodigoV.setEnabled(b);

            etNombreV.setEnabled(b);
            etProveedorV.setEnabled(b);
            etCantidadV.setEnabled(b);
            etPrecioVenta.setEnabled(b);
            etTotalV.setEnabled(b);

            btnGuardarV.setEnabled(b);
        }
    }

