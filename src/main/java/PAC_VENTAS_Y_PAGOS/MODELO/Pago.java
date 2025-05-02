package PAC_VENTAS_Y_PAGOS.MODELO;

public class Pago {
    private MetodoDePago metodoPago;

    public Pago(MetodoDePago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean realizarPago() {
        return metodoPago.realizarPago();
    }
}
