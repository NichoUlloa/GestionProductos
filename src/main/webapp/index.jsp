<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
    <title>Gestión de Productos</title>
</head>
<body>
<h1 class="encabezado">Menú principal - Gestión de Productos</h1>
<br>
<form action="agregarProducto" method="get">
    <div class="centrado">
        <input type="submit" value="Agregar producto" class="boton">
    </div>
</form>

<form action="modificarProducto" method="get">
    <div class="centrado">
        <input type="submit" value="Modificar producto" class="boton">
    </div>
</form>

<form action="eliminarProducto" method="get">
    <div class="centrado">
        <input type="submit" value="Eliminar producto" class="boton">
    </div>
</form>

<form action="mostrarProductos" method="get">
    <div class="centrado">
        <input type="submit" value="Mostrar productos" class="boton">
    </div>
</form>
</body>
</html>
