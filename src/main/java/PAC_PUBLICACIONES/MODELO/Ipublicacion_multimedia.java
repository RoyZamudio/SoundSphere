package PAC_PUBLICACIONES.MODELO;

public class Ipublicacion_multimedia implements Implementacion_publicacion {
    private String imagen;

    public Ipublicacion_multimedia(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public void mostrarContenido() {
        System.out.println("Imagen: " + imagen);
    }

    @Override
    public String obtenerContenidoTexto() {
        return ""; // No aplica texto en publicación multimedia
    }

    @Override
    public String obtenerRutaImagen() {
        return imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}