package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public class webRender implements PlataformaRender{
    @Override
    public void renderizar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Renderizando para plataforma WEB.");
        try {
            request.getRequestDispatcher("../../../webapp/enVivo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
