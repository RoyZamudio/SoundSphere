package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class Melodia extends Musica {
    private List<String> instrumentos;
    private int nivelRelajacion;

    public Melodia(String titulo, String artista, int duracion, int tiempoActual, List<String> instrumentos, int nivelRelajacion) {
        super(titulo, artista, duracion, tiempoActual);
        this.instrumentos = new ArrayList<>(instrumentos);
        this.nivelRelajacion = nivelRelajacion;
        this.setTipo("Melodia");
    }

    public List<String> getInstrumentos() {
        return instrumentos;
    }

    public int getNivelRelajacion() {
        return nivelRelajacion;
    }

    public void setInstrumentos(List<String> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public void setNivelRelajacion(int nivelRelajacion) {
        this.nivelRelajacion = nivelRelajacion;
    }
}
