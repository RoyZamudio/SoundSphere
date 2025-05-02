<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="PAC_CANALES.MODELO.MensajeCanal" %>
<!DOCTYPE html>
<html>
<head>
  <title>Detalle del Canal</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/panel_artista_canal.css">
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
<h2> Canal: ${nombreCanal}</h2>
<p><strong>Descripción:</strong> ${descripcionCanal}</p>

<hr>

<h3>✍Publicar mensaje</h3>
<form action="<%= request.getContextPath() %>/Controlador_Canales" method="post" enctype="multipart/form-data">
  <input type="hidden" name="accion" value="enviarMensaje" />
  <input type="hidden" name="idCanal" value="${idCanal}" />

  <label>Texto:</label><br>
  <textarea name="contenidoTexto" rows="4" cols="60"></textarea><br><br>

  <label>Imagen:</label>
  <input type="file" name="imagen" accept="image/*"><br><br>

  <label> Nota de voz:</label>
  <input type="file" name="audio" accept="audio/*"><br><br>

  <label> Video corto:</label>
  <input type="file" name="video" accept="video/*"><br><br>

  <input type="submit" value="Publicar">
</form>

<hr>
<h3>Mensajes enviados</h3>
<%
  List<MensajeCanal> mensajes = (List<MensajeCanal>) request.getAttribute("mensajes");
  if (mensajes != null && !mensajes.isEmpty()) {
    for (MensajeCanal msg : mensajes) {
%>
<div class="mensaje">
  <p><b>Fecha:</b> <%= msg.getFecha() %></p>

  <% if (msg.getContenido() != null && !msg.getContenido().isEmpty()) { %>
  <p><%= msg.getContenido() %></p>
  <% } %>

  <% if (msg.getTipoMensaje().equals("imagen")) { %>
  <img src="UPLOADS/<%= msg.getContenido() %>" width="250"><br>
  <% } else if (msg.getTipoMensaje().equals("nota_voz")) { %>
  <audio controls>
    <source src="UPLOADS/<%= msg.getContenido() %>" type="audio/mpeg">
  </audio><br>
  <% } else if (msg.getTipoMensaje().equals("video")) { %>
  <video width="320" height="240" controls>
    <source src="UPLOADS/<%= msg.getContenido() %>" type="video/mp4">
  </video><br>
  <% } %>

</div>
<%
  }
} else {
%>
<p>No hay mensajes en este canal todavía.</p>
<%
  }
%>

<% String mensaje = (String) request.getAttribute("mensaje");
  if (mensaje != null) { %>
<p style="color:green;"><b><%= mensaje %></b></p>
<% } %>

<!-- Regresar -->
<form action="Controlador_Canales" method="get">
  <input type="hidden" name="accion" value="irPanelArtista">
  <input type="submit" value="Volver al panel">
</form>
</div>
</body>
</html>