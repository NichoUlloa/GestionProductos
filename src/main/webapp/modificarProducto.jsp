<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Modificar Producto</title>
</head>
<body>
<h1 class="encabezado">Modificar Producto</h1>
<form action="modificarProducto" method="post">
    <div class="centrado">
        <label> ID Producto:</label>
        <input name="idProducto" type="number" class="campoTexto" value="${producto.idProducto}" readonly>
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto" value="${producto.nombreProducto}">
        <label> Precio:</label>
        <input name="precio" type="text" class="campoTexto" value="${producto.precioProducto}">
        <label> Stock:</label>
        <input name="stock" type="number" class="campoTexto" value="${producto.stockProducto}"><br><br>
        <input type="submit" value="Modificar Producto" class="boton">
    </div>
</form>
</body>
</html>

