package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class PedidoCuenta {
    public static ConstructorCuentas obtenerCuenta(String tipoCuenta) {
        switch (tipoCuenta.toLowerCase()) {
            case "admin":
                return new CuentaAdministrador();
            case "premium":
                return new CuentaOyente();
            case "artista":
                return new CuentaArtista();
            case "basico":
                return new CuentaOyente();
            default:
                throw new IllegalArgumentException("Tipo de cuenta inválido: " + tipoCuenta);
        }
    }
}