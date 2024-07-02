package com.productos.gestionproductos.controller;

import com.productos.gestionproductos.model.Categoria;
import com.productos.gestionproductos.model.Marca;
import com.productos.gestionproductos.model.Producto;
import com.productos.gestionproductos.model.data.DBGenerator;
import com.productos.gestionproductos.model.data.dao.CategoriaDAO;
import com.productos.gestionproductos.model.data.dao.MarcaDAO;
import com.productos.gestionproductos.model.data.dao.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

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
        DSLContext query = null;
        try {
            query = DBGenerator.conectarBD("ProductosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Categoria> categorias = CategoriaDAO.obtenerCategorias(query);
        List<Marca> marcas = MarcaDAO.obtenerMarcas(query);

        // Imprimir listas para verificar que no están vacías
        System.out.println("Categorias: " + categorias);
        System.out.println("Marcas: " + marcas);

        req.setAttribute("categorias", categorias);
        req.setAttribute("marcas", marcas);

        RequestDispatcher respuesta = req.getRequestDispatcher("/WEB-INF/agregarProducto.jsp");
        respuesta.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if (req.getParameter("nombre").length() != 0 && req.getParameter("precio").length() != 0 && req.getParameter("stock").length() != 0
                && req.getParameter("idMarca").length() != 0 && req.getParameter("idCategoria").length() != 0) {
            String nombre = req.getParameter("nombre");
            double precio = Double.parseDouble(req.getParameter("precio"));
            int stock = Integer.parseInt(req.getParameter("stock"));
            int idMarca = Integer.parseInt(req.getParameter("idMarca"));
            int idCategoria = Integer.parseInt(req.getParameter("idCategoria"));

            DSLContext query = null;
            try {
                query = DBGenerator.conectarBD("ProductosBD");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Marca marca = obtenerMarca(query, idMarca);
            Categoria categoria = obtenerCategoria(query, idCategoria);

            if (marca != null && categoria != null) {
                Producto producto = new Producto(0, nombre, precio, stock, marca, categoria);
                try {
                    if (agregarProducto(producto)) {
                        req.setAttribute("producto", producto);
                        respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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

    private Marca obtenerMarca(DSLContext query, int idMarca) {
        List<Marca> marcas = MarcaDAO.obtenerMarca(query, "idMarca", idMarca);
        if (marcas.size() == 1) {
            return marcas.get(0);
        } else {
            return null;
        }
    }

    private Categoria obtenerCategoria(DSLContext query, int idCategoria) {
        List<Categoria> categorias = CategoriaDAO.obtenerCategoria(query, "idCategoria", idCategoria);
        if (categorias.size() == 1) {
            return categorias.get(0);
        } else {
            return null;
        }
    }


}
