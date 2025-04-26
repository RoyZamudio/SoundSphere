package PAC_REPRODUCTOR_DE_MUSICA.CONTROLADOR;

import PAC_REPRODUCTOR_DE_MUSICA.MODELO.*;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ControladorReproductor", value = "/controlador-reproductor")
public class Controlador_Reproductor extends HttpServlet {

    private Gestor_Reproductor gestor;

    @Override
    public void init() {
        gestor = new Gestor_Reproductor();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        String seleccionStr = request.getParameter("seleccion");
        int seleccion = seleccionStr != null ? Integer.parseInt(seleccionStr) : 1;

        if (accion == null) {
            request.setAttribute("musica", gestor.getMusicaActual());
            request.setAttribute("lista", gestor.getListaActual());

            RequestDispatcher dispatcher = request.getRequestDispatcher("IU_Gestor_Reproductor.jsp");
            dispatcher.forward(request, response);
            return;
        }

        switch (accion) {
            case "crearCancion":
                gestor.crearCancion(seleccion);
                break;

            case "crearMelodia":
                gestor.crearMelodia(seleccion);
                break;

            case "crearPlaylist":
                gestor.crearPlaylist(seleccion);
                break;

            case "crearAlbum":
                gestor.crearAlbum(seleccion);
                break;

            case "play":
                request.setAttribute("mensaje", gestor.reproducirMusica());
                break;

            case "pause":
                gestor.pausarMusica();
                request.setAttribute("mensaje", "⏸️ Música pausada.");
                break;

            case "adelantar":
                gestor.adelantarMusica(10);
                request.setAttribute("mensaje", "⏩ Música adelantada 10 segundos.");
                break;

            case "retroceder":
                gestor.retrocederMusica(10);
                request.setAttribute("mensaje", "⏪ Música retrocedida 10 segundos.");
                break;

            case "verDatos":
                request.setAttribute("datosMusica", gestor.verDatosMusica());
                break;

            case "verLista":
                request.setAttribute("resumenLista", gestor.verResumenLista());
                break;

            case "agregarAMiLista":
                Musica m = gestor.getMusicaActual();
                if (m != null) {
                    gestor.agregarMusicaALista(m);
                    request.setAttribute("mensaje", "✅ Pista agregada a la lista.");
                } else {
                    request.setAttribute("mensaje", "⚠️ No hay pista actual para agregar.");
                }
                break;

            case "vaciarLista":
                gestor.vaciarLista();
                request.setAttribute("mensaje", "🗑️ Lista vaciada exitosamente.");
                break;

            case "verGestorListas":
                request.setAttribute("lista", gestor.getListaActual());
                RequestDispatcher irAPlaylists = request.getRequestDispatcher("IU_Gestor_Playlists.jsp");
                irAPlaylists.forward(request, response);
                return;

            case "volverReproductor":
                request.setAttribute("musica", gestor.getMusicaActual());
                request.setAttribute("lista", gestor.getListaActual());
                RequestDispatcher volver = request.getRequestDispatcher("IU_Gestor_Reproductor.jsp");
                volver.forward(request, response);
                return;
        }

        request.setAttribute("musica", gestor.getMusicaActual());
        request.setAttribute("lista", gestor.getListaActual());

        RequestDispatcher dispatcher = request.getRequestDispatcher("IU_Gestor_Reproductor.jsp");
        dispatcher.forward(request, response);
    }
}