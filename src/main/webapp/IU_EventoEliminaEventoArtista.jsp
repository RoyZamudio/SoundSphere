<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="PAC_EVENTOS.MODELO.Evento" %>
<!DOCTYPE html>
<html>
<head><title>Eliminar Evento</title></head>
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
<h2>Eliminar Evento</h2>

<form action="<%= request.getContextPath() %>/Controlador_Eventos" method="get">
    <input type="hidden" name="accion" value="buscarEliminar">
    <label>Nombre del evento:</label><br>
    <input type="text" name="nombreEvento" required><br><br>
    <input type="submit" value="Buscar Evento para Eliminar">
</form>

<% String mensaje = (String) request.getAttribute("mensaje"); if (mensaje != null) { %>
<p style="color:green;"><b><%= mensaje %></b></p>
<% } %>

<% Evento evento = (Evento) request.getAttribute("evento"); if (evento != null) { %>
<h3>¿Deseas eliminar el siguiente evento?</h3>
<form action="<%= request.getContextPath() %>/Controlador_Eventos" method="post">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>">

    <p><strong>Nombre:</strong> <%= evento.getNombre() %></p>
    <p><strong>Tipo:</strong> <%= evento.getTipoEvento() %></p>
    <p><strong>Fecha:</strong> <%= evento.getFechaEvento() %> <%= evento.getHoraEvento() %></p>
    <p><strong>Ubicación:</strong> <%= evento.getUbicacion() %></p>
    <p><strong>Descripción:</strong> <%= evento.getDescripcion() %></p>
    <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>">


    <input type="submit" value="Eliminar Evento" onclick="return confirm('¿Estás segura que deseas eliminar este evento?');">
</form>
<% } %>
</div>
</body>
</html>
