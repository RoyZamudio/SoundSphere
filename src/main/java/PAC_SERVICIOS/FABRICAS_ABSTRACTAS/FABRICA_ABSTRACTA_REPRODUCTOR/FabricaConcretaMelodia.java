package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaMelodia extends FabricaAbstractaMusica {

    private int seleccion;

    public FabricaConcretaMelodia(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public Musica crearMusica() {
        List<String> instrumentos = new ArrayList<>();
        String titulo, artista;
        int duracion, tiempoActual = 0, nivelRelajacion;

        switch (seleccion) {
            case 1:
                titulo = "Meditación Lunar";
                artista = "Ambient Flow";
                duracion = 300;
                instrumentos.add("Flauta");
                instrumentos.add("Piano");
                instrumentos.add("Pad sintetizado");
                nivelRelajacion = 9;
                break;

            case 2:
                titulo = "Bruma de la Mañana";
                artista = "Calma Interior";
                duracion = 240;
                instrumentos.add("Violín");
                instrumentos.add("Arpa");
                nivelRelajacion = 8;
                break;

            default:
                titulo = "Ecos del Bosque";
                artista = "Sonido Natural";
                duracion = 270;
                instrumentos.add("Viento");
                instrumentos.add("Piano");
                nivelRelajacion = 7;
                break;
        }

        return new Melodia(titulo, artista, duracion, tiempoActual, instrumentos, nivelRelajacion);
    }
}