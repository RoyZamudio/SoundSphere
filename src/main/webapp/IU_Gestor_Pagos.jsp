<!-- ✅ Archivo: webapp/pagos/presentacion/index.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PagoApp - Realizar Pago</title>
    <link rel="stylesheet" href="estilos/streaming.css">
    <link rel="stylesheet" type="text/css" href="estilos/barra_superior.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script>
        function mostrarCampos() {
            const metodo = document.querySelector('input[name="metodoPago"]:checked').value;

            document.getElementById('datosTarjeta').style.display = metodo === 'debito' || metodo === 'credito' ? 'block' : 'none';
            document.getElementById('datosTransferencia').style.display = metodo === 'transferencia' ? 'block' : 'none';
            document.getElementById('datosApplePay').style.display = metodo === 'applepay' ? 'block' : 'none';
        }

        function formatearTarjeta(input) {
            let valor = input.value.replace(/\D/g, '').substring(0,16);
            valor = valor.replace(/(.{4})/g, '$1 ').trim();
            input.value = valor;
        }

        function formatearFecha(input) {
            let valor = input.value.replace(/\D/g, '').substring(0,4);
            if (valor.length >= 3) {
                valor = valor.substring(0,2) + '/' + valor.substring(2);
            }
            input.value = valor;
        }
    </script>
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
    <h1>💳 Realizar Pago</h1>

    <form action="<%= request.getContextPath() %>/procesarPago" method="post" class="formulario-pago">
        <div class="seccion">
            <label><strong>Seleccione el método de pago:</strong></label><br><br>

            <input type="radio" id="tarjetaDebito" name="metodoPago" value="debito" onclick="mostrarCampos()" required>
            <label for="tarjetaDebito">Tarjeta de Débito</label><br>

            <input type="radio" id="tarjetaCredito" name="metodoPago" value="credito" onclick="mostrarCampos()">
            <label for="tarjetaCredito">Tarjeta de Crédito</label><br>

            <input type="radio" id="transferencia" name="metodoPago" value="transferencia" onclick="mostrarCampos()">
            <label for="transferencia">Transferencia / Oxxo</label><br>

            <input type="radio" id="applePay" name="metodoPago" value="applepay" onclick="mostrarCampos()">
            <label for="applePay">Apple Pay</label><br><br>
        </div>

        <div class="seccion">
            <label><strong>ID del Oyente:</strong></label><br>
            <input type="number" name="idOyente" required><br><br>
        </div>

        <div id="datosTarjeta" class="seccion" style="display: none;">
            <h3>Datos de la Tarjeta</h3>
            <input type="text" name="numeroTarjeta" placeholder="Número de Tarjeta" maxlength="19" oninput="formatearTarjeta(this)"><br><br>
            <input type="text" name="fechaVencimiento" placeholder="MM/YY" maxlength="5" oninput="formatearFecha(this)"><br><br>
            <input type="password" name="cvv" placeholder="CVV" maxlength="3"><br><br>
        </div>

        <div id="datosTransferencia" class="seccion" style="display: none;">
            <h3>Datos para Transferencia</h3>
            <p><strong>Banco:</strong> BBVA</p>
            <p><strong>Cuenta CLABE:</strong> 012345678901234567</p>
            <p><strong>Referencia:</strong> 789456123</p>
            <small>También puedes pagar en Oxxo, 7-Eleven, Santander, Banorte, Inbursa.</small><br><br>
            <img src="https://barcode.tec-it.com/barcode.ashx?data=789456123&code=Code128&dpi=96" alt="Código de Barras">
        </div>

        <div id="datosApplePay" class="seccion" style="display: none;">
            <h3>Paga con Apple Pay</h3>
            <button type="button" class="boton-applepay" onclick="alert('Pago simulado con  Pay')"> Pagar</button>
            <small>Simulación visual de Apple Pay.</small>
        </div>

        <br>
        <button type="submit" class="boton-principal">💰 Realizar Pago</button>
    </form>
</div>
</body>
</html>
