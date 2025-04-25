package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

public class FabricaConcretaPlaylist extends FabricaAbstractaListaR {

    private int seleccion;

    public FabricaConcretaPlaylist(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public ListaR crearLista() {
        Playlist playlist;

        switch (seleccion) {
            case 1:
                playlist = new Playlist("Lofi para estudiar", "/img/portadas/lofi.jpg");
                break;
            case 2:
                playlist = new Playlist("Pop Latino", "/img/portadas/pop.jpg");
                break;
            case 3:
                playlist = new Playlist("Jazz relajante", "/img/portadas/jazz.jpg");
                break;
            default:
                playlist = new Playlist("Mi Playlist", "/img/portadas/default.jpg");
                break;
        }

        return playlist;
    }
}