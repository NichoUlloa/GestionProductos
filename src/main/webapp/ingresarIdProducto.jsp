<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Ingresar ID de Producto</title>
</head>
<body>
<h1 class="encabezado">Ingresar ID de Producto a Modificar</h1>
<form action="modificarProducto" method="get">
    <div class="centrado">
        <label>ID Producto:</label>
        <input type="number" name="idProducto" required class="campoTexto"><br><br>
        <input type="submit" value="Modificar producto" class="boton">
    </div>
</form>
</body>
</html>
