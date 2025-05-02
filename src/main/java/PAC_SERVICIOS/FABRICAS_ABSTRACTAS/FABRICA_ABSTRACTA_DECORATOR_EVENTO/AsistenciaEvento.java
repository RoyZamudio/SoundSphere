package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class AsistenciaEvento extends DecoratorC_AsistenciaEvento {
    public AsistenciaEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>Confirmación de asistencia procesada.";
    }
}
