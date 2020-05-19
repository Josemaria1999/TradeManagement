package com.example.trademanagement.rvUtils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trademanagement.CompraActivity;
import com.example.trademanagement.ModificarProductosAlmacen;
import com.example.trademanagement.R;
import com.example.trademanagement.VentasActivity;
import com.example.trademanagement.model.Producto;

import java.util.ArrayList;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.CompraVH> {
    private ArrayList<Producto> lista;
    private Activity _activity;
    private Intent intent;

    public ProductosAdapter(ArrayList<Producto> listaCompra,Activity activity) {
        this.lista = listaCompra;
        this._activity = activity;
    }

    @NonNull
    @Override
    public CompraVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.compra_item, parent, false);


        return new CompraVH(v);
    }



    @Override
    public void onBindViewHolder(@NonNull CompraVH holder, int position) {
        holder.bindCompra(lista.get(position));

        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioVentana(VentasActivity.class);
            }
        });

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioVentana(ModificarProductosAlmacen.class);
            }
        });
    }
    void cambioVentana(Class goTo){
        intent = new Intent(_activity, goTo);
        _activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }



   public class CompraVH extends RecyclerView.ViewHolder{
        private TextView tvCodigo;
        private TextView tvNombre;
        private TextView tvProveedor;
        private TextView tvCantidad;
        private TextView tvPrecio;
        private TextView tvTotal;
        private Button btn1, btn2;
       public CompraVH(@NonNull View v) {
           super(v);
           tvCodigo = v.findViewById(R.id.tvCodigoI);
           tvNombre = v.findViewById(R.id.tvNombreI);
           tvProveedor = v.findViewById(R.id.tvProveedorI);
           tvCantidad = v.findViewById(R.id.tvCantidadI);
           tvPrecio = v.findViewById(R.id.tvPrecioI);
           tvTotal = v.findViewById(R.id.tvTotalI);
           btn1 = v.findViewById(R.id.btnComprar);
           btn2 = v.findViewById(R.id.btnAniadir);



       }


       public void bindCompra(Producto c){
           tvCodigo.setText("Código: " + c.getCodigo() );
           tvNombre.setText("Nombre: " + c.getNombre());
           tvProveedor.setText("Proveedor: " + c.getProveedor());
           tvCantidad.setText("Cantidad: "+ c.getCantidad());
           tvPrecio.setText("Precio: " + c.getPrecio() + "€");
           tvTotal.setText("Total: " + c.getTotal()+ "€");



       }

   }


}