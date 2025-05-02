package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class Notificacion_Canal extends DecoratorCNotificacion_Canal {
    public Notificacion_Canal(ICanal canal) {
        super(canal);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "\nNotificación enviada exitosamente.";
    }
}