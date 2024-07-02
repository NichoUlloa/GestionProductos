package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.MarcaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import org.jooq.DSLContext;

@WebServlet(name = "eliminarMarcaServlet", value = "/eliminarMarca")
public class EliminarMarcaServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/eliminarMarca.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idMarcaStr = req.getParameter("idMarca");
        if (idMarcaStr == null || idMarcaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/marcaNoEncontrada.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idMarca = Integer.parseInt(idMarcaStr);

        try {
            if (marcaExiste(idMarca)) {
                eliminarMarca(idMarca);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/eliminacionExitosa.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/marcaNoEncontrada.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean marcaExiste(int idMarca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        return !MarcaDAO.obtenerMarca(query, "idMarca", idMarca).isEmpty();
    }

    private void eliminarMarca(int idMarca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        MarcaDAO.eliminarMarca(query, idMarca);
    }
}
