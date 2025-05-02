package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class FabricaConcreta_Eventos extends FabricaAbstracta_Evento {
    @Override
    public IEvento crearEvento() {
        return new Eventos();
    }
}