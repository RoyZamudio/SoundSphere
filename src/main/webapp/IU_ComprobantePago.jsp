<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%
    String mensaje = (String) request.getAttribute("mensaje");

    // Datos simulados del comprobante
    String numeroOperacion = "TXN" + (int)(Math.random() * 1000000);
    String metodoPago = request.getParameter("metodoPago") != null ? request.getParameter("metodoPago") : "N/A";
    String monto = "$99.00";
    LocalDateTime fecha = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    String idUsuario = request.getParameter("idOyente") != null ? request.getParameter("idOyente") : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado del Pago</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            color: var(--text);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .resultado-card {
            background-color: var(--card);
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(88,104,117,0.2);
            animation: fadeIn 0.6s ease-in-out;
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

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
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
        </div>
    </div>

<div class="container d-flex justify-content-center align-items-center min-vh-100">
    <div class="resultado-card w-100" style="max-width: 550px;">
        <h2 class="text-center mb-3">🧾 Comprobante de Pago</h2>
        <p class="text-center"><strong><%= mensaje %></strong></p>

        <hr>

        <div class="mb-3">
            <p><strong>No. Operación:</strong> <%= numeroOperacion %></p>
            <p><strong>Método de Pago:</strong> <%= metodoPago %></p>
            <p><strong>Monto:</strong> <%= monto %></p>
            <p><strong>Fecha:</strong> <%= fecha.format(formato) %></p>
        </div>

        <hr>

        <div class="mb-4">
            <p>📧 El comprobante ha sido enviado automáticamente a tu correo registrado.</p>
        </div>

        <div class="d-grid gap-2">
            <a href="index.jsp" class="btn btn-outline-secondary">🔁 Volver al inicio de pagos</a>
            <form action="IU_Gestor_Pagos.jsp" method="get">
                <input type="hidden" name="idUsuario" value="<%= idUsuario %>" />
                <button class="btn btn-accent w-100 mt-2">🎬 Volver al Streaming</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
