package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaCancion extends FabricaAbstractaMusica {

    private int seleccion;

    public FabricaConcretaCancion(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public Musica crearMusica() {
        List<String> letra = new ArrayList<>();
        String videoURL;
        Cancion cancion;

        switch (seleccion) {
            case 1:
                letra.add("En la noche brilla el sol");
                letra.add("Caminamos sin razón");
                letra.add("Los recuerdos ya se van");
                letra.add("Y despierto en tu canción");
                videoURL = "https://youtu.be/video1";
                cancion = new Cancion("Despertar", "Aurora Azul", 210, 0, letra, videoURL);
                break;

            case 2:
                letra.add("Fluye la ciudad al ritmo del tren");
                letra.add("Mil historias que vienen y van");
                letra.add("El concreto no ahoga mi voz");
                letra.add("Sigo el beat, no miro atrás");
                videoURL = "https://youtu.be/video2";
                cancion = new Cancion("Beat Urbano", "Ritmo Callejero", 200, 0, letra, videoURL);
                break;

            default:
                letra.add("Silencio entre las nubes");
                letra.add("Una melodía azul");
                videoURL = "https://youtu.be/videoDefault";
                cancion = new Cancion("Nube", "Minimal Sound", 190, 0, letra, videoURL);
                break;
        }

        return cancion;
    }
}
