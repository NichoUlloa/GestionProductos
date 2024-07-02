<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>Página de Inicio de Sesión | GestionProductos</title>
</head>

<body>

<div class="container" id="container">
    <div class="form-container sign-up">
        <form action="RegistrarParticipanteServlet" method="post">
            <h1>Crear Cuenta</h1>
            <span>o utiliza tu correo electrónico para registrarte</span>
            <input type="text" name="name" placeholder="Nombre">
            <input type="email" name="email" placeholder="Correo Electrónico">
            <input type="password" name="password" placeholder="Contraseña">
            <button type="submit">Registrarse</button>
        </form>
    </div>
    <div class="form-container sign-in">
        <form action="LoginParticipanteServlet" method="post">
            <h1>Iniciar Sesión</h1>
            <span>o utiliza tu correo electrónico y contraseña</span>
            <input type="email" name="email" placeholder="Correo Electrónico">
            <input type="password" name="password" placeholder="Contraseña">
            <a href="#">¿Olvidaste tu contraseña?</a>
            <button
                    type="submit">Iniciar Sesión
                <ul>
                    <li><a href="${pageContext.request.contextPath}/MenuPrincipal.jsp">IS</a></li>
                </ul>
            </button>
        </form>
    </div>
    <div class="toggle-container">
        <div class="toggle">
            <div class="toggle-panel toggle-left">
                <img src="img/logo.png" alt="Inventory Plus Manager" class="logo">
                <h1>¡Bienvenido de nuevo!</h1>
                <p>Introduce tus datos personales para acceder a Inventory Plus Manager.</p>
                <button class="hidden" id="login">Iniciar Sesión</button>
            </div>
            <div class="toggle-panel toggle-right">
                <img src="img/logo.png" alt="Inventory Plus Manager" class="logo">
                <h1>¡Hola, Amigo!</h1>
                <p>Regístrate para obtener acceso completo a Inventory Plus Manager.</p>
                <button class="hidden" id="register">Registrarse</button>
            </div>
        </div>
    </div>
</div>

<script src="js/script.js"></script>
</body>

</html>
