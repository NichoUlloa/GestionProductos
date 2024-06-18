<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Registro Exitoso</title>
</head>
<body>
<h1 class="encabezado">Registro Exitoso</h1>
<div class="centrado">
    <p class="subtitulo">El registro del producto fue exitoso.</p><br>
    <label class="campoTexto">Producto: <c:out value="${producto.getNombreProducto()}"></c:out></label><br>
    <label class="campoTexto">Precio: <c:out value="${producto.getPrecioProducto()}"></c:out></label><br>
    <label class="campoTexto">Stock: <c:out value="${producto.getStockProducto()}"></c:out></label><br>
    <a class="boton" href="/GestionProductos">Volver</a>
</div>
</body>
</html>

