package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class Canales implements ICanal {
    @Override
    public String mostrar() {
        return "Canal base creado: Canal #general";
    }
}