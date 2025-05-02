package PAC_USUARIOS_Y_CUENTAS.CONTROLADOR;

import PAC_USUARIOS_Y_CUENTAS.MODELO.Gestor_Cuentas;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControladorInicioSesion", value = "/controlador_inicio_sesion")
public class Controlador_InicioSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mensaje = "Conexión con servlet exitosa desde inicio de sesion";
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + mensaje + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Gestor_Cuentas gestorCuentas = new Gestor_Cuentas();
        Boolean resultado = gestorCuentas.iniciarSesion(email, password);
        if (resultado) {
            String contextPath = request.getContextPath();

            response.sendRedirect(contextPath + "/index.jsp");

        } else {
            response.setContentType("text/html");
            response.getWriter().println("Inicio De Sesion no exitoso");
        }



    }
}
