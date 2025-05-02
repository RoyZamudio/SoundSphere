<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="PAC_CANALES.MODELO.Canal" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panel del Artista - Canales</title>
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
<h1> Crea tu canal de artista</h1>

<!-- Botón para crear canal -->
<form action="Controlador_Canales" method="get">
    <input type="hidden" name="accion" value="verFormularioCrear">
    <input type="submit" value="+ Crear Canal">
</form>

<hr>

<h2>Mis Canales</h2>
<%
    List<Canal> canales = (List<Canal>) request.getAttribute("canales");
    if (canales != null && !canales.isEmpty()) {
        for (Canal canal : canales) {
%>
<div class="card-canal">
    <p><strong> <%= canal.getNombre() %></strong></p>
    <p><em><%= canal.getDescripcion() %></em></p>

    <!-- Ver canal -->
    <form action="Controlador_Canales" method="get" style="display:inline;">
        <input type="hidden" name="accion" value="verDetalle">
        <input type="hidden" name="idCanal" value="<%= canal.getId() %>">
        <input type="submit" value="Ver canal">
    </form>

    <!-- Editar canal -->
    <form action="Controlador_Canales" method="get" style="display:inline;">
        <input type="hidden" name="accion" value="verFormularioEditar">
        <input type="hidden" name="idCanal" value="<%= canal.getId() %>">
        <input type="submit" value="Editar">
    </form>

</div>
<%
    }
} else {
%>
<p>No tienes canales aún.</p>
<%
    }
%>
</div>
</body>
</html>