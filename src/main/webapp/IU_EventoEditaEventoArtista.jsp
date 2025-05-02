<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="PAC_EVENTOS.MODELO.Evento" %>
<!DOCTYPE html>
<html>
<head><title>Editar Evento</title></head>
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
<h2>Buscar Evento por Nombre</h2>

<form action="<%=request.getContextPath()%>/Controlador_Eventos" method="get">
  <input type="hidden" name="accion" value="buscarPorNombre">
  <label>Nombre del evento:</label><br>
  <input type="text" name="nombreEvento" required><br><br>
  <input type="submit" value="Buscar Evento">
</form>

<% String mensaje = (String) request.getAttribute("mensaje");
  if (mensaje != null) { %>
<p style="color:green;"><b><%= mensaje %></b></p>
<% } %>

<% Evento evento = (Evento) request.getAttribute("evento");
  if (evento != null) { %>
<h2>Editar Evento</h2>
<form action="<%=request.getContextPath()%>/Controlador_Eventos" method="post" enctype="multipart/form-data">
  <input type="hidden" name="accion" value="editar">
  <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>">

  <label>Nombre:</label><br>
  <input type="text" name="nombre" value="<%= evento.getNombre() %>" required><br><br>

  <label>Tipo:</label><br>
  <input type="text" name="tipo" value="<%= evento.getTipoEvento() %>" required><br><br>

  <label>Descripción:</label><br>
  <textarea name="descripcion" required><%= evento.getDescripcion() %></textarea><br><br>

  <label>Fecha y hora (yyyy-mm-dd hh:mm):</label><br>
  <input type="text" name="fechaHora" value="<%= evento.getFechaEvento() %> <%= evento.getHoraEvento() %>" required><br><br>

  <label>Ubicación:</label><br>
  <input type="text" name="ubicacion" value="<%= evento.getUbicacion() %>" required><br><br>

  <label>Capacidad:</label><br>
  <input type="number" name="capacidad" value="<%= evento.getCapacidad() %>" required><br><br>

  <label>Archivo multimedia (opcional):</label><br>
  <input type="file" name="archivoMultimedia"><br><br>

  <input type="submit" value="Actualizar Evento">
</form>
<% } %>
</div>
</body>
</html>