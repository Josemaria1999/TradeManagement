package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trademanagement.db.CompraPersistencia;
import com.example.trademanagement.model.Compra;
import com.example.trademanagement.rvUtils.CompraAdapter;

import java.util.ArrayList;

public class AlmacenActivity extends AppCompatActivity {

    RecyclerView rv;
    CompraAdapter ca;
    LinearLayoutManager llm;
    CompraPersistencia cp;
    ArrayList<Compra> listaCompra;
    EditText etProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen);

        etProducto = findViewById(R.id.etProductosV);
        rv = findViewById(R.id.rvProductos);
        cp = new CompraPersistencia(this);
        listaCompra = cp.leerCompra();

        configurarRV();
    }


    private void configurarRV() {
        ca = new CompraAdapter(listaCompra);
        llm = new LinearLayoutManager(this);

        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(ca);
    }




    public void consultar(View view) {

        String producto = etProducto.getText().toString().trim();
        if(producto.isEmpty()){
            listaCompra = cp.leerCompra();

        }else {
            listaCompra = cp.leerCompraPorProducto(producto);

        }
        configurarRV();
    }
}
