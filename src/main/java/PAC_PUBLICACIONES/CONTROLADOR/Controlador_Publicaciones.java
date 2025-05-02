package PAC_PUBLICACIONES.CONTROLADOR;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import PAC_PUBLICACIONES.MODELO.Gestor_Publicaciones;

import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

@MultipartConfig
@WebServlet("/controlador-publicaciones")
public class Controlador_Publicaciones extends HttpServlet {
    private Gestor_Publicaciones gestor;

    @Override
    public void init() {
        gestor = new Gestor_Publicaciones();
    }

    // 🔄 Mostrar publicaciones al acceder vía GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("publicacionesExt", gestor.obtenerPublicaciones());
        request.getRequestDispatcher("IU_Gestor_Publicaciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String contenido = request.getParameter("contenidoTexto");
        String tipo = request.getParameter("tipo");
        int idPerfil = 1; // 🔒 ID fijo de prueba

        if ("crear".equals(accion)) {
            Part filePart = request.getPart("imagen");
            String nombreArchivo = null;

            if (filePart != null && filePart.getSize() > 0) {
                nombreArchivo = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String ruta = getServletContext().getRealPath("/imagenes/");
                File carpeta = new File(ruta);
                if (!carpeta.exists()) carpeta.mkdirs();
                filePart.write(ruta + File.separator + nombreArchivo);
                tipo = "multimedia";
            }

            gestor.crearPublicacion(tipo, idPerfil, contenido, nombreArchivo);
        } else if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("idPublicacion"));
            gestor.eliminarPublicacion(id);
        }

        request.setAttribute("publicacionesExt", gestor.obtenerPublicaciones());
        request.getRequestDispatcher("IU_Gestor_Publicaciones.jsp").forward(request, response);
    }
}
