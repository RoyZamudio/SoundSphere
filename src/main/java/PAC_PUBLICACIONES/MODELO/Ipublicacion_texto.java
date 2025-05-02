package PAC_PUBLICACIONES.MODELO;

public class Ipublicacion_texto implements Implementacion_publicacion {
    private String contenidoTexto;

    public Ipublicacion_texto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    @Override
    public void mostrarContenido() {
        System.out.println("Texto: " + contenidoTexto);
    }

    @Override
    public String obtenerContenidoTexto() {
        return contenidoTexto;
    }

    @Override
    public String obtenerRutaImagen() {
        return null; // No aplica para texto
    }

    public String getContenidoTexto() {
        return contenidoTexto;
    }
}