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
                    DSLContext query = DBGenerator.conectarBD("ProductosBD");
                    List<Categoria> categorias = CategoriaDAO.obtenerCategorias(query);
                    List<Marca> marcas = MarcaDAO.obtenerMarcas(query);

                    req.setAttribute("producto", producto);
                    req.setAttribute("categorias", categorias);
                    req.setAttribute("marcas", marcas);

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
            Producto producto = new Producto(idProducto, nombre, precio, stock, marca, categoria);
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
        ProductoDAO.modificarProducto(query, producto.getIdProducto(), "idMarca", producto.getMarca().getIdMarca());
        ProductoDAO.modificarProducto(query, producto.getIdProducto(), "idCategoria", producto.getCategoria().getIdCategoria());
        return true;
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
