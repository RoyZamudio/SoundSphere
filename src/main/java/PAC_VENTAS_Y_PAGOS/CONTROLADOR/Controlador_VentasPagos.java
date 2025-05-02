package PAC_VENTAS_Y_PAGOS.CONTROLADOR;

import PAC_USUARIOS_Y_CUENTAS.MODELO.ConstructorCuentas;
import PAC_USUARIOS_Y_CUENTAS.MODELO.CuentaOyente;
import PAC_USUARIOS_Y_CUENTAS.MODELO.Perfil;
import PAC_USUARIOS_Y_CUENTAS.MODELO.SesionCuenta;
import PAC_VENTAS_Y_PAGOS.MODELO.*;
import PAC_BD.Conector_BD;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

@WebServlet("/procesarPago")
public class Controlador_VentasPagos extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metodoPago = request.getParameter("metodoPago");
        ConstructorCuentas cuenta = SesionCuenta.getCuentaActual();
        int idOyente;
        if (cuenta instanceof CuentaOyente) {
            CuentaOyente cuentaOyente = (CuentaOyente) cuenta;
            Perfil perfil = cuentaOyente.getPerfil();
            System.out.println(perfil.getTipoCuenta());
            idOyente = perfil.getIdUsuario();
            System.out.println(idOyente);
        }
        else{
            idOyente = -10;
        }
        boolean pagoExitoso = false;

        MetodoDePago metodo = switch (metodoPago) {
            case "debito" -> new TarjetaDebito();
            case "credito" -> new TarjetaCredito();
            case "transferencia" -> new Transferencia();
            case "applepay" -> new ApplePayAdapter(new ApplePayImpl());
            default -> null;
        };

        if (metodo != null) pagoExitoso = metodo.realizarPago();

        String mensaje;
        if (pagoExitoso) {
            try (Connection conexion = Conector_BD.conectar()) {
                PreparedStatement ps = conexion.prepareStatement(
                        "UPDATE Perfil SET tipoCuenta = 'premium' WHERE idPerfil = ?"
                );
                ps.setInt(1, idOyente);
                int actualizado = ps.executeUpdate();

                if (actualizado > 0) {
                    String metodoPagoDB = switch (metodoPago.toLowerCase()) {
                        case "debito", "credito" -> "tarjeta";
                        default -> metodoPago;
                    };

                    PreparedStatement insertPago = conexion.prepareStatement(
                            "INSERT INTO pago (idOyente, metodoPago, fechaPago, monto, estado) VALUES (?, ?, NOW(), ?, ?)"
                    );
                    insertPago.setInt(1, idOyente);
                    insertPago.setString(2, metodoPagoDB);
                    insertPago.setInt(3, 99); // Monto fijo
                    insertPago.setString(4, "exitoso");
                    insertPago.executeUpdate();

                    String destinatario = "isai.zurita@cua.uam.mx"; // Cambia si gustas
                    String asunto = "Comprobante de Pago - SoundSphere 🎵";
                    String contenido = """
¡Hola!

Gracias por tu pago. A continuación te compartimos los detalles de tu transacción:

━━━━━━━━━━━━━━━━━━━━━━
🧾 Comprobante de Pago - SoundSphere

Método de pago: %s
Monto: $99.00
Fecha: %s
Número de operación: TXN%s
━━━━━━━━━━━━━━━━━━━━━━

Este comprobante ha sido generado automáticamente.
Si tienes alguna duda, contáctanos a soporte@soundsphere.com

¡Disfruta tu experiencia premium! 🎧

--
Equipo SoundSphere
""".formatted(metodoPago, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), (int)(Math.random() * 1000000));


                    boolean enviado = MailSender.enviarCorreo(destinatario, asunto, contenido);

                    mensaje = enviado
                            ? "¡Pago exitoso! Se ha enviado tu comprobante al correo 🎶"
                            : "¡Pago exitoso! Pero hubo un error al enviar el correo.";
                } else {
                    mensaje = "Error: oyente no encontrado.";
                }
            } catch (SQLException e) {
                mensaje = "Error al registrar el pago: " + e.getMessage();
            }
        } else {
            mensaje = "Error en el pago. Intenta de nuevo.";
        }

        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("IU_ComprobantePago.jsp").forward(request, response);

    }
}
