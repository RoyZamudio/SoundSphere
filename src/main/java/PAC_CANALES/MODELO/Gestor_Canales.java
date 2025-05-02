// Archivo: Gestor_Canales.java con Decorator y Abstract Factory integrados

package PAC_CANALES.MODELO;

import PAC_BD.Canal_BD;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES.*;

import java.util.List;

public class Gestor_Canales {

    public List<Canal> obtenerCanales() {
        return Canal_BD.obtenerTodos();
    }

    public Canal buscarPorId(int id) {
        return Canal_BD.buscarPorId(id);
    }

    public boolean crearCanal(Canal canal) {
        return Canal_BD.insertar(canal);
    }

    public boolean editarCanal(Canal canal) {
        return Canal_BD.actualizar(canal);
    }


    public boolean enviarMensaje(MensajeCanal mensaje) {
        return Canal_BD.insertarMensaje(mensaje);
    }

    public List<MensajeCanal> obtenerMensajesPorCanal(int idCanal) {
        return Canal_BD.obtenerMensajes(idCanal);
    }

    public boolean reaccionarAMensaje(int idMensaje, int idOyente) {
        return Canal_BD.reaccionarMensaje(idMensaje, idOyente);
    }

    public int obtenerIdCanalDesdeMensaje(int idMensaje) {
        return Canal_BD.obtenerIdCanalDesdeMensaje(idMensaje);
    }

    public int contarReaccionesMensaje(int idMensaje) {
        return Canal_BD.contarReacciones(idMensaje);
    }


    public ICanal construirCanalDecorado(String tipo, ICanal base, String nombreUsuario) {
        if (tipo == null || base == null) return base;

        switch (tipo) {
            case "union":
                return new DecoratorCUnion_Canal(base, nombreUsuario);
            case "reaccion":
                return new DecoratorCReaccion_Canal(base, nombreUsuario);
            case "notificacion":
                return new DecoratorCNotificacion_Canal(base);
            case "combo":
                return new DecoratorCNotificacion_Canal(
                        new DecoratorCUnion_Canal(
                                new DecoratorCReaccion_Canal(base, nombreUsuario), nombreUsuario));
            default:
                return base;
        }
    }

    public ICanal crearCanalBaseDecorado() {
        FabricaAbstracta_Canal fabrica = new FabricaConcreta_Canales();
        return fabrica.crearCanal();
    }
}