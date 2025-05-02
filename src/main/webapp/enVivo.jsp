<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    if (tipoUsuario == null || !"premium".equalsIgnoreCase(tipoUsuario)) {
        response.sendRedirect("accesoDenegado.jsp");
        return;
    }

    String urlVideo = (String) request.getAttribute("urlVideo");
    if (urlVideo == null) {
        urlVideo = "media/sample-video.mp4"; // Fallback
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SoundSphere | En Vivo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <style>
        body {
            background-color: #F6EDDC;
            color: #586875;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .navbar {
            background-color: #586875;
        }
        .navbar .navbar-brand {
            color: #F6EDDC;
            font-weight: bold;
        }
        .navbar .navbar-brand:hover {
            color: #BDD6D2;
        }
        .live-tag {
            background-color: #A5C8CA;
            color: white;
            padding: 6px 12px;
            border-radius: 6px;
            font-weight: bold;
        }
        .container-custom {
            background-color: #E3E5D7;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(88,104,117,0.3);
        }
        video {
            width: 100%;
            border: 4px solid #A5C8CA;
            border-radius: 10px;
        }
        .stats {
            color: #586875;
        }
        .reactions span {
            font-size: 1.5rem;
            margin: 0 10px;
            cursor: pointer;
            transition: transform 0.2s;
        }
        .reactions span:hover {
            transform: scale(1.2);
        }
        .chat-box {
            background-color: #BDD6D2;
            border-radius: 10px;
            padding: 20px;
            margin-top: 20px;
        }
        .chat-msg {
            margin-bottom: 10px;
            background-color: #E3E5D7;
            padding: 10px;
            border-radius: 6px;
        }
        .chat-msg strong {
            color: #586875;
        }
    </style>
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

<nav class="navbar navbar-expand-lg">
    <div class="container-fluid px-4 d-flex justify-content-between align-items-center">
        <a class="navbar-brand" href="#">SoundSphere</a>
        <div class="live-tag">🔴 EN VIVO</div>
    </div>
</nav>

<div class="container my-5">
    <div class="container-custom">
        <video controls autoplay>
            <source src="<%= urlVideo %>" type="video/mp4">
            Tu navegador no soporta la reproducción de video.
        </video>

        <p class="stats mt-3 text-center">
            👁️ 12,478 espectadores | 💬 357 comentarios
        </p>

        <div class="reactions text-center my-3">
            <span title="Like">👍</span>
            <span title="Love">❤️</span>
            <span title="Haha">😂</span>
            <span title="Fire">🔥</span>
            <span title="Clap">👏</span>
        </div>

        <div class="chat-box">
            <h5>💬 Comentarios en vivo</h5>
            <div class="chat-msg"><strong>luna01:</strong> ¡Está increíble esta rola!</div>
            <div class="chat-msg"><strong>djmax:</strong> Vamos Isaí, te rifas 😎</div>
            <div class="chat-msg"><strong>melomanoX:</strong> Amo esta transmisión 🔥🔥🔥</div>
            <div class="chat-msg"><strong>anonimo44:</strong> ¡Saludos desde CDMX!</div>
            <div class="chat-msg"><strong>isa_fan:</strong> ¡No puedo parar de escuchar esto!</div>
        </div>
    </div>
</div>

</body>
</html>
