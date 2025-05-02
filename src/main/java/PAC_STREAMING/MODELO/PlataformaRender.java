package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public interface PlataformaRender {
    void renderizar(HttpServletRequest request, HttpServletResponse response);
}
