package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trademanagement.db.VentaPersistencia;
import com.example.trademanagement.model.Venta;
import com.example.trademanagement.rvUtils.VentaAdapter;

import java.util.ArrayList;

public class RegistroVentas extends AppCompatActivity {
    RecyclerView rvV;
    VentaAdapter caV;
    LinearLayoutManager llmV;
    VentaPersistencia vP;
    ArrayList<Venta> listaVenta;
    EditText etProductoV;
    EditText etCodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ventas);
        etProductoV = findViewById(R.id.etProductosV);
        etCodigo = (EditText) findViewById(R.id.etCodigoV);
        rvV = (RecyclerView) findViewById(R.id.rvProductos);
        vP = new VentaPersistencia(this);
        //listaVenta = vP.leerVenta();

        configurarRV();
    }

    private void configurarRV() {
        caV = new VentaAdapter(listaVenta);
        llmV = new LinearLayoutManager(this);

        rvV.setLayoutManager(llmV);
        rvV.setHasFixedSize(true);
        rvV.setAdapter(caV);
    }


    public void consultarV(View view) {
        String producto = etProductoV.getText().toString().trim();
        if(producto.isEmpty()){
            String cod = etCodigo.getText().toString().trim();
            VentaPersistencia vp = new VentaPersistencia(RegistroVentas.this);
            vp.leerVenta(cod);

        }else {
            listaVenta = vP.leerVentaPorProducto(producto);

        }
        configurarRV();
    }
}
