package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class DecoratorC_VerEvento extends FabricaDecorator_Evento {
    public DecoratorC_VerEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>El evento ha sido visto por el usuario.";
    }
}