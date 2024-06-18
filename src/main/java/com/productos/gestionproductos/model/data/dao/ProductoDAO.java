package com.productos.gestionproductos.model.data.dao;

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

        query.insertInto(tablaProducto, nombre, precio, stock)
                .values(producto.getNombreProducto(), producto.getPrecioProducto(), producto.getStockProducto())
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
            productos.add(new Producto(idProducto, nombreProducto, precioProducto, stockProducto));
        }
        return productos;
    }
}
