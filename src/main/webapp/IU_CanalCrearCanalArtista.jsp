<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Canal</title>
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
<h2>Crear Canal</h2>

<form action="<%= request.getContextPath() %>/Controlador_Canales" method="post">
    <input type="hidden" name="accion" value="crear" />

    <label>🎤 Nombre del canal:</label><br>
    <input type="text" name="nombre" required><br><br>

    <label> Descripción del canal:</label><br>
    <textarea name="descripcion" rows="4" cols="50" required></textarea><br><br>

    <input type="submit" value="Crear canal">
</form>

<form action="<%= request.getContextPath() %>/Controlador_Canales" method="get">
    <input type="hidden" name="accion" value="irPanelArtista" />
    <input type="submit" value=" Volver al panel">
</form>
</div>
</body>
</html>
