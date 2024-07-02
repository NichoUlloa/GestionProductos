package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.CategoriaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.jooq.DSLContext;

@WebServlet(name = "eliminarCategoriaServlet", value = "/eliminarCategoria")
public class EliminarCategoriaServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/eliminarCategoria.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCategoriaStr = req.getParameter("idCategoria");
        if (idCategoriaStr == null || idCategoriaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/categoriaNoEncontrada.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idCategoria = Integer.parseInt(idCategoriaStr);

        try {
            if (categoriaExiste(idCategoria)) {
                eliminarCategoria(idCategoria);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/eliminacionExitosa.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/categoriaNoEncontrada.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean categoriaExiste(int idCategoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        return !CategoriaDAO.obtenerCategoria(query, "idCategoria", idCategoria).isEmpty();
    }

    private void eliminarCategoria(int idCategoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        CategoriaDAO.eliminarCategoria(query, idCategoria);
    }
}
