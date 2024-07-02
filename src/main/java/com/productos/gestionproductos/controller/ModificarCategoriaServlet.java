package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.Categoria;
import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.CategoriaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import org.jooq.DSLContext;

@WebServlet(name = "modificarCategoriaServlet", value = "/modificarCategoria")
public class ModificarCategoriaServlet extends HttpServlet {
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
        String idCategoriaStr = req.getParameter("idCategoria");

        if (idCategoriaStr == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/ingresarIdCategoria.jsp");
            dispatcher.forward(req, resp);
        } else if (idCategoriaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/categoriaNoEncontrada.jsp");
            dispatcher.forward(req, resp);
        } else {
            int idCategoria = Integer.parseInt(idCategoriaStr);
            try {
                Categoria categoria = obtenerCategoriaPorId(idCategoria);
                if (categoria != null) {
                    req.setAttribute("categoria", categoria);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/modificarCategoria.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/categoriaNoEncontrada.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCategoriaStr = req.getParameter("idCategoria");
        if (idCategoriaStr == null || idCategoriaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/modificacionErronea.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idCategoria = Integer.parseInt(idCategoriaStr);
        String nombre = req.getParameter("nombre");

        try {
            if (modificarCategoria(idCategoria, nombre)) {
                Categoria categoria = obtenerCategoriaPorId(idCategoria);
                req.setAttribute("categoria", categoria);
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

    private Categoria obtenerCategoriaPorId(int idCategoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Categoria> categorias = CategoriaDAO.obtenerCategoria(query, "idCategoria", idCategoria);
        return categorias.isEmpty() ? null : categorias.get(0);
    }

    private boolean modificarCategoria(int idCategoria, String nombre) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        CategoriaDAO.modificarCategoria(query, idCategoria, "nombreCategoria", nombre);
        return true;
    }
}
