package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.util.ArrayList;
import java.util.List;

public abstract class ListaR {
    protected String nombre;
    protected String portadaURL;
    protected List<Musica> contenido;

    public ListaR(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public ListaR(String nombre, String portadaURL) {
        this(nombre);
        this.portadaURL = portadaURL;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Musica> getContenido() {
        return contenido;
    }

    public void agregarMusica(Musica musica) {
        contenido.add(musica);
    }

    public void eliminarMusica(Musica musica) {
        contenido.remove(musica);
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }
}
