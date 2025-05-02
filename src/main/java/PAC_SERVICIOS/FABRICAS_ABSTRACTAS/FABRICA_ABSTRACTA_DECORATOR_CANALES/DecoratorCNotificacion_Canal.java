package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class DecoratorCNotificacion_Canal extends FabricaDecorator_Canal {
    public DecoratorCNotificacion_Canal(ICanal canal) {
        super(canal);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "\nNotificación enviada al canal.";
    }
}