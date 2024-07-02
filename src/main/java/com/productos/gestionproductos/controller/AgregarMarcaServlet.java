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

@WebServlet(name = "agregarMarcaServlet", value = "/agregarMarca")
public class AgregarMarcaServlet extends HttpServlet {
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
        RequestDispatcher respuesta = req.getRequestDispatcher("/agregarMarca.jsp");
        respuesta.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if (req.getParameter("nombre").length() != 0) {
            String nombre = req.getParameter("nombre");
            Marca marca = new Marca(nombre);
            try {
                if (agregarMarca(marca)) {
                    req.setAttribute("marca", marca);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req, resp);
    }

    private boolean agregarMarca(Marca marca) throws ClassNotFoundException {
        DSLContext query = DBGenerator.conectarBD("ProductosBD");
        List<Marca> marcas = MarcaDAO.obtenerMarca(query, "nombreMarca", marca.getNombreMarca());
        if (marcas.size() != 0) {
            return false;
        } else {
            MarcaDAO.agregarMarca(query, marca);
            return true;
        }
    }
}
