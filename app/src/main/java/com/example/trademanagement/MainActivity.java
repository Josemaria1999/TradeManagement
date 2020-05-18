package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void venderAClientes(View view) {
        Intent i = new Intent(this, VentasActivity.class);
        startActivity(i);    }
   /* public void vender(View view) {
        Intent i = new Intent(this, VentasActivity.class);
        startActivity(i);    }
*/
    public void comprarAProveedores(View view) {
        Intent i = new Intent(this, CompraActivity.class);
        startActivity(i);    }

    public void almacen(View view) {
        Intent i = new Intent(this, AlmacenActivity.class);
        startActivity(i);    }

    public void cuentaResultados(View view) {
        Intent i = new Intent(this, CuentaResultadosActivity.class);
        startActivity(i);    }

    public void comprarAProveedoores(View view) {
        Intent i = new Intent(this, ModificarProductosAlmacen.class);
        startActivity(i);
    }

    public void registroVentas(View view) {
        Intent i = new Intent(this, RegistroVentas.class);
        startActivity(i);
    }
}
