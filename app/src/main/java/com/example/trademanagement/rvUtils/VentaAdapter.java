package com.example.trademanagement.rvUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trademanagement.R;
import com.example.trademanagement.model.Producto;
import com.example.trademanagement.model.Venta;

import java.util.ArrayList;

public class VentaAdapter extends RecyclerView.Adapter {

    private ArrayList<Venta> lista;

    public VentaAdapter(ArrayList<Venta> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class VentaVH extends RecyclerView.ViewHolder{
        private TextView tvCodigoV;
        private TextView tvCliente;
        private TextView tvNombreV;
        private TextView tvProveedorV;
        private TextView tvCantidadV;
        private TextView tvPrecioV;
        private TextView tvTotalV;

        public VentaVH(@NonNull View v) {
            super(v);
            tvCodigoV = v.findViewById(R.id.tvCodigoI);
            tvNombreV = v.findViewById(R.id.tvNombreI);
            tvProveedorV = v.findViewById(R.id.tvProveedorI);
            tvCantidadV = v.findViewById(R.id.tvCantidadI);
            tvPrecioV = v.findViewById(R.id.tvPrecioCompra);
            tvTotalV = v.findViewById(R.id.tvTotalCompra);
        }

        public void bindCompra(Producto c){
            tvCodigoV.setText("Código: " + c.getCodigo() );
            tvNombreV.setText("Nombre: " + c.getNombre());
            tvProveedorV.setText("Proveedor: " + c.getProveedor());
            tvCantidadV.setText("Cantidad: "+ c.getCantidad());
            tvPrecioV.setText("Precio: " + c.getPrecio() + "€");
            tvTotalV.setText("Total: " + c.getTotal()+ "€");
        }
    }
}
