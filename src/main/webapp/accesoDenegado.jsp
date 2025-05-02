<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso Denegado</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <style>
        :root {
            --bg: #F6EDDC;
            --card: #E3E5D7;
            --accent: #A5C8CA;
            --text: #586875;
        }

        body {
            background-color: var(--bg);
            font-family: 'Montserrat', sans-serif;
            color: var(--text);
            animation: fadeIn 1s ease-in-out;
        }

        .denegado-card {
            background-color: var(--card);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(88,104,117,0.2);
            animation: slideIn 0.7s ease-out;
        }

        .btn-accent {
            background-color: var(--accent);
            color: white;
            border: none;
        }

        .btn-accent:hover {
            background-color: #BDD6D2;
            color: #586875;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeIn {
            from {
                background-color: #ffffff;
            }
            to {
                background-color: var(--bg);
            }
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
        </label>
    </div>
</div>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="denegado-card w-100" style="max-width: 500px; text-align: center;">
        <h2 class="mb-3">⛔ Acceso Denegado</h2>
        <p class="mb-4">Este contenido solo está disponible para usuarios <strong>premium</strong>.</p>
        <div class="d-grid gap-2">
            <a href="IU_Gestor_Pagos.jsp" class="btn btn-accent">💳 Ir a Pagar Suscripción</a>
            <a href="IU_Gestor_Pagos.jsp" class="btn btn-outline-secondary">🔁 Reintentar Acceder</a>
        </div>
    </div>
</div>

</body>
</html>
