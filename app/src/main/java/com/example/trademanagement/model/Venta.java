package com.example.trademanagement.model;

public class Venta {

    private String codigoV;
    private String nombreV;
    private String proveedorV;
    private String cantidadV;
    private String precioV;
    private String totalV;

    public Venta(String codigoV,String nombreV, String proveedorV, String cantidadV, String precioV, String totalV) {
        this.codigoV = codigoV;
        this.nombreV = nombreV;
        this.proveedorV = proveedorV;
        this.cantidadV = cantidadV;
        this.precioV = precioV;
        this.totalV = totalV;
    }


    public String getCodigoV() {
        return codigoV;
    }

    public void setCodigoV(String codigoV) {
        this.codigoV = codigoV;
    }

    public String getNombreV() {
        return nombreV;
    }

    public void setNombreV(String nombreV) {
        this.nombreV = nombreV;
    }

    public String getProveedorV() {
        return proveedorV;
    }

    public void setProveedorV(String proveedorV) {
        this.proveedorV = proveedorV;
    }

    public String getCantidadV() {
        return cantidadV;
    }

    public void setCantidadV(String cantidadV) {
        this.cantidadV = cantidadV;
    }

    public String getPrecioV() {
        return precioV;
    }

    public void setPrecioV(String precioV) {
        this.precioV = precioV;
    }

    public String getTotalV() {
        return totalV;
    }

    public void setTotalV(String totalV) {
        this.totalV = totalV;
    }
}
