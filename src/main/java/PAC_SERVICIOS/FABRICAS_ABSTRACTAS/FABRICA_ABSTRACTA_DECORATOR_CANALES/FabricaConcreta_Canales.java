package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public class FabricaConcreta_Canales extends FabricaAbstracta_Canal {
    @Override
    public ICanal crearCanal() {
        return new Canales(); // canal base
    }
}
