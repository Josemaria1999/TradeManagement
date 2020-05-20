package com.example.trademanagement.model;

public class Producto {
    private String codigo;
    private String nombre;
    private String proveedor;
    private String cantidad;
    private String precio;
    private String total;
    private String cantidadVendida;
    private String precioVenta;
    private String totalVenta;
    private String diferencia;

    public Producto(String codigo, String nombre, String proveedor, String cantidad, String precio, String total,
                    String cantidadVendida, String precioVenta, String totalVenta, String diferencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.cantidadVendida = cantidadVendida;
        this.precioVenta = precioVenta;
        this.totalVenta = totalVenta;
        this.diferencia = diferencia;
    }

    public String getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia;
    }

    public String getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(String cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(String totalVenta) {
        this.totalVenta = totalVenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
