package PAC_EVENTOS.MODELO;

import PAC_BD.EventoBD;
import PAC_BD.AsistenciaEventoBD;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO.AsistenciaEvento;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO.IEvento;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO.NotificaEvento;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO.VerEvento;

import java.util.List;

public class Gestor_Eventos {
    private EventoBD eventoBD = new EventoBD();
    private AsistenciaEventoBD asistenciaEventoBD = new AsistenciaEventoBD();

    public List<Evento> obtenerListaEventos() {
        return eventoBD.findAll();
    }

    public Evento obtenerEventoPorId(int idEvento) {
        return eventoBD.findById(idEvento);
    }

    public Evento buscarPorNombre(String nombre) {
        return eventoBD.findByNombre(nombre);
    }

    public boolean crearEvento(Evento evento) {
        return eventoBD.create(evento);
    }

    public boolean editarEvento(Evento evento) {
        return eventoBD.update(evento);
    }

    public boolean eliminarEvento(int idEvento) {
        return eventoBD.delete(idEvento);
    }

    public boolean confirmarAsistencia(int idEvento, int idOyente) {
        return asistenciaEventoBD.confirmarAsistencia(idEvento, idOyente);
    }

    public boolean cancelarAsistencia(int idEvento, int idOyente) {
        return asistenciaEventoBD.cancelarAsistencia(idEvento, idOyente);
    }

    public IEvento construirEventoDecorado(String tipo, IEvento eventoBase) {
        if (tipo == null) return eventoBase;
        switch (tipo) {
            case "ver":
                return new VerEvento(eventoBase);
            case "asistencia":
                return new AsistenciaEvento(eventoBase);
            case "notificar":
                return new NotificaEvento(eventoBase);
            case "combo1":
                return new AsistenciaEvento(new VerEvento(eventoBase));
            case "combo2":
                return new NotificaEvento(new AsistenciaEvento(eventoBase));
            case "full":
                return new VerEvento(new AsistenciaEvento(new NotificaEvento(eventoBase)));
            default:
                return eventoBase;
        }
    }
}