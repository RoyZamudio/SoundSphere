package PAC_PUBLICACIONES.MODELO;

public class PublicacionExtendida {
    private int idPublicacion;
    private int idPerfil;
    private String nombrePerfil;
    private String contenidoTexto;
    private String fechaPublicacion;
    private String imagen;

    // Getters y Setters
    public int getIdPublicacion() { return idPublicacion; }
    public void setIdPublicacion(int idPublicacion) { this.idPublicacion = idPublicacion; }

    public int getIdPerfil() { return idPerfil; }
    public void setIdPerfil(int idPerfil) { this.idPerfil = idPerfil; }

    public String getNombrePerfil() { return nombrePerfil; }
    public void setNombrePerfil(String nombrePerfil) { this.nombrePerfil = nombrePerfil; }

    public String getContenidoTexto() { return contenidoTexto; }
    public void setContenidoTexto(String contenidoTexto) { this.contenidoTexto = contenidoTexto; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}