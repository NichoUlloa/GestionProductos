package com.productos.gestionproductos.model.data.dao;

import com.productos.gestionproductos.model.Categoria;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class CategoriaDAO {
    public static void agregarCategoria(DSLContext query, Categoria categoria) {
        Table<?> tablaCategoria = table(name("Categoria"));
        Field<String> nombre = field(name("nombreCategoria"), VARCHAR(100));

        query.insertInto(tablaCategoria, nombre)
                .values(categoria.getNombreCategoria())
                .execute();
    }

    public static void modificarCategoria(DSLContext query, int idCategoria, String columnaTabla, Object dato) {
        query.update(DSL.table("Categoria")).set(DSL.field(columnaTabla), dato)
                .where(DSL.field("idCategoria").eq(idCategoria)).execute();
    }

    public static List<Categoria> obtenerCategoria(DSLContext query, String columnaTabla, Object dato) {
        Result<?> resultados = query.select().from(DSL.table("Categoria")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaCategorias(resultados);
    }

    public static List<Categoria> obtenerCategorias(DSLContext query) {
        Result<?> resultados = query.select().from(DSL.table("Categoria")).fetch();
        return obtenerListaCategorias(resultados);
    }

    public static void eliminarCategoria(DSLContext query, int idCategoria) {
        query.delete(DSL.table("Categoria")).where(DSL.field("idCategoria").eq(idCategoria)).execute();
    }

    private static List<Categoria> obtenerListaCategorias(Result<?> resultados) {
        List<Categoria> categorias = new ArrayList<>();
        for (Record resultado : resultados) {
            int idCategoria = resultado.get("idCategoria", Integer.class);
            String nombreCategoria = resultado.get("nombreCategoria", String.class);
            categorias.add(new Categoria(idCategoria, nombreCategoria));
        }
        return categorias;
    }
}
