package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class Reaccion_Canal extends DecoratorCReaccion_Canal {
    public Reaccion_Canal(ICanal canal) {
        super(canal, "Oyente"); // nombre ficticio
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "\nReacción registrada correctamente.";
    }
}