package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaAlbum extends FabricaAbstractaListaR {

    private int seleccion;

    public FabricaConcretaAlbum(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public ListaR crearLista() {
        List<Musica> contenido = new ArrayList<>();

        switch (seleccion) {
            case 1:
                List<String> letraA = new ArrayList<>();
                letraA.add("Verso 1");
                letraA.add("Verso 2");

                List<String> instA = new ArrayList<>();
                instA.add("Flauta");
                instA.add("Cuerdas");

                contenido.add(new Cancion("Despertar", "Aurora Azul", 210, 0, letraA, "https://youtu.be/v1"));
                contenido.add(new Melodia("Viento Norte", "Místico", 200, 0, instA, 8));
                return new Album("Luz de la Mañana", "/img/portadas/luz.jpg", contenido);

            case 2:
                List<String> letraB = new ArrayList<>();
                letraB.add("Verso A");
                letraB.add("Verso B");

                contenido.add(new Cancion("Sonrisa", "Alegría Viva", 185, 0, letraB, "https://youtu.be/v2"));
                return new Album("Días Felices", "/img/portadas/feliz.jpg", contenido);

            default:
                List<String> instDef = new ArrayList<>();
                instDef.add("Sintetizador");
                instDef.add("Piano");

                contenido.add(new Melodia("Susurro del Mar", "Ocean Dreams", 240, 0, instDef, 9));
                return new Album("Reflejo Azul", "/img/portadas/default.jpg", contenido);
        }
    }
}