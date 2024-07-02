<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mostrar Productos</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Productos Ingresados</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Listado de Productos</h2>
            <div class="centrado">
                <table class="productos-table">
                    <tr>
                        <th>ID Producto</th>
                        <th>Nombre Producto</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Acciones</th>
                    </tr>
                    <c:forEach items="${productos}" var="producto">
                        <tr>
                            <td><c:out value="${producto.getIdProducto()}"></c:out></td>
                            <td><c:out value="${producto.getNombreProducto()}"></c:out></td>
                            <td><c:out value="${producto.getPrecioProducto()}"></c:out></td>
                            <td><c:out value="${producto.getStockProducto()}"></c:out></td>
                            <td>
                                <a href="modificarProducto?id=${producto.idProducto}" class="boton boton-actualizar">Actualizar</a>
                                <a href="eliminarProducto?id=${producto.idProducto}" class="boton boton-eliminar">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="boton-centro">
                    <a class="boton" href="${pageContext.request.contextPath}/index.jsp">Volver</a>
                </div>
            </div>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
