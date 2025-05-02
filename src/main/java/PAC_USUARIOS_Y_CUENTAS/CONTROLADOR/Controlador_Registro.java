package PAC_USUARIOS_Y_CUENTAS.CONTROLADOR;

import PAC_USUARIOS_Y_CUENTAS.MODELO.Gestor_Cuentas;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ControladorRegistro", value = "/controlador_registro")
public class Controlador_Registro extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String artistaParam = request.getParameter("artista");
        boolean esArtista = artistaParam != null;
        Gestor_Cuentas gestor = new Gestor_Cuentas();
        boolean resultado = gestor.registrarUsuario(nombre, email, password, esArtista);


        String contextPath = request.getContextPath();

        if (resultado) {
            response.sendRedirect(contextPath + "/IU_InicioSesion.jsp");
        } else {

            response.sendRedirect(contextPath + "/IU_Registro.jsp");
        }
    }
}