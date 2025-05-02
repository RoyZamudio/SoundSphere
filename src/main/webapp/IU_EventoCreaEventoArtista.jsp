<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Crear Evento</title></head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/estilos_globales.css">
<link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
<body>

<!-- Barra superior -->
<div class="top-nav-bar">
    <div class="title-area">
        <div class="logo-container">
            <img src="img/logo.png" alt="SoundSphere Logo" class="app-logo">
            <h1 class="titulo-app">SoundSphere</h1>
        </div>
    </div>

    <div class="search-container">
        <div class="search-input-wrapper">
            <input type="text" placeholder="Buscar..." class="search-input">
            <i class="fas fa-search search-icon"></i>
        </div>
    </div>

    <div class="nav-buttons">
        <button class="nav-button active" onclick="location.href='reproductor'">
            <i class="fas fa-music"></i>
            <span>Reproductor</span>
        </button>
        <button class="nav-button" onclick="location.href='Streaming'">
            <i class="fas fa-broadcast-tower" ></i>
            <span>Streaming</span>
        </button>
        <button class="nav-button" onclick="location.href='controlador-publicaciones'">
            <i class="fas fa-users"></i>
            <span>Red Social</span>
        </button>
        <button class="nav-button">
            <i class="fas fa-user"></i>
            <span>Mi Cuenta</span>
        </button>
    </div>
</div>
<div>
<h2>Crear Evento</h2>

<form action="<%=request.getContextPath()%>/Controlador_Eventos" method="post" enctype="multipart/form-data">
    <input type="hidden" name="accion" value="crear">

    <label>Nombre del evento:</label><br>
    <input type="text" name="nombre" required><br><br>

    <label>Tipo de evento (concierto / reunión/ exposición, etc):</label><br>
    <input type="text" name="tipo" required><br><br>

    <label>Descripción del evento:</label><br>
    <textarea name="descripcion" required></textarea><br><br>

    <label>Fecha y hora (aaaa-mm-dd hh:mm):</label><br>
    <input type="text" name="fechaHora" required><br><br>

    <label>Ubicación del evento:</label><br>
    <input type="text" name="ubicacion" required><br><br>

    <label>Capacidad máxima del evento:</label><br>
    <input type="number" name="capacidad" required><br><br>

    <label>Sube una imagen alusiva al evento (opcional):</label><br>
    <input type="file" name="archivoMultimedia"><br><br>

    <input type="submit" value="Crear Evento">
</form>

<% String mensaje = (String) request.getAttribute("mensaje");
    if (mensaje != null) { %>
<p style="color:green;"><b><%= mensaje %></b></p>
<% } %>
</div>
</body>
</html>