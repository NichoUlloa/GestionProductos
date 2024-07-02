<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Marca</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Modificar Marca</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Formulario de Modificar Marca</h2>
            <form action="modificarMarca" method="post">
                <div class="form-group">
                    <label for="idMarca">ID Marca:</label>
                    <input id="idMarca" name="idMarca" type="number" class="campoTexto" value="${marca.idMarca}" readonly>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input id="nombre" name="nombre" type="text" class="campoTexto" value="${marca.nombreMarca}">
                </div>
                <input type="submit" value="Modificar Marca" class="boton">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
