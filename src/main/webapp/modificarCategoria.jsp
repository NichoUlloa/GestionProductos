<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Categoría</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Modificar Categoría</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Formulario de Modificar Categoría</h2>
            <form action="modificarCategoria" method="post">
                <div class="form-group">
                    <label for="idCategoria">ID Categoría:</label>
                    <input id="idCategoria" name="idCategoria" type="number" class="campoTexto" value="${categoria.idCategoria}" readonly>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input id="nombre" name="nombre" type="text" class="campoTexto" value="${categoria.nombreCategoria}">
                </div>
                <input type="submit" value="Modificar Categoría" class="boton">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
