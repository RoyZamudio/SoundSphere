package PAC_VENTAS_Y_PAGOS.MODELO;

public class ApplePayAdapter extends MetodoDePago{
    private ApplePay applePay;

    public ApplePayAdapter(ApplePay applePay) {
        this.applePay = applePay;
    }

    @Override
    public boolean realizarPago() {
        return applePay.pagarConApple();
    }
}
