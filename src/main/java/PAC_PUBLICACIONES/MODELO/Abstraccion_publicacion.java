package PAC_PUBLICACIONES.MODELO;

public abstract class Abstraccion_publicacion {
    public String tipo;
    protected String nombreUsuario;
    protected Implementacion_publicacion implementacion;
    protected int idPublicacion;
    protected int idPerfil;
    protected String nombrePerfil;
    protected String fechaPublicacion;

    public Abstraccion_publicacion(Implementacion_publicacion implementacion) {
        this.implementacion = implementacion;
    }

    public abstract void mostrar();

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Implementacion_publicacion getImplementacion() {
        return implementacion;
    }

    public void setImplementacion(Implementacion_publicacion implementacion) {
        this.implementacion = implementacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // Nuevo método útil para JSP (retorna contenido textual si existe)
    public String getContenidoTexto() {
        if (implementacion instanceof Ipublicacion_texto texto) {
            return texto.getContenidoTexto();
        }
        return "";
    }

    public String getImagen() {
        if (implementacion instanceof Ipublicacion_multimedia img) {
            return img.getImagen();
        }
        return null;
    }
}