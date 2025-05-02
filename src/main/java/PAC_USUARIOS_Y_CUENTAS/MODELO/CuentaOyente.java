package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class CuentaOyente extends ConstructorCuentas {
    private Perfil perfil;
    private int esPremium;
    private int puntosRecompensa;

    public CuentaOyente() {
        this.perfil = new Perfil();
        this.esPremium = -1;
        this.puntosRecompensa = 0;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int isEsPremium() {
        return esPremium;
    }

    public void setEsPremium(int esPremium) {
        this.esPremium = esPremium;
    }

    public int getPuntosRecompensa() {
        return puntosRecompensa;
    }

    public void setPuntosRecompensa(int puntosRecompensa) {
        this.puntosRecompensa = puntosRecompensa;
    }

    @Override
    public void crearCuenta() {
        if (this.perfil == null) {
            this.perfil = new Perfil();
        }
    }

    @Override
    public Perfil getCuenta() {
        return perfil;
    }

    @Override
    public void setCuenta(Perfil perfil) {
        this.perfil = perfil;
    }


    public void mostrarDetalles() {
        System.out.println("Cuenta Premium: " + esPremium);
        System.out.println("Puntos de Recompensa: " + puntosRecompensa);
        System.out.println("Perfil Asociado: " + perfil.getNombreUsuario() + " | " + perfil.getCorreo());
    }
}