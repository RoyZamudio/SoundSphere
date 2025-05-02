package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class CuentaAdministrador extends ConstructorCuentas {
    private Perfil perfil;
    private int nivelAcceso;

    public CuentaAdministrador() {
        this.perfil = new Perfil();
        this.nivelAcceso = 0;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    // Método para crear la cuenta
    @Override
    public void crearCuenta() {
        if (this.perfil == null) {
            this.perfil = new Perfil();
        }
    }

    // Devuelve la cuenta actual
    @Override
    public Perfil getCuenta() {
        return perfil;
    }

    @Override
    public void setCuenta(Perfil perfil) {
        this.perfil = perfil;
    }

    // Método para mostrar detalles de la cuenta
    public void mostrarDetalles() {
        System.out.println("Nivel de acceso: " + nivelAcceso);
        System.out.println("Perfil Asociado: " + perfil.getNombreUsuario() + " | " + perfil.getCorreo());
    }
}