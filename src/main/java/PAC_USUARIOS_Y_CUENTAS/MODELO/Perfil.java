package PAC_USUARIOS_Y_CUENTAS.MODELO;

public class Perfil implements IPerfil {
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    private String imagenPerfil;
    private String fechaCreacion;
    private String tipoCuenta;

    // Constructor con parámetros
    public Perfil(int idUsuario, String nombreUsuario, String correo, String contrasena, String imagenPerfil, String fechaCreacion, String tipoCuenta) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.imagenPerfil = imagenPerfil;
        this.fechaCreacion = fechaCreacion;
        this.tipoCuenta = tipoCuenta;
    }

    // Constructor vacío para inicialización sin valores
    public Perfil() {
        this.idUsuario = -1;
        this.nombreUsuario = "";
        this.correo = "";
        this.contrasena = "";
        this.imagenPerfil = "";
        this.fechaCreacion = "";
        this.tipoCuenta = "";
    }

    @Override
    public void iniciarSesion() {
        System.out.println(nombreUsuario + " ha iniciado sesión.");
    }

    @Override
    public void cerrarSesion() {
        System.out.println(nombreUsuario + " ha cerrado sesión.");
    }

    @Override
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    // Métodos Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    // Métodos Setters

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}