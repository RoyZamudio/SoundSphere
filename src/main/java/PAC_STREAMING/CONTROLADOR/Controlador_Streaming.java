package PAC_STREAMING.CONTROLADOR;

import PAC_STREAMING.MODELO.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/Streaming")
public class Controlador_Streaming extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idUsuario;

        try {
            idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        // Obtener el tipo de usuario desde la BD
        String tipoUsuario = ValidadorUsuario.obtenerTipoUsuario(idUsuario);
        request.getSession().setAttribute("tipoUsuario", tipoUsuario);

        // Si es artista, redirigimos directo al panel
        if ("artista".equalsIgnoreCase(tipoUsuario)) {
            response.sendRedirect("panelArtista.jsp");
            return;
        }

        // Si es premium, permitimos acceso al streaming
        boolean acceso = "premium".equalsIgnoreCase(tipoUsuario);
        Usuario usuario = new Usuario(idUsuario, "SinNombre", acceso);

        PlataformaRender plataforma = new webRender(); // o tabletRender, mobileRender
        Streaming streaming = new ProxyStreaming(usuario, plataforma, request);

        usuario.solicitarStreaming(streaming, request, response);
    }
}
