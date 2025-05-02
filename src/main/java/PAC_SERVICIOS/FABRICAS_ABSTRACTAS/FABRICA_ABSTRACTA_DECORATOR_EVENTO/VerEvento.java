package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_EVENTO;

public class VerEvento extends DecoratorC_VerEvento {
    public VerEvento(IEvento evento) {
        super(evento);
    }

    @Override
    public String mostrar() {
        return super.mostrar() + "<br>Visualización registrada correctamente.";
    }
}