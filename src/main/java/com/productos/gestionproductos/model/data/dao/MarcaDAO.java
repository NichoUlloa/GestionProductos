package com.productos.gestionproductos.model.data.dao;

import com.productos.gestionproductos.model.Marca;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class MarcaDAO {
    public static void agregarMarca(DSLContext query, Marca marca) {
        Table<?> tablaMarca = table(name("Marca"));
        Field<String> nombre = field(name("nombreMarca"), VARCHAR(100));

        query.insertInto(tablaMarca, nombre)
                .values(marca.getNombreMarca())
                .execute();
    }

    public static void modificarMarca(DSLContext query, int idMarca, String columnaTabla, Object dato) {
        query.update(DSL.table("Marca")).set(DSL.field(columnaTabla), dato)
                .where(DSL.field("idMarca").eq(idMarca)).execute();
    }

    public static List<Marca> obtenerMarca(DSLContext query, String columnaTabla, Object dato) {
        Result<?> resultados = query.select().from(DSL.table("Marca")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaMarcas(resultados);
    }

    public static List<Marca> obtenerMarcas(DSLContext query) {
        Result<?> resultados = query.select().from(DSL.table("Marca")).fetch();
        return obtenerListaMarcas(resultados);
    }

    public static void eliminarMarca(DSLContext query, int idMarca) {
        query.delete(DSL.table("Marca")).where(DSL.field("idMarca").eq(idMarca)).execute();
    }

    private static List<Marca> obtenerListaMarcas(Result<?> resultados) {
        List<Marca> marcas = new ArrayList<>();
        for (Record resultado : resultados) {
            int idMarca = resultado.get("idMarca", Integer.class);
            String nombreMarca = resultado.get("nombreMarca", String.class);
            marcas.add(new Marca(idMarca, nombreMarca));
        }
        return marcas;
    }
}
