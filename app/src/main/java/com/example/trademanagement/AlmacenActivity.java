package com.example.trademanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trademanagement.db.ProductosPersistencia;
import com.example.trademanagement.model.Producto;
import com.example.trademanagement.rvUtils.ProductosAdapter;

import java.util.ArrayList;

public class AlmacenActivity extends AppCompatActivity {

    RecyclerView rv;
    ProductosAdapter ca;
    LinearLayoutManager llm;
    ProductosPersistencia cp;
    ArrayList<Producto> listaCompra;
    EditText etProducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacen);

        etProducto = findViewById(R.id.etProductosV);
        rv = findViewById(R.id.rvProductos);
        cp = new ProductosPersistencia(this);
        listaCompra = cp.leerCompra();

        configurarRV();
    }


    private void configurarRV() {
        ca = new ProductosAdapter(listaCompra,AlmacenActivity.this);
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
            listaCompra = cp.leerProductoPorCodigo(producto);

        }
        configurarRV();
    }
}
