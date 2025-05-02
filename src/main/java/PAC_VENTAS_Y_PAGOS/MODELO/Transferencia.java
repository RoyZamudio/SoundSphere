package PAC_VENTAS_Y_PAGOS.MODELO;

public class Transferencia extends MetodoDePago{
    @Override
    public boolean realizarPago() {
        return true; // Simulamos éxito
    }
}
