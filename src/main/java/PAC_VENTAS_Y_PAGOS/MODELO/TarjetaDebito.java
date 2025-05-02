package PAC_VENTAS_Y_PAGOS.MODELO;

public class TarjetaDebito extends TarjetaBancaria{
    @Override
    public boolean realizarPago() {
        return true; // Simulamos éxito
    }
}
