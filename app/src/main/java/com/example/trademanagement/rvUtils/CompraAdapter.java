package com.example.trademanagement.rvUtils;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trademanagement.R;
import com.example.trademanagement.VentasActivity;
import com.example.trademanagement.model.Compra;

import java.util.ArrayList;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraVH> {
    private ArrayList<Compra> lista;

    public CompraAdapter(ArrayList<Compra> listaCompra) {
        this.lista = listaCompra;
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
       public CompraVH(@NonNull View v) {
           super(v);
           tvCodigo = v.findViewById(R.id.tvCodigoI);
           tvNombre = v.findViewById(R.id.tvNombreI);
           tvProveedor = v.findViewById(R.id.tvProveedorI);
           tvCantidad = v.findViewById(R.id.tvCantidadI);
           tvPrecio = v.findViewById(R.id.tvPrecioI);
           tvTotal = v.findViewById(R.id.tvTotalI);

       }
       /*public void venderPantalla(View v){
           Intent i = new Intent(this, VentasActivity.class);
            startActivity(i);
       }*/

       public void bindCompra(Compra c){
           tvCodigo.setText("Código: " + c.getCodigo() );
           tvNombre.setText("Nombre: " + c.getNombre());
           tvProveedor.setText("Proveedor: " + c.getProveedor());
           tvCantidad.setText("Cantidad: "+ c.getCantidad());
           tvPrecio.setText("Precio: " + c.getPrecio() + "€");
           tvTotal.setText("Total: " + c.getTotal()+ "€");



       }

   }


}