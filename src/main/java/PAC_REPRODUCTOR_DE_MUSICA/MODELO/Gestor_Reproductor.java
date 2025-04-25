package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

public class Gestor_Reproductor {

    // Slaves
    private final ReproductorMusical reproductor;
    private final DatosCancion datos;
    private final ManejadorPlaylist manejador;

    // Objeto actual en reproducción o gestión
    private Musica musicaActual;
    private ListaR listaActual;

    public Gestor_Reproductor() {
        reproductor = new ReproductorMusical();
        datos = new DatosCancion();
        manejador = new ManejadorPlaylist();
    }

    // ==== FABRICACIÓN DE OBJETOS ====

    public void crearCancion(int seleccion) {
        FabricaAbstractaMusica fabrica = new FabricaConcretaCancion(seleccion);
        musicaActual = fabrica.crearMusica();
    }

    public void crearMelodia(int seleccion) {
        FabricaAbstractaMusica fabrica = new FabricaConcretaMelodia(seleccion);
        musicaActual = fabrica.crearMusica();
    }

    public void crearPlaylist(int seleccion) {
        FabricaAbstractaListaR fabrica = new FabricaConcretaPlaylist(seleccion);
        listaActual = fabrica.crearLista();
    }

    public void crearAlbum(int seleccion) {
        FabricaAbstractaListaR fabrica = new FabricaConcretaAlbum(seleccion);
        listaActual = fabrica.crearLista();
    }

    // ==== ACCIONES DEL REPRODUCTOR ====

    public String reproducirMusica() {
        return reproductor.reproducir(musicaActual);
    }

    public void pausarMusica() {
        reproductor.pausar();
    }

    public void adelantarMusica(int segundos) {
        reproductor.adelantar(musicaActual, segundos);
    }

    public void retrocederMusica(int segundos) {
        reproductor.retroceder(musicaActual, segundos);
    }

    // ==== VISUALIZACIÓN DE DATOS ====

    public String verDatosMusica() {
        return datos.generarVista(musicaActual);
    }

    public String verResumenLista() {
        return manejador.obtenerResumen(listaActual);
    }

    // ==== GESTIÓN DE PLAYLISTS ====

    public void agregarMusicaALista(Musica musica) {
        manejador.agregarMusica(listaActual, musica);
    }

    public void eliminarMusicaDeLista(Musica musica) {
        manejador.eliminarMusica(listaActual, musica);
    }

    public void vaciarLista() {
        manejador.vaciarLista(listaActual);
    }

    public int obtenerCantidadDePistas() {
        return manejador.getCantidadCanciones(listaActual);
    }

    // ==== Getters opcionales para IU ====

    public Musica getMusicaActual() {
        return musicaActual;
    }

    public ListaR getListaActual() {
        return listaActual;
    }
}
