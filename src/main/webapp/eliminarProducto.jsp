<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 18-06-2024
  Time: 5:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Producto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Eliminar Producto</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Formulario de Eliminar Producto</h2>
            <form action="eliminarProducto" method="post">
                <div class="form-group">
                    <label for="idProducto">ID del Producto:</label>
                    <input id="idProducto" name="idProducto" type="number" class="campoTexto" required>
                </div>
                <input type="submit" value="Eliminar Producto" class="boton">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
