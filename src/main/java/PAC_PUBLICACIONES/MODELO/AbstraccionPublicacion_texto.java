package PAC_PUBLICACIONES.MODELO;

public class AbstraccionPublicacion_texto extends Abstraccion_publicacion {

    public AbstraccionPublicacion_texto(Implementacion_publicacion implementacion) {
        super(implementacion);
        this.tipo = "Ipublicacion_texto";
    }

    @Override
    public void mostrar() {
        implementacion.mostrarContenido();
    }

    @Override
    public String getContenidoTexto() {
        return implementacion.obtenerContenidoTexto();
    }

    @Override
    public String getImagen() {
        return null;
    }
}