package com.productos.gestionproductos.model.data.dao;

import com.productos.gestionproductos.model.Categoria;
import com.productos.gestionproductos.model.Marca;
import com.productos.gestionproductos.model.Producto;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class ProductoDAO {
    public static void agregarProducto(DSLContext query, Producto producto) {
        Table<?> tablaProducto = table(name("Producto"));
        Field<String> nombre = field(name("nombreProducto"), VARCHAR(100));
        Field<Double> precio = field(name("precioProducto"), DOUBLE);
        Field<Integer> stock = field(name("stockProducto"), INTEGER);
        Field<Integer> idMarca = field(name("idMarca"), INTEGER);
        Field<Integer> idCategoria = field(name("idCategoria"), INTEGER);

        query.insertInto(tablaProducto, nombre, precio, stock, idMarca, idCategoria)
                .values(producto.getNombreProducto(), producto.getPrecioProducto(), producto.getStockProducto(),
                        producto.getMarca().getIdMarca(), producto.getCategoria().getIdCategoria())
                .execute();
    }

    public static void modificarProducto(DSLContext query, int idProducto, String columnaTabla, Object dato) {
        query.update(DSL.table("Producto")).set(DSL.field(columnaTabla), dato)
                .where(DSL.field("idProducto").eq(idProducto)).execute();
    }

    public static List<Producto> obtenerProducto(DSLContext query, String columnaTabla, Object dato) {
        Result<?> resultados = query.select().from(DSL.table("Producto")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaProductos(resultados);
    }

    public static List<Producto> obtenerProductos(DSLContext query) {
        Result<?> resultados = query.select().from(DSL.table("Producto")).fetch();
        return obtenerListaProductos(resultados);
    }

    public static void eliminarProducto(DSLContext query, int idProducto) {
        query.delete(DSL.table("Producto")).where(DSL.field("idProducto").eq(idProducto)).execute();
    }

    private static List<Producto> obtenerListaProductos(Result<?> resultados) {
        List<Producto> productos = new ArrayList<>();
        for (Record resultado : resultados) {
            int idProducto = resultado.get("idProducto", Integer.class);
            String nombreProducto = resultado.get("nombreProducto", String.class);
            double precioProducto = resultado.get("precioProducto", Double.class);
            int stockProducto = resultado.get("stockProducto", Integer.class);
            int idMarca = resultado.get("idMarca", Integer.class);
            int idCategoria = resultado.get("idCategoria", Integer.class);

            Marca marca = obtenerMarca(idMarca);
            Categoria categoria = obtenerCategoria(idCategoria);

            productos.add(new Producto(idProducto, nombreProducto, precioProducto, stockProducto, marca, categoria));
        }
        return productos;
    }

    private static Marca obtenerMarca(int idMarca) {
        // Implementar lógica para obtener Marca por idMarca desde la base de datos
        return new Marca(idMarca, "NombreMarca"); // Ejemplo, reemplazar con lógica real
    }

    private static Categoria obtenerCategoria(int idCategoria) {
        // Implementar lógica para obtener Categoria por idCategoria desde la base de datos
        return new Categoria(idCategoria, "NombreCategoria"); // Ejemplo, reemplazar con lógica real
    }
}
