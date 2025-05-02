package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class NotificaEvento extends DecoratorC_NotificaEvento {
    public NotificaEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>Notificación enviada exitosamente.";
    }
}
