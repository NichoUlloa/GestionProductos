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
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Mostrar Productos</title>
</head>
<body>
<h1 class="encabezado">Productos ingresados</h1>
<div class="centrado">
    <table>
        <tr>
            <th>ID Producto</th>
            <th>Nombre Producto</th>
            <th>Precio</th>
            <th>Stock</th>
        </tr>
        <c:forEach items="${productos}" var="producto">
            <tr>
                <td><c:out value="${producto.getIdProducto()}"></c:out></td>
                <td><c:out value="${producto.getNombreProducto()}"></c:out></td>
                <td><c:out value="${producto.getPrecioProducto()}"></c:out></td>
                <td><c:out value="${producto.getStockProducto()}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
    <a class="boton" href="/GestionProductos">Volver</a>
</div>
</body>
</html>
