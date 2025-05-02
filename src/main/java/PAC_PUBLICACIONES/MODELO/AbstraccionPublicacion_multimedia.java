package PAC_PUBLICACIONES.MODELO;

public class AbstraccionPublicacion_multimedia extends Abstraccion_publicacion {

    public AbstraccionPublicacion_multimedia(Implementacion_publicacion implementacion) {
        super(implementacion);
        this.tipo = "Ipublicacion_multimedia";
    }

    @Override
    public void mostrar() {
        implementacion.mostrarContenido();
    }

    @Override
    public String getContenidoTexto() {
        return null;
    }

    @Override
    public String getImagen() {
        return implementacion.obtenerRutaImagen();
    }
}