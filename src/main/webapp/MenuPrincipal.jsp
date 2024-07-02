<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menu Principal</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="container">
    <header>
        <h1>Inventory Plus Manager</h1>
        <p class="descripcion">Gestión eficiente de tu inventario</p>
    </header>
    <nav>
        <div class="menu-container">
            <div class="menu-section">
                <h3>Registrar</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/agregarProducto.jsp">Producto</a></li>
                    <li><a href="${pageContext.request.contextPath}/agregarCategoria.jsp">Categoría</a></li>
                    <li><a href="${pageContext.request.contextPath}/agregarMarca.jsp">Marca</a></li>
                </ul>
            </div>
            <div class="menu-section">
                <h3>Modificar</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/modificarProducto.jsp">Producto</a></li>
                    <li><a href="${pageContext.request.contextPath}/modificarCategoria.jsp">Categoría</a></li>
                    <li><a href="${pageContext.request.contextPath}/modificarMarca.jsp">Marca</a></li>
                </ul>
            </div>
            <div class="menu-section">
                <h3>Eliminar</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/eliminarProducto.jsp">Producto</a></li>
                    <li><a href="${pageContext.request.contextPath}/eliminarCategoria.jsp">Categoría</a></li>
                    <li><a href="${pageContext.request.contextPath}/eliminarMarca.jsp">Marca</a></li>
                </ul>
            </div>
            <div class="menu-section">
                <h3>Mostrar</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/mostrarProductos.jsp">Productos</a></li>
                    <li><a href="${pageContext.request.contextPath}/mostrarCategorias.jsp">Categorías</a></li>
                    <li><a href="${pageContext.request.contextPath}/mostrarMarcas.jsp">Marcas</a></li>
                </ul>
            </div>
        </div>
        <div class="buscar">
            <form action="${pageContext.request.contextPath}/buscarProducto" method="get">
                <input type="text" name="query" placeholder="Buscar productos...">
                <button type="submit">Buscar</button>
            </form>
        </div>
    </nav>
    <main>
        <section>
            <h2>Bienvenido a Inventory Plus Manager</h2>
            <p style="text-align: center;">Seleccione una opción del menú para comenzar a gestionar su inventario.</p>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Inventory Plus Manager. Todos los derechos reservados.</p>
    </footer>
</div>
</body>
</html>
