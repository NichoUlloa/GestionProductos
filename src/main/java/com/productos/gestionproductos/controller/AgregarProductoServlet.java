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
import java.util.List;
import org.jooq.DSLContext;

@WebServlet(name = "agregarProductoServlet", value = "/agregarProducto")
public class AgregarProductoServlet extends HttpServlet {
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
        RequestDispatcher respuesta = req.getRequestDispatcher("/agregarProducto.jsp");
        respuesta.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if (req.getParameter("nombre").length() != 0 && req.getParameter("precio").length() != 0 && req.getParameter("stock").length() != 0) {
            String nombre = req.getParameter("nombre");
            double precio = Double.parseDouble(req.getParameter("precio"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            Producto producto = new Producto(0, nombre, precio, stock);
            try {
                if (agregarProducto(producto)) {
                    req.setAttribute("producto", producto);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req, resp);
    }

    private boolean agregarProducto(Producto producto) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Producto> productos = ProductoDAO.obtenerProducto(query, "nombreProducto", producto.getNombreProducto());
        if (productos.size() != 0) {
            return false;
        } else {
            ProductoDAO.agregarProducto(query, producto);
            return true;
        }
    }
}
