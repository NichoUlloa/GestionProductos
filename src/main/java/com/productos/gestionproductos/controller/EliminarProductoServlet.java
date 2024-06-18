package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.jooq.DSLContext;

@WebServlet(name = "eliminarProductoServlet", value = "/eliminarProducto")
public class EliminarProductoServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/ingresarIdEliminar.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idProductoStr = req.getParameter("idProducto");
        if (idProductoStr == null || idProductoStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/productoNoEncontrado.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idProducto = Integer.parseInt(idProductoStr);

        try {
            if (productoExiste(idProducto)) {
                eliminarProducto(idProducto);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/eliminacionExitosa.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/productoNoEncontrado.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean productoExiste(int idProducto) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        return !ProductoDAO.obtenerProducto(query, "idProducto", idProducto).isEmpty();
    }

    private void eliminarProducto(int idProducto) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        ProductoDAO.eliminarProducto(query, idProducto);
    }
}
