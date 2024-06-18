<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Registro Erróneo</title>
</head>
<body>
<h1 class="encabezado">Registro Erróneo</h1>
<h2 class="subtitulo">Datos incorrectos, por favor verifique e intente nuevamente.</h2>
<form action="agregarProducto" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <label> Precio:</label>
        <input name="precio" type="text" class="campoTexto">
        <label> Stock:</label>
        <input name="stock" type="number" class="campoTexto"><br><br>
        <input type="submit" value="Intentar nuevamente" class="boton">
    </div>
</form>
</body>
</html>
