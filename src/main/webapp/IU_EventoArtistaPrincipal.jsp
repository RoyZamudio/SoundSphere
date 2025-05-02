<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="PAC_EVENTOS.MODELO.Evento" %>
<!DOCTYPE html>
<html>
<head>
  <title>Panel Artista</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/estilos_globales.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/eventos_artista.css">
  <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
</head>
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
<div class="panel-eventos">
  <h1>🎤 Crea tus eventos de Artista</h1>

  <div class="acciones">
    <ul>
      <li><a href="<%= request.getContextPath() %>/Controlador_Eventos?accion=verFormularioCrear">+ Crear evento</a></li>
      <li><a href="<%= request.getContextPath() %>/Controlador_Eventos?accion=verFormularioEditar">✏️ Editar evento</a></li>
      <li><a href="<%= request.getContextPath() %>/Controlador_Eventos?accion=verFormularioEliminar">🗑️ Eliminar evento</a></li>
    </ul>
  </div>

  <h2>Eventos Disponibles</h2>
  <div>
    <%
      List<Evento> eventos = (List<Evento>) request.getAttribute("eventos");
      if (eventos != null && !eventos.isEmpty()) {
        for (Evento e : eventos) {
    %>
    <div class="evento-item">
      <strong><%= e.getNombre() %></strong><br>
      <span>📅 <%= e.getFechaEvento() %> ⏰ <%= e.getHoraEvento() %></span><br>
      <span>🎯 Tipo: <%= e.getTipoEvento() %></span><br>
      <span>📝 Descripción: <%= e.getDescripcion() %></span><br>
      <span>📍 Ubicación: <%= e.getUbicacion() %></span><br>
      <span>👥 Capacidad: <%= e.getCapacidad() %></span>

      <% if (e.getMultimedia() != null && !e.getMultimedia().isEmpty()) { %>
      <br><img src="UPLOADS/<%= e.getMultimedia() %>" alt="Imagen del evento" width="250" style="margin-top: 10px; border-radius: 6px;">
      <% } %>
    </div>

    <%
      }
    } else {
    %>
    <p>No hay eventos disponibles actualmente.</p>
    <%
      }
    %>
    <% String mensaje = (String) session.getAttribute("mensaje");
      if (mensaje != null) { %>
    <p style="color:green;"><%= mensaje %></p>
    <% session.removeAttribute("mensaje"); } %>

  </div>
</div>
</body>
</html>
