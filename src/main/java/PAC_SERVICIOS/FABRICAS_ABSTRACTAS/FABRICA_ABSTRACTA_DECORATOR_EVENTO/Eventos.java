package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class Eventos implements IEvento {
    @Override
    public String mostrar() {
        return "Evento base creado.";
    }
}