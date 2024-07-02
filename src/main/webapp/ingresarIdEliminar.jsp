<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ingresar ID de Producto a Eliminar</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<header>
    <h1>Ingresar ID de Producto a Eliminar</h1>
</header>
<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
    </ul>
</nav>
<main>
    <section>
        <h2>Formulario de Ingresar ID</h2>
        <form action="eliminarProducto" method="post">
            <div class="centrado">
                <label>ID del Producto:</label>
                <input name="idProducto" type="number" class="campoTexto"><br><br>
                <input type="submit" value="Eliminar Producto" class="boton">
            </div>
        </form>
    </section>
</main>
<footer>
    <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
</footer>
</body>
</html>

