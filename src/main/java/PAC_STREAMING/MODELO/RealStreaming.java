package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public class RealStreaming implements Streaming {
    private PlataformaRender plataforma;

    public RealStreaming(PlataformaRender plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public void verTransmision(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Iniciando transmisión real...");
        plataforma.renderizar(request, response);
    }
}
