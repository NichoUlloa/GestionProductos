package com.productos.gestionproductos.model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;

import java.sql.Connection;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class DBGenerator {

    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaProducto(create);
        crearTablaCategoria(create);
        crearTablaMarca(create);
        DBConnector.closeConnection();
    }

    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    private static void crearTablaProducto(DSLContext create) {
        create.createTableIfNotExists("Producto")
                .column("idProducto", INTEGER.identity(true))
                .column("nombreProducto", VARCHAR(100))
                .column("precioProducto", DOUBLE)
                .column("stockProducto", INTEGER)
                .column("idMarca", INTEGER)  // Agregar columna para idMarca
                .column("idCategoria", INTEGER)  // Agregar columna para idCategoria
                .constraint(primaryKey("idProducto"))
                .constraint(foreignKey("idMarca").references("Marca", "idMarca"))
                .constraint(foreignKey("idCategoria").references("Categoria", "idCategoria"))
                .execute();
    }

    private static void crearTablaCategoria(DSLContext create) {
        create.createTableIfNotExists("Categoria")
                .column("idCategoria", INTEGER.identity(true))
                .column("nombreCategoria", VARCHAR(100))
                .constraint(primaryKey("idCategoria")).execute();
    }

    private static void crearTablaMarca(DSLContext create) {
        create.createTableIfNotExists("Marca")
                .column("idMarca", INTEGER.identity(true))
                .column("nombreMarca", VARCHAR(100))
                .constraint(primaryKey("idMarca")).execute();
    }

    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla).alterConstraint(foreignKey(claveForanea).references(nombreTablaRelacion)).enforced();
    }

    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna) {
        create.alterTableIfExists(nombreTabla).addColumn(columna, tipoColumna);
    }
}
