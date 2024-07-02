<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Categoría</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Eliminar Categoría</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Formulario de Eliminar Categoría</h2>
            <form action="eliminarCategoria" method="post">
                <div class="form-group">
                    <label for="idCategoria">ID de la Categoría:</label>
                    <input id="idCategoria" name="idCategoria" type="number" class="campoTexto" required>
                </div>
                <input type="submit" value="Eliminar Categoría" class="boton">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
