package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.List;

public class Album extends ListaR {

    // Constructor sin imagen
    public Album(String nombre, List<Musica> contenidoInicial) {
        super(nombre);
        if (contenidoInicial == null || contenidoInicial.isEmpty()) {
            throw new IllegalArgumentException("Un álbum debe tener al menos una canción o melodía");
        }
        this.contenido.addAll(contenidoInicial);
    }

    // Constructor con imagen de portada
    public Album(String nombre, String portadaURL, List<Musica> contenidoInicial) {
        super(nombre, portadaURL);
        if (contenidoInicial == null || contenidoInicial.isEmpty()) {
            throw new IllegalArgumentException("Un álbum debe tener al menos una canción o melodía");
        }
        this.contenido.addAll(contenidoInicial);
    }
}