<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="PAC_EVENTOS.MODELO.Evento" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalle del Evento</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/estilos_globales.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/estilos/evento_detalle.css">
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
    <h2>Detalles de los Eventos</h2>

    <% Evento evento = (Evento) request.getAttribute("evento"); %>

    <% if (evento != null) { %>
    <div class="evento-item">
        <p><strong>Nombre del evento:</strong> <%= evento.getNombre() %></p>
        <p><strong>Tipo de evento:</strong> <%= evento.getTipoEvento() %></p>
        <p><strong>Descripción del evento:</strong> <%= evento.getDescripcion() %></p>
        <p><strong>Fecha y Hora:</strong> <%= evento.getFechaEvento() %> a las <%= evento.getHoraEvento() %></p>
        <p><strong>Ubicación del evento:</strong> <%= evento.getUbicacion() %></p>
        <p><strong>👥 Capacidad disponible del evento:</strong> <%= evento.getCapacidad() %></p>

        <% if (evento.getMultimedia() != null && !evento.getMultimedia().isEmpty()) { %>
        <img src="<%= request.getContextPath() + "/uploads/" + evento.getMultimedia() %>"
             alt="Imagen del Evento" width="300" style="border-radius:8px; box-shadow: 0 0 6px #aaa;">
        <% } else { %>
        <p><i>Este evento no tiene imagen asociada.</i></p>
        <% } %>


        <form action="<%= request.getContextPath() %>/Controlador_Eventos" method="post" style="margin-bottom:10px;">
            <input type="hidden" name="accion" value="asistir"/>
            <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>"/>
            <input type="hidden" name="idOyente" value="1"/>
            <input type="submit" value="Asistir">
        </form>

        <form action="<%= request.getContextPath() %>/Controlador_Eventos" method="post" style="margin-bottom:10px;">
            <input type="hidden" name="accion" value="cancelar"/>
            <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>"/>
            <input type="hidden" name="idOyente" value="1"/>
            <input type="submit" value="Cancelar Asistencia">
        </form>

        <form action="Controlador_Eventos" method="post" style="margin-bottom:10px;">
            <input type="hidden" name="accion" value="notificar"/>
            <input type="hidden" name="idEvento" value="<%= evento.getIdEvento() %>"/>
            <input type="submit" value="Notificar"/>
        </form>

        <% String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) { %>
        <p style="color:green;"><strong><%= mensaje %></strong></p>
        <% } %>

        <!--  Botón para regresar al panel del oyente -->
        <form action="<%= request.getContextPath() %>/Controlador_Eventos" method="get">
            <input type="hidden" name="accion" value="irPanelOyente"/>
            <input type="submit" value="Volver al panel del oyente">
        </form>
    </div>
    <% } else { %>
    <p style="color:red;"><strong>No se encontró el evento.</strong></p>
    <% } %>
</div>
</body>
</html>