package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class Union_Canal extends DecoratorCUnion_Canal {
    public Union_Canal(ICanal canal) {
        super(canal, "Oyente"); // nombre ficticio por ahora
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "\nCanal actualizado con unión confirmada.";
    }
}
