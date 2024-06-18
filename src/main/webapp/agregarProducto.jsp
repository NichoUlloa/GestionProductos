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
    <title>Agregar Producto</title>
</head>
<body>
<h1 class="encabezado">Agregar Producto</h1>
<form action="agregarProducto" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <label> Precio:</label>
        <input name="precio" type="text" class="campoTexto">
        <label> Stock:</label>
        <input name="stock" type="number" class="campoTexto"><br><br>
        <input type="submit" value="Agregar Producto" class="boton">
    </div>
</form>
</body>
</html>
