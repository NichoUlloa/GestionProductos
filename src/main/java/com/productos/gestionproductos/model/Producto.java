package com.productos.gestionproductos.model;

public class Producto {
    // atributos Producto: idProducto, nombreProducto, precioProducto, stockProducto
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;
    private Marca marca;
    private Categoria categoria;

    // constructor Producto
    public Producto(int idProducto, String nombreProducto, double precioProducto, int stockProducto, Marca marca, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.marca = marca;
        this.categoria = categoria;
    }

    // getters y setters

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
