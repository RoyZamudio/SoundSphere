<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    if (tipoUsuario == null || !"artista".equalsIgnoreCase(tipoUsuario)) {
        response.sendRedirect("accesoDenegado.jsp");
        return;
    }
    String enlaceStreaming = "https://stream.com/live1";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>SoundSphere | Panel Artista</title>
    <link rel="stylesheet" type="text/css" href="estilos/streaming.css">
    <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .panel-container {
            background-color: var(--color2);
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(88,104,117,0.2);
            display: flex;
            gap: 20px;
            flex-wrap: wrap;
            max-width: 1200px;
            width: 100%;
            margin: 40px auto;
        }
        .video-section, .comments-section {
            flex: 1;
            min-width: 300px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .live-indicator {
            font-weight: bold;
            color: red;
            margin-bottom: 10px;
            display: none;
        }
        .viewer-count {
            font-size: 16px;
            margin-bottom: 20px;
        }
        video {
            width: 100%;
            border: 4px solid var(--color4);
            border-radius: 12px;
        }
        .comments-box {
            background-color: var(--color3);
            height: 400px;
            overflow-y: auto;
            padding: 15px;
            border-radius: 10px;
            width: 100%;
            max-width: 500px;
            margin-bottom: 10px;
        }
        .btn-accent {
            background-color: var(--color3);
            color: var(--color5);
            border: none;
            border-radius: 8px;
            padding: 10px 20px;
            margin-top: 10px;
            cursor: pointer;
        }
        .btn-accent:hover {
            background-color: var(--color4);
            color: white;
        }
        .enlace-box {
            background-color: var(--color3);
            padding: 15px;
            border-radius: 10px;
            width: 100%;
            max-width: 500px;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            border: 1px solid var(--color4);
            border-radius: 8px;
            font-size: 16px;
        }
        .emoji {
            position: absolute;
            font-size: 24px;
            animation: flotar 6s linear forwards;
            pointer-events: none;
        }
        @keyframes flotar {
            0% { transform: translateY(0) translateX(0); opacity: 1; }
            100% { transform: translateY(-600px) translateX(50px); opacity: 0; }
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

<div class="panel-container">
    <div class="video-section">
        <h2>🎤 Panel de Transmisión - Artista</h2>
        <div id="liveStatus" class="live-indicator">🔴 EN VIVO</div>
        <div id="viewerCount" class="viewer-count"></div>

        <video id="miVideo" autoplay muted playsinline></video>

        <div class="d-grid gap-2 my-3" id="botonesTransmision">
            <button class="btn-accent" onclick="iniciarTransmision()" id="btnIniciar">🎬 Iniciar Transmisión</button>
        </div>
    </div>

    <div class="comments-section">
        <h5>💬 Comentarios en vivo</h5>
        <div class="comments-box" id="comentarios">
            <p><strong>luna01:</strong> ¡Ya queremos escucharte en vivo!</p>
            <p><strong>djmax:</strong> ¡Listos para el show! 🎶</p>
            <p><strong>beatlover:</strong> ¡Vamos a romperla esta noche! 🔥</p>
            <p><strong>musicfan22:</strong> 🎵 ¡Gran talento, éxito total!</p>
            <p><strong>rockstar07:</strong> ¡Ésta es mi rola favorita! 🤘</p>
        </div>

        <div class="enlace-box">
            <h5>🔗 Comparte tu transmisión</h5>
            <input type="text" id="enlaceStreaming" value="<%= enlaceStreaming %>" readonly>
            <button class="btn-accent mt-2" onclick="copiarEnlace()">📋 Copiar enlace</button>
        </div>
    </div>
</div>

<script>
    let streamActual = null;
    let intervaloEmojis;

    function iniciarTransmision() {
        alert("🎬 ¡Transmisión iniciada!");
        document.getElementById("liveStatus").style.display = "block";
        document.getElementById("viewerCount").innerHTML = "👁️ 25 espectadores";

        const botonesDiv = document.getElementById("botonesTransmision");
        botonesDiv.innerHTML = '<button class="btn-accent" onclick="detenerTransmision()">🛑 Detener Transmisión</button>';

        navigator.mediaDevices.getUserMedia({ video: true, audio: false })
            .then(function(stream) {
                document.getElementById('miVideo').srcObject = stream;
                streamActual = stream;
            })
            .catch(function(err) {
                console.error("🚫 Error cámara:", err);
            });

        intervaloEmojis = setInterval(crearEmoji, 2000);
    }

    function detenerTransmision() {
        alert("🛑 ¡Transmisión detenida!");
        document.getElementById("viewerCount").innerHTML = "";
        document.getElementById("liveStatus").style.display = "none";

        if (streamActual) {
            streamActual.getTracks().forEach(track => track.stop());
            streamActual = null;
        }

        clearInterval(intervaloEmojis);

        const botonesDiv = document.getElementById("botonesTransmision");
        botonesDiv.innerHTML = '<button class="btn-accent" disabled>🚫 Transmisión finalizada</button>';
    }

    function copiarEnlace() {
        const enlace = document.getElementById("enlaceStreaming");
        enlace.select();
        document.execCommand("copy");
        alert("🔗 ¡Enlace copiado!");
    }

    function crearEmoji() {
        const emojis = ["🎶", "🚀", "❤️", "✨", "🎤", "👏", "🎧"];
        const emoji = document.createElement('div');
        emoji.className = 'emoji';
        emoji.style.left = Math.random() * 90 + '%';
        emoji.innerText = emojis[Math.floor(Math.random() * emojis.length)];
        document.querySelector('.panel-container').appendChild(emoji);

        setTimeout(() => emoji.remove(), 6000);
    }
</script>
</body>
</html>