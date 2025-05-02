<%--
  Created by IntelliJ IDEA.
  User: gamaliel
  Date: 01/05/2025
  Time: 01:09 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="estilos/iniciodesesion.css">
  <title>SoundSphere</title>

  <script>
    document.addEventListener("DOMContentLoaded", () => {
      const form = document.querySelector("form");
      form.addEventListener("submit", (event) => {
        const email = form.email.value.trim();
        const password = form.password.value.trim();

        if (!email || !password) {
          event.preventDefault();
          alert("Por favor, completa todos los campos antes de continuar.");
        }
      });
    });
  </script>
</head>
<body>
<div class="container">
  <h1>Inicio de Sesión SoundSphere</h1>
  <%
    String error = request.getParameter("error");
    if ("true".equals(error)) {
  %>
  <div style="color: red; font-weight: bold;">
    Inicio de sesión fallido. Por favor, inténtalo de nuevo.
  </div>
  <%
    }
  %>
  <%
    String registroExito = request.getParameter("registroExito");
    if ("true".equals(registroExito)) {
  %>
  <div style="color: green; font-weight: bold;">
    ¡Registro exitoso! Ahora puedes iniciar sesión.
  </div>
  <%
    }
  %>


  <form action="${pageContext.request.contextPath}/controlador_inicio_sesion" method="post">

    <label for="email">Correo electrónico</label>
    <input type="email" id="email" name="email" placeholder="Ingresa tu correo" required />

    <label for="password">Contraseña</label>
    <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required />

    <button type="submit">Iniciar Sesión</button>
  </form>

  <a href="IU_Registro.jsp">
    <button>¿Aún no tienes cuenta?</button>
  </a>

</div>
</body>
</html>