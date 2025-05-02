<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Red Social</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/publicaciones.css">
  <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
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

<div class="contenedor-principal">
  <aside class="panel-izquierdo">
    <div class="box">
      <h3>Chats</h3>
      <ul><li>Chat 1</li><li>Chat 2</li><li>Chat 3</li></ul>
    </div>
    <div class="box">
      <h3>Canales</h3>
      <ul><li>Canal 1</li><li>Canal 2</li><li>Canal 3</li></ul>
    </div>
  </aside>

  <main class="contenido-central">
    <div class="post-box">
      <form method="post" action="controlador-publicaciones" enctype="multipart/form-data">
        <input type="hidden" name="accion" value="crear">
        <input type="hidden" name="idPerfil" value="1">
        <input type="hidden" name="tipo" value="texto">

        <textarea name="contenidoTexto" placeholder="¿Qué estás pensando?" rows="3" required></textarea>

        <button type="button" onclick="document.getElementById('imagenInput').click();">📷 Multimedia</button>

        <input type="file" id="imagenInput" name="imagen" accept="image/*" style="display: none;"
               onchange="mostrarVistaPrevia(this); document.querySelector('input[name=tipo]').value = 'multimedia'">

        <div id="vistaPreviaImagen" style="margin-top: 10px;"></div>

        <button type="submit">Publicar</button>
      </form>

      <!-- Publicaciones -->
      <c:forEach var="pub" items="${publicacionesExt}">
        <div class="publicacion-card">
          <div class="publicacion-header">
            <span class="publicacion-usuario">${pub.nombreUsuario}</span>
            <span class="publicacion-fecha">${pub.fechaPublicacion}</span>
          </div>
          <div class="publicacion-contenido">
            <p>${pub.contenidoTexto}</p>
            <c:if test="${pub.imagen != null}">
              <img src="imagenes/${pub.imagen}" alt="Imagen subida">
            </c:if>
          </div>
        </div>
      </c:forEach>
    </div>
  </main>

  <aside class="panel-derecho">
    <div class="bloque">
      <div class="box"><h3>Notificaciones</h3><ul><li>Notificación 1</li><li>Notificación 2</li><li>Notificación 3</li></ul></div>
      <div class="box"><h3>Eventos</h3><ul><li>Evento 1</li><li>Evento 2</li><li>Evento 3</li></ul></div>
    </div>
  </aside>
</div>

<script>
  function mostrarVistaPrevia(input) {
    const preview = document.getElementById("vistaPreviaImagen");
    preview.innerHTML = "";

    if (input.files && input.files[0]) {
      const reader = new FileReader();
      reader.onload = function (e) {
        const img = document.createElement("img");
        img.src = e.target.result;
        img.width = 300;
        preview.appendChild(img);
      };
      reader.readAsDataURL(input.files[0]);
    }
  }
</script>
</body>
</html>