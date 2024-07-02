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

@WebServlet(name = "agregarCategoriaServlet", value = "/agregarCategoria")
public class AgregarCategoriaServlet extends HttpServlet {
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
        RequestDispatcher respuesta = req.getRequestDispatcher("/agregarCategoria.jsp");
        respuesta.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if (req.getParameter("nombre").length() != 0) {
            String nombre = req.getParameter("nombre");
            Categoria categoria = new Categoria(nombre);
            try {
                if (agregarCategoria(categoria)) {
                    req.setAttribute("categoria", categoria);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req, resp);
    }

    private boolean agregarCategoria(Categoria categoria) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Categoria> categorias = CategoriaDAO.obtenerCategoria(query, "nombreCategoria", categoria.getNombreCategoria());
        if (categorias.size() != 0) {
            return false;
        } else {
            CategoriaDAO.agregarCategoria(query, categoria);
            return true;
        }
    }
}
