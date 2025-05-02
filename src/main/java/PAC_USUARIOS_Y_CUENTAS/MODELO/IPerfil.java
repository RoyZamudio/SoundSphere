package PAC_USUARIOS_Y_CUENTAS.MODELO;

public interface IPerfil {
    void iniciarSesion();
    void cerrarSesion();
    String getTipoCuenta();
}