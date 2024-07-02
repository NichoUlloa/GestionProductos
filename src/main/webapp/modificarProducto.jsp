<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Producto</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Modificar Producto</h1>
    </header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/MenuPrincipal.jsp">Inicio</a></li>
        </ul>
    </nav>
    <main>
        <section>
            <h2>Formulario de Modificar Producto</h2>
            <form action="modificarProducto" method="post">
                <div class="form-group">
                    <label for="idProducto">ID Producto:</label>
                    <input id="idProducto" name="idProducto" type="number" class="campoTexto" value="${producto.idProducto}" readonly>
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input id="nombre" name="nombre" type="text" class="campoTexto" value="${producto.nombreProducto}">
                </div>
                <div class="form-group">
                    <label for="precio">Precio:</label>
                    <input id="precio" name="precio" type="text" class="campoTexto" value="${producto.precioProducto}">
                </div>
                <div class="form-group">
                    <label for="stock">Stock:</label>
                    <input id="stock" name="stock" type="number" class="campoTexto" value="${producto.stockProducto}">
                </div>
                <div class="form-group">
                    <label for="categoria">Categoría:</label>
                    <select id="categoria" name="idCategoria" class="campoTexto" required>
                        <option value="">Seleccione una categoría</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.idCategoria}" ${categoria.idCategoria == producto.categoria.idCategoria ? 'selected' : ''}>${categoria.nombreCategoria}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="marca">Marca:</label>
                    <select id="marca" name="idMarca" class="campoTexto" required>
                        <option value="">Seleccione una marca</option>
                        <c:forEach var="marca" items="${marcas}">
                            <option value="${marca.idMarca}" ${marca.idMarca == producto.marca.idMarca ? 'selected' : ''}>${marca.nombreMarca}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="Modificar Producto" class="boton">
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Gestión de Productos. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
