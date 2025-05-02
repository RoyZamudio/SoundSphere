package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public abstract class FabricaDecorator_Evento implements IEvento {
    protected IEvento evento;

    public FabricaDecorator_Evento(IEvento evento) {
        this.evento = evento;
    }

    @Override
    public String mostrar() {
        return evento.mostrar();
    }
}