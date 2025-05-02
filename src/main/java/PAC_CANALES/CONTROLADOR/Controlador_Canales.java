package PAC_CANALES.CONTROLADOR;

import PAC_CANALES.MODELO.Canal;
import PAC_CANALES.MODELO.Gestor_Canales;
import PAC_CANALES.MODELO.MensajeCanal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "Controlador_Canales", value = "/Controlador_Canales")
@MultipartConfig
public class Controlador_Canales extends HttpServlet {

    private Gestor_Canales gestor = new Gestor_Canales();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String mensaje = "";

        switch (accion) {
            case "irPanelArtista":
                List<Canal> canales = gestor.obtenerCanales();
                request.setAttribute("canales", canales);
                request.getRequestDispatcher("IU_CanalPrincipalArtista.jsp").forward(request, response);
                break;

            case "irPanelOyente":
                List<Canal> canalesOyente = gestor.obtenerCanales();
                request.setAttribute("canales", canalesOyente);
                request.getRequestDispatcher("IU_CanalPrincipalOyente.jsp").forward(request, response);
                break;

            case "verFormularioCrear":
                request.getRequestDispatcher("IU_CanalCrearCanalArtista.jsp").forward(request, response);
                break;

            case "crear":
                Canal nuevo = new Canal();
                nuevo.setIdArtista(2); // Provisional, reemplazar por sesión futura
                nuevo.setNombre(request.getParameter("nombre"));
                nuevo.setDescripcion(request.getParameter("descripcion"));

                boolean creado = gestor.crearCanal(nuevo);

                String mensajeCrear = creado ? "✅ Canal creado correctamente." : "❌ Error al crear canal.";
                request.setAttribute("mensaje", mensajeCrear);

                List<Canal> canalesActualizados = gestor.obtenerCanales();
                request.setAttribute("canales", canalesActualizados);

                request.getRequestDispatcher("IU_CanalPrincipalArtista.jsp").forward(request, response);
                break;


            case "verFormularioEditar":
                int idEditar = Integer.parseInt(request.getParameter("idCanal"));
                Canal canalEditar = gestor.buscarPorId(idEditar);
                request.setAttribute("canal", canalEditar);
                request.getRequestDispatcher("IU_CanalEditaCanalArtista.jsp").forward(request, response);
                break;

            case "editar":
                Canal editar = new Canal();
                editar.setId(Integer.parseInt(request.getParameter("idCanal")));
                editar.setNombre(request.getParameter("nombre"));
                editar.setDescripcion(request.getParameter("descripcion"));
                mensaje = gestor.editarCanal(editar) ? "Canal actualizado correctamente." : "Error al actualizar canal.";
                response.sendRedirect("Controlador_Canales?accion=irPanelArtista");
                break;

            case "verFormularioEliminar":
                int idEliminar = Integer.parseInt(request.getParameter("idCanal"));
                Canal canalEliminar = gestor.buscarPorId(idEliminar);
                request.setAttribute("canal", canalEliminar);
                request.getRequestDispatcher("IU_CanalEliminaCanalArtista.jsp").forward(request, response);
                break;


            case "verMensajesCanal":
                int idCanal = Integer.parseInt(request.getParameter("idCanal"));
                List<MensajeCanal> mensajes = gestor.obtenerMensajesPorCanal(idCanal);
                for (MensajeCanal m : mensajes) {
                    int reacciones = gestor.contarReaccionesMensaje(m.getIdMensaje());
                    m.setReacciones(reacciones);
                }

                Canal canalActual = gestor.buscarPorId(idCanal);
                request.setAttribute("nombreCanal", canalActual.getNombre());
                request.setAttribute("mensajes", mensajes);
                request.getRequestDispatcher("IU_CanalMensajesOyente.jsp").forward(request, response);
                break;


            case "enviarMensaje":
                int canalId = Integer.parseInt(request.getParameter("idCanal"));
                String texto = request.getParameter("contenidoTexto");

                Part imagen = request.getPart("imagen");
                Part audio = request.getPart("audio");
                Part video = request.getPart("video");

                // Obtener la ruta real para la carpeta UPLOADS
                String uploadPath = getServletContext().getRealPath("") + File.separator + "UPLOADS";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // ✅ crea la carpeta si no existe
                }

                MensajeCanal nuevoMensaje = new MensajeCanal();
                nuevoMensaje.setIdCanal(canalId);
                nuevoMensaje.setFecha(java.time.LocalDateTime.now().toString());

                if (imagen != null && imagen.getSize() > 0) {
                    String nombreArchivo = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
                    imagen.write(uploadPath + File.separator + nombreArchivo);
                    nuevoMensaje.setTipoMensaje("imagen");
                    nuevoMensaje.setContenido(nombreArchivo);

                } else if (audio != null && audio.getSize() > 0) {
                    String nombreArchivo = Paths.get(audio.getSubmittedFileName()).getFileName().toString();
                    audio.write(uploadPath + File.separator + nombreArchivo);
                    nuevoMensaje.setTipoMensaje("nota_voz");
                    nuevoMensaje.setContenido(nombreArchivo);

                } else if (video != null && video.getSize() > 0) {
                    String nombreArchivo = Paths.get(video.getSubmittedFileName()).getFileName().toString();
                    video.write(uploadPath + File.separator + nombreArchivo);
                    nuevoMensaje.setTipoMensaje("video");
                    nuevoMensaje.setContenido(nombreArchivo);

                } else {
                    nuevoMensaje.setTipoMensaje("texto");
                    nuevoMensaje.setContenido(texto);
                }

                boolean enviado = gestor.enviarMensaje(nuevoMensaje);
                response.sendRedirect(request.getContextPath() + "/Controlador_Canales?accion=verDetalle&idCanal=" + canalId);
                break;


            case "reaccionar":
                int idMensaje = Integer.parseInt(request.getParameter("idMensaje"));
                int idOyente = 1; // Simulado

                boolean reaccion = gestor.reaccionarAMensaje(idMensaje, idOyente);
                int canalPadre = gestor.obtenerIdCanalDesdeMensaje(idMensaje);
                response.sendRedirect("Controlador_Canales?accion=verMensajesCanal&idCanal=" + canalPadre);
                break;

            case "verDetalle":
                int idCanalDetalle = Integer.parseInt(request.getParameter("idCanal"));
                Canal canalDetalle = gestor.buscarPorId(idCanalDetalle);
                List<MensajeCanal> mensajesDetalle = gestor.obtenerMensajesPorCanal(idCanalDetalle);


                for (MensajeCanal m : mensajesDetalle) {
                    int reacciones = gestor.contarReaccionesMensaje(m.getIdMensaje());
                    m.setReacciones(reacciones);
                }

                request.setAttribute("idCanal", canalDetalle.getId());
                request.setAttribute("nombreCanal", canalDetalle.getNombre());
                request.setAttribute("descripcionCanal", canalDetalle.getDescripcion());
                request.setAttribute("mensajes", mensajesDetalle);

                request.getRequestDispatcher("IU_CanalDetalle.jsp").forward(request, response);
                break;



            default:
                request.setAttribute("mensaje", "Acción no reconocida.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                break;
        }
    }
}
