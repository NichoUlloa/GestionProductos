package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.Marca;
import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.MarcaDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import org.jooq.DSLContext;

@WebServlet(name = "modificarMarcaServlet", value = "/modificarMarca")
public class ModificarMarcaServlet extends HttpServlet {
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
        String idMarcaStr = req.getParameter("idMarca");

        if (idMarcaStr == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/ingresarIdMarca.jsp");
            dispatcher.forward(req, resp);
        } else if (idMarcaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/marcaNoEncontrada.jsp");
            dispatcher.forward(req, resp);
        } else {
            int idMarca = Integer.parseInt(idMarcaStr);
            try {
                Marca marca = obtenerMarcaPorId(idMarca);
                if (marca != null) {
                    req.setAttribute("marca", marca);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/modificarMarca.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/marcaNoEncontrada.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idMarcaStr = req.getParameter("idMarca");
        if (idMarcaStr == null || idMarcaStr.isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/modificacionErronea.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int idMarca = Integer.parseInt(idMarcaStr);
        String nombre = req.getParameter("nombre");

        try {
            if (modificarMarca(idMarca, nombre)) {
                Marca marca = obtenerMarcaPorId(idMarca);
                req.setAttribute("marca", marca);
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

    private Marca obtenerMarcaPorId(int idMarca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Marca> marcas = MarcaDAO.obtenerMarca(query, "idMarca", idMarca);
        return marcas.isEmpty() ? null : marcas.get(0);
    }

    private boolean modificarMarca(int idMarca, String nombre) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        MarcaDAO.modificarMarca(query, idMarca, "nombreMarca", nombre);
        return true;
    }
}
