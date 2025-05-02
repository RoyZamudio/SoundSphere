package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class CuentaArtista extends ConstructorCuentas {
    private Perfil perfil;
    private String nombreArtista;
    private String redesSociales;
    private int oyentesMensuales;

    public CuentaArtista() {
        this.perfil = new Perfil();
        this.nombreArtista = "";
        this.redesSociales = "";
        this.oyentesMensuales = 0;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(String redesSociales) {
        this.redesSociales = redesSociales;
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    //  para crear la cuenta
    @Override
    public void crearCuenta() {
        // Si el perfil no está inicializado, se crea uno nuevo
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

    // Método para mostrar detalles de la cuenta
    public void mostrarDetalles() {
        System.out.println("Nombre Artístico: " + nombreArtista);
        System.out.println("Redes Sociales: " + redesSociales);
        System.out.println("Oyentes Mensuales: " + oyentesMensuales);
        System.out.println("Perfil Asociado: " + perfil.getNombreUsuario() + " | " + perfil.getCorreo());
    }
}