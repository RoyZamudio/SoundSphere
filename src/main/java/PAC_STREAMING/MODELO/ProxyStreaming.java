package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public class ProxyStreaming implements Streaming {
    private Usuario usuario;
    private RealStreaming realStreaming;
    private HttpServletRequest request;

    public ProxyStreaming(Usuario usuario, PlataformaRender plataforma, HttpServletRequest request) {
        this.usuario = usuario;
        this.realStreaming = new RealStreaming(plataforma);
        this.request = request;
    }

    @Override
    public void verTransmision(HttpServletRequest request, HttpServletResponse response) {
        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");

        System.out.println("🧠 Tipo de usuario desde sesión: " + tipoUsuario);

        if ("premium".equalsIgnoreCase(tipoUsuario) || "artista".equalsIgnoreCase(tipoUsuario)) {
            realStreaming.verTransmision(request, response);
        } else {
            System.out.println("🚫 Acceso denegado. Solo usuarios premium o artistas pueden ver esta transmisión.");
            try {
                request.getRequestDispatcher("../../../webapp/accesoDenegado.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
