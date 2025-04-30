package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;
import PAC_BD.Conector_BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Métodos de acciones del reproductor
    public String reproducirMusica() {
        if (musicaActual != null) {
            return reproductor.reproducir(musicaActual);
        } else {
            return "No hay música cargada para reproducir.";
        }
    }

    public void pausarMusica() {
        reproductor.pausar();
    }

    public void adelantarMusica(int segundos) {
        if (musicaActual != null) {
            reproductor.adelantar(musicaActual, segundos);
        }
    }

    public void retrocederMusica(int segundos) {
        if (musicaActual != null) {
            reproductor.retroceder(musicaActual, segundos);
        }
    }

    public String verDatosMusica() {
        if (musicaActual != null) {
            return datos.generarVista(musicaActual);
        } else {
            return "No hay información disponible.";
        }
    }

    public String verResumenLista() {
        if (listaActual != null) {
            return manejador.obtenerResumen(listaActual);
        } else {
            return "No hay lista cargada.";
        }
    }

    // Métodos para la gestión de playlists
    public void agregarMusicaALista(Musica musica) {
        if (listaActual != null && musica != null) {
            manejador.agregarMusica(listaActual, musica);
        }
    }

    public void eliminarMusicaDeLista(Musica musica) {
        if (listaActual != null && musica != null) {
            manejador.eliminarMusica(listaActual, musica);
        }
    }

    public void vaciarLista() {
        if (listaActual != null) {
            manejador.vaciarLista(listaActual);
        }
    }

    public int obtenerCantidadDePistas() {
        if (listaActual != null) {
            return manejador.getCantidadCanciones(listaActual);
        } else {
            return 0;
        }
    }

    public Musica getMusicaActual() {
        return musicaActual;
    }

    public ListaR getListaActual() {
        return listaActual;
    }

    // Getter para el reproductor
    public ReproductorMusical getReproductor() {
        return reproductor;
    }

    public List<Musica> obtenerPistasDesdeBD() {
        List<Musica> listaPistas = new ArrayList<>();

        // Obtener IDs de canciones
        List<Integer> idsCanciones = obtenerIdsDeTabla("cancion", "idCancion");
        for (Integer id : idsCanciones) {
            FabricaAbstractaMusica fabricaCancion = new FabricaConcretaCancion(id);
            Musica cancion = fabricaCancion.crearMusica();
            if (cancion != null) {
                listaPistas.add(cancion);
            }
        }

        // Obtener IDs de melodías
        List<Integer> idsMelodias = obtenerIdsDeTabla("melodia", "idMelodia");
        for (Integer id : idsMelodias) {
            FabricaAbstractaMusica fabricaMelodia = new FabricaConcretaMelodia(id);
            Musica melodia = fabricaMelodia.crearMusica();
            if (melodia != null) {
                listaPistas.add(melodia);
            }
        }

        return listaPistas;
    }

    private List<Integer> obtenerIdsDeTabla(String tabla, String columnaId) {
        List<Integer> ids = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conector_BD.conectar();
            String query = "SELECT " + columnaId + " FROM " + tabla;
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt(columnaId));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener IDs de " + tabla + ": " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }

        return ids;
    }
}