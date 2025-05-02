package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class SesionCuenta {
    private static ConstructorCuentas cuentaActual;

    private SesionCuenta() {
        // Constructor privado para evitar instanciar esta clase
    }

    public static void setCuentaActual(ConstructorCuentas cuenta) {
        cuentaActual = cuenta;
    }

    public static ConstructorCuentas getCuentaActual() {
        return cuentaActual;
    }

    public static void cerrarSesion() {
        cuentaActual = null;
    }
}
