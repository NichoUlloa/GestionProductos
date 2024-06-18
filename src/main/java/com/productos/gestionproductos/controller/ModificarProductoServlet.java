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

@WebServlet(name = "modificarProductoServlet", value = "/modificarProducto")
public class ModificarProductoServlet extends HttpServlet {
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
        String idProductoStr = req.getParameter("idProducto");

        if (idProductoStr == null) {
            // Mostrar la página para ingresar el ID del producto
            RequestDispatcher dispatcher = req.getRequestDispatcher("/ingresarIdProducto.jsp");
            dispatcher.forward(req, resp);
        } else if (idProductoStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/productoNoEncontrado.jsp");
            dispatcher.forward(req, resp);
        } else {
            int idProducto = Integer.parseInt(idProductoStr);
            try {
                Producto producto = obtenerProductoPorId(idProducto);
                if (producto != null) {
                    req.setAttribute("producto", producto);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/modificarProducto.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/productoNoEncontrado.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProductoStr = req.getParameter("idProducto");
        if (idProductoStr == null || idProductoStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/modificacionErronea.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idProducto = Integer.parseInt(idProductoStr);
        String nombre = req.getParameter("nombre");
        double precio = Double.parseDouble(req.getParameter("precio"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        Producto producto = new Producto(idProducto, nombre, precio, stock);
        try {
            if (modificarProducto(producto)) {
                req.setAttribute("producto", producto);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/modificacionExitosa.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/modificacionErronea.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Producto obtenerProductoPorId(int idProducto) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Producto> productos = ProductoDAO.obtenerProducto(query, "idProducto", idProducto);
        return productos.isEmpty() ? null : productos.get(0);
    }

    private boolean modificarProducto(Producto producto) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        ProductoDAO.modificarProducto(query, producto.getIdProducto(), "nombreProducto", producto.getNombreProducto());
        ProductoDAO.modificarProducto(query, producto.getIdProducto(), "precioProducto", producto.getPrecioProducto());
        ProductoDAO.modificarProducto(query, producto.getIdProducto(), "stockProducto", producto.getStockProducto());
        return true;
    }
}
