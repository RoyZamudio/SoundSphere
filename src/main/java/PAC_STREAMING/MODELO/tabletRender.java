package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public class tabletRender implements PlataformaRender{
    @Override
    public void renderizar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Renderizando para plataforma TABLET.");
        try {
            request.getRequestDispatcher("enVivo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
