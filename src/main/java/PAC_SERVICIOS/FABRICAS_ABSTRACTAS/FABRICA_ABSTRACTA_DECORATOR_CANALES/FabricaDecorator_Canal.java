package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_DECORATOR_CANALES;

public abstract class FabricaDecorator_Canal implements ICanal {
    protected ICanal canalDecorado;

    public FabricaDecorator_Canal(ICanal canal) {
        this.canalDecorado = canal;
    }

    @Override
    public String mostrar() {
        return canalDecorado.mostrar();
    }
}
