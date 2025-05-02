package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class DecoratorC_NotificaEvento extends FabricaDecorator_Evento {
    public DecoratorC_NotificaEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>Se ha enviado una notificación a todos los asistentes.";
    }
}
