package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class DecoratorCReaccion_Canal extends FabricaDecorator_Canal {
    private String nombreUsuario;

    public DecoratorCReaccion_Canal(ICanal canal, String nombreUsuario) {
        super(canal);
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "\n " + nombreUsuario + " reaccionó al contenido del canal.";
    }
}
