package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.Producto;
import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "mostrarProductoServlet", value = "/mostrarProductos")
public class MostrarProductoServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("ProductosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("productos", obtenerProductos());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RequestDispatcher respuesta = req.getRequestDispatcher("/mostrarProductos.jsp");
        respuesta.forward(req, resp);
    }

    private List<Producto> obtenerProductos() throws ClassNotFoundException {
        List<Producto> productos = new ArrayList<>();
        productos = ProductoDAO.obtenerProductos(DBGenerator.conectarBD("ProductosBD"));
        return productos;
    }
}
