package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class Cancion extends Musica {
    private List<String> letra;
    private String videoURL;

    public Cancion(String titulo, String artista, int duracion, int tiempoActual, List<String> letra, String videoURL) {
        super(titulo, artista, duracion, tiempoActual);
        this.letra = new ArrayList<>(letra);
        this.videoURL = videoURL;
        this.setTipo("Cancion");
    }

    public List<String> getLetra() {
        return letra;
    }

    public void setLetra(List<String> letra) {
        this.letra = letra;
    }

    public void agregarVerso(String verso) {
        this.letra.add(verso);
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
