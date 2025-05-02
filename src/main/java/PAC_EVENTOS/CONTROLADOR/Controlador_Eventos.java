package PAC_EVENTOS.CONTROLADOR;

import PAC_EVENTOS.MODELO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "Controlador_Eventos", value = "/Controlador_Eventos")
@MultipartConfig
public class Controlador_Eventos extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";
    private Gestor_Eventos gestor = new Gestor_Eventos();

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
        int idEvento = request.getParameter("idEvento") != null && !request.getParameter("idEvento").isEmpty()
                ? Integer.parseInt(request.getParameter("idEvento")) : -1;
        String mensaje = "";

        switch (accion) {
            case "verFormularioCrear":
                request.getRequestDispatcher("IU_EventoCreaEventoArtista.jsp").forward(request, response);
                break;



            case "listarOyente":
                List<Evento> lista = gestor.obtenerListaEventos();
                request.setAttribute("eventos", lista);
                request.getRequestDispatcher("IU_EventoOyentePrincipal.jsp").forward(request, response);
                break;

            case "buscarPorNombre":
                String nombreEvento = request.getParameter("nombreEvento").trim();
                System.out.println("🔍 Buscando evento con nombre: " + nombreEvento); // Debug

                Evento encontrado = gestor.buscarPorNombre(nombreEvento);

                if (encontrado != null) {
                    System.out.println("Evento encontrado: " + encontrado.getNombre());
                    request.setAttribute("evento", encontrado);
                } else {
                    System.out.println(" Evento NO encontrado.");
                    request.setAttribute("mensaje", "El evento '" + nombreEvento + "' no existe.");
                }

                request.getRequestDispatcher("IU_EventoEditaEventoArtista.jsp").forward(request, response);
                break;


            case "crear":
                Evento nuevoEvento = construirEventoDesdeRequest(request);
                nuevoEvento.setIdArtista(2); // Simulado, reemplazar con valor real
                boolean creado = gestor.crearEvento(nuevoEvento);

                String mensajeCrear = creado ? " Evento creado correctamente." : " Error al crear el evento.";
                request.getSession().setAttribute("mensaje", mensajeCrear);

                response.sendRedirect("Controlador_Eventos?accion=irPanelArtista");
                break;

            case "buscarEliminar":
                String nombreEliminar = request.getParameter("nombreEvento");
                Evento eventoAEliminar = gestor.buscarPorNombre(nombreEliminar);

                if (eventoAEliminar != null) {
                    request.setAttribute("evento", eventoAEliminar);
                    request.getRequestDispatcher("IU_EventoEliminaEventoArtista.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensaje", "No se encontró el evento con ese nombre.");
                    response.sendRedirect("Controlador_Eventos?accion=irPanelArtista");

                }
                break;


            case "eliminar":
                int idEliminarEvento = Integer.parseInt(request.getParameter("idEvento"));
                System.out.println("ID recibido para eliminar: " + idEliminarEvento);

                boolean eliminado = gestor.eliminarEvento(idEliminarEvento);

                String mensajeEliminar = eliminado
                        ? "Evento eliminado correctamente."
                        : "No se pudo eliminar el evento.";

                // Obtener eventos actualizados para recargar la vista
                List<Evento> eventosActualizados = gestor.obtenerListaEventos();
                request.setAttribute("mensaje", mensajeEliminar);
                request.setAttribute("eventos", eventosActualizados);

                request.getRequestDispatcher("IU_EventoArtistaPrincipal.jsp").forward(request, response);
                break;


            case "verFormularioEditar":
                request.getRequestDispatcher("IU_EventoEditaEventoArtista.jsp").forward(request, response);
                break;

            case "verFormularioEliminar":
                request.getRequestDispatcher("IU_EventoEliminaEventoArtista.jsp").forward(request, response);
                break;

            case "irPanelArtista":
                List<Evento> eventos = gestor.obtenerListaEventos();
                request.setAttribute("eventos", eventos);
                request.getRequestDispatcher("IU_EventoArtistaPrincipal.jsp").forward(request, response);
                break;

            case "notificarEvento":
                request.setAttribute("mensaje", " La función de notificación aún no está implementada.");
                request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                break;

            case "confirmarAsistencia":
                int idEventoAsistir = Integer.parseInt(request.getParameter("idEvento"));
                int idOyenteAsistir = Integer.parseInt(request.getParameter("idOyente")); //
                boolean confirmada = gestor.confirmarAsistencia(idEventoAsistir, idOyenteAsistir);

                request.setAttribute("mensaje", confirmada
                        ? "Asistencia confirmada con éxito."
                        : "No se pudo confirmar la asistencia.");

                Evento eventoConfirmado = gestor.obtenerEventoPorId(idEventoAsistir);
                request.setAttribute("evento", eventoConfirmado);
                request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                break;

            case "cancelarAsistencia":
                int idEventoCancelar = Integer.parseInt(request.getParameter("idEvento"));
                int idOyenteCancelar = Integer.parseInt(request.getParameter("idOyente")); // ⚠ Simulado

                boolean cancelada = gestor.cancelarAsistencia(idEventoCancelar, idOyenteCancelar);

                request.setAttribute("mensaje", cancelada
                        ? "Asistencia cancelada exitosamente."
                        : "No se pudo cancelar la asistencia.");

                Evento eventoCancelado = gestor.obtenerEventoPorId(idEventoCancelar);
                request.setAttribute("evento", eventoCancelado);
                request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                break;

            case "irPanelOyente":
                eventos = gestor.obtenerListaEventos(); //  Reutilizamos la variable ya declarada
                request.setAttribute("eventos", eventos);
                request.getRequestDispatcher("IU_EventoOyentePrincipal.jsp").forward(request, response);
                break;

            case "verDetalle":
                try {
                    idEvento = Integer.parseInt(request.getParameter("idEvento"));
                    Evento evento = gestor.obtenerEventoPorId(idEvento);
                    request.setAttribute("evento", evento);
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
                break;

            case "asistir":
                try {
                    idEventoAsistir = Integer.parseInt(request.getParameter("idEvento"));
                    idOyenteAsistir = Integer.parseInt(request.getParameter("idOyente"));
                    boolean confirmado = gestor.confirmarAsistencia(idEventoAsistir, idOyenteAsistir);

                    Evento eventoAsistido = gestor.obtenerEventoPorId(idEventoAsistir);
                    request.setAttribute("evento", eventoAsistido);
                    request.setAttribute("mensaje", confirmado ? "Asistencia confirmada." : "No se pudo confirmar la asistencia.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje", "Error al confirmar asistencia.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                }
                break;

            case "cancelar":
                try {
                    idEventoCancelar = Integer.parseInt(request.getParameter("idEvento"));
                    idOyenteCancelar = Integer.parseInt(request.getParameter("idOyente"));
                    boolean cancelado = gestor.cancelarAsistencia(idEventoCancelar, idOyenteCancelar);

                    eventoCancelado = gestor.obtenerEventoPorId(idEventoCancelar);
                    request.setAttribute("evento", eventoCancelado);
                    request.setAttribute("mensaje", cancelado ? "Asistencia cancelada." : "No se pudo cancelar la asistencia.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje", "Error al cancelar asistencia.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                }
                break;

            case "notificar":
                try {
                    int idEventoNotificar = Integer.parseInt(request.getParameter("idEvento"));
                    Evento eventoNotificar = gestor.obtenerEventoPorId(idEventoNotificar);

                    request.setAttribute("evento", eventoNotificar);
                    request.setAttribute("mensaje", "Funcionalidad de notificación aún no implementada.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje", "Error al intentar notificar.");
                    request.getRequestDispatcher("IU_EventoDetalleOyente.jsp").forward(request, response);
                }
                break;

            case "editar":
                Evento eventoEditado = construirEventoDesdeRequest(request);
                eventoEditado.setIdEvento(idEvento);
                eventoEditado.setIdArtista(2); // reemplazar con ID real si hay sesión OJOOOO!!!!!!!!

                boolean editado = gestor.editarEvento(eventoEditado);


                request.setAttribute("mensaje", mensaje);
                eventos = gestor.obtenerListaEventos();
                request.setAttribute("eventos", eventos);
                request.getRequestDispatcher("IU_EventoArtistaPrincipal.jsp").forward(request, response);

                break;


            default:
                request.setAttribute("mensaje", "Acción no reconocida.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private Evento construirEventoDesdeRequest(HttpServletRequest request) throws IOException, ServletException {
        Evento evento = new Evento();
        evento.setNombre(request.getParameter("nombre"));
        evento.setTipoEvento(request.getParameter("tipo"));
        evento.setDescripcion(request.getParameter("descripcion"));

        String fechaHoraParam = request.getParameter("fechaHora");
        if (fechaHoraParam != null && fechaHoraParam.contains(" ")) {
            String[] partes = fechaHoraParam.split(" ");
            evento.setFechaEvento(partes[0]);
            String hora = partes[1].replace("-", ":");
            if (hora.matches("^\\d{2}:\\d{2}$")) hora += ":00";
            evento.setHoraEvento(hora);
        } else {
            evento.setFechaEvento("");
            evento.setHoraEvento("");
        }

        evento.setUbicacion(request.getParameter("ubicacion"));

        try {
            evento.setCapacidad(Integer.parseInt(request.getParameter("capacidad")));
        } catch (NumberFormatException e) {
            evento.setCapacidad(0);
        }

        Part filePart = request.getPart("archivoMultimedia");
        if (filePart != null && filePart.getSize() > 0) {
            String original = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = original.contains(".") ? original.substring(original.lastIndexOf(".")) : ".jpg";
            String nuevoNombre = "evento_" + System.currentTimeMillis() + extension;

            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String filePath = uploadPath + File.separator + nuevoNombre;
            filePart.write(filePath);
            evento.setMultimedia(nuevoNombre);
        }

        return evento;
    }

}