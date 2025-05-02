<%--
  Created by IntelliJ IDEA.
  User: gamaliel
  Date: 01/05/2025
  Time: 01:56 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="estilos/iniciodesesion.css">
    <title>Registro en SoundSphere</title>

</head>
<body>
<div class="container">
    <h1>Registro en SoundSphere</h1>
    <%
        String error = request.getParameter("error");
        if ("true".equals(error)) {
    %>
    <div style="color: red; font-weight: bold;">
        No se pudo completar el registro. Por favor, inténtalo de nuevo.
    </div>
    <%
        }
    %>

    <form  action="${pageContext.request.contextPath}/controlador_registro" method="post">
        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre" required />

        <label for="email">Correo electrónico</label>
        <input type="email" id="email" name="email" required />

        <label for="password">Contraseña</label>
        <input type="password" id="password" name="password" required />

        <label for="password2">Repite tu contraseña</label>
        <input type="password" id="password2" name="password2" required />

        <label for="artista">
            <input type="checkbox" id="artista" name="artista" />
            ¿Eres Artista?
        </label>

        <button type="submit">Registro</button>
    </form>

    <div class="message">
        <a href="IU_InicioSesion.jsp">
            <button>¿Ya tienes cuenta?</button>
        </a>
    </div>
</div>

<script>
    // Validar contraseñas coinciden
    document.querySelector("form").addEventListener("submit", function (event) {
        var password = document.getElementById("password").value;
        var password2 = document.getElementById("password2").value;

        if (password !== password2) {
            alert("Las contraseñas no coinciden");
            event.preventDefault(); // Evitar el envío del formulario
        }
    });
</script>
</body>
</html>