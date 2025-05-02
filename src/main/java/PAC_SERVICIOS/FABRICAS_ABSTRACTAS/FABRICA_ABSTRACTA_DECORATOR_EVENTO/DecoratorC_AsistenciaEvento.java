package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class DecoratorC_AsistenciaEvento extends FabricaDecorator_Evento {
    public DecoratorC_AsistenciaEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>El usuario ha confirmado su asistencia al evento.";
    }
}
