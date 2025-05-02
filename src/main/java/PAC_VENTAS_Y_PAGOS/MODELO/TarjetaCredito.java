package PAC_VENTAS_Y_PAGOS.MODELO;

public class TarjetaCredito extends TarjetaBancaria{
    @Override
    public boolean realizarPago() {
        return true; // Simulamos éxito
    }
}
