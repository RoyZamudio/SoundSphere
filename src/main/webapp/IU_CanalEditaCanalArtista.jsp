<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="PAC_CANALES.MODELO.Canal" %>
<!DOCTYPE html>
<html>
<head>
  <title>Editar Canal</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/estilos_globales.css">
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
<div>
<h2>Editar Canal</h2>

<%
  Canal canal = (Canal) request.getAttribute("canal");
  if (canal != null) {
%>
<form action="Controlador_Canales" method="post">
  <input type="hidden" name="accion" value="editar">
  <input type="hidden" name="idCanal" value="<%= canal.getId() %>">

  <label>🎤 Nombre del canal:</label><br>
  <input type="text" name="nombre" value="<%= canal.getNombre() %>" required><br><br>

  <label>📝 Descripción:</label><br>
  <textarea name="descripcion" rows="4" cols="50" required><%= canal.getDescripcion() %></textarea><br><br>

  <input type="submit" value="✅ Actualizar Canal">
</form>

<%
} else {
%>
<p style="color:red;">❌ No se pudo cargar el canal para editar.</p>
<%
  }
%>

<%
  String mensaje = (String) request.getAttribute("mensaje");
  if (mensaje != null) {
%>
<p style="color:green;"><b><%= mensaje %></b></p>
<%
  }
%>

<!-- Botón para regresar -->
<form action="Controlador_Canales" method="get">
  <input type="hidden" name="accion" value="irPanelArtista">
  <input type="submit" value="⬅️ Volver al panel">
</form>
</div>
</body>
</html>
