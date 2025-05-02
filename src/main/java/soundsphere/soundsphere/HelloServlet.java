package soundsphere.soundsphere;

import java.io.*;

import PAC_USUARIOS_Y_CUENTAS.MODELO.ConstructorCuentas;
import PAC_USUARIOS_Y_CUENTAS.MODELO.CuentaOyente;
import PAC_USUARIOS_Y_CUENTAS.MODELO.Perfil;
import PAC_USUARIOS_Y_CUENTAS.MODELO.SesionCuenta;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int mensaje = obtener();
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + mensaje + "</h1>");
        out.println("</body></html>");
    }

    public int obtener() {
        ConstructorCuentas cuenta = SesionCuenta.getCuentaActual();
        int tipo;
        if (cuenta instanceof CuentaOyente) {
            CuentaOyente cuentaOyente = (CuentaOyente) cuenta;
            Perfil perfil = cuentaOyente.getPerfil();
            System.out.println(perfil.getTipoCuenta());
            tipo = perfil.getIdUsuario();
        }
        else{
            tipo = -10;
        }
        return tipo;
    }

}