package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import PAC_BD.Conector_BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaAlbum extends FabricaAbstractaListaR {

    private int seleccion;

    public FabricaConcretaAlbum(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public ListaR crearLista() {
        Album album = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Musica> cancionesDelAlbum = new ArrayList<>();

        try {
            conn = Conector_BD.conectar();

            // 1. Obtener los datos del álbum
            String queryAlbum = "SELECT * FROM album WHERE idAlbum = ?";
            stmt = conn.prepareStatement(queryAlbum);
            stmt.setInt(1, seleccion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // 2. Si existe, entonces cargamos sus canciones
                cancionesDelAlbum = obtenerCancionesDelAlbum(conn, seleccion);

                // 3. Crear el objeto Album usando el ResultSet y las canciones
                album = new Album(rs, cancionesDelAlbum);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al cargar álbum desde la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error cerrando conexión: " + e.getMessage());
            }
        }

        return album;
    }

    private List<Musica> obtenerCancionesDelAlbum(Connection conn, int idAlbum) {
        List<Musica> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT c.* FROM cancion c JOIN cancion_album ca ON c.idCancion = ca.idCancion WHERE ca.idAlbum = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idAlbum);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idCancion = rs.getInt("idCancion");
                int idArtista = rs.getInt("idArtista");
                String titulo = rs.getString("titulo");
                int duracion = rs.getTime("duracion").toLocalTime().toSecondOfDay();
                int numReproducciones = rs.getInt("numReproducciones");
                String fecha = rs.getDate("fechaLanzamiento").toString();
                String videoURL = rs.getString("enlaceVideo");

                // Procesar la letra
                List<String> letra = new ArrayList<>();
                String texto = rs.getString("letra");
                if (texto != null) {
                    for (String verso : texto.split("\\n")) {
                        letra.add(verso.trim());
                    }
                }

                Cancion cancion = new Cancion(idCancion, idArtista, titulo, duracion, 0, numReproducciones, fecha, letra, videoURL);
                lista.add(cancion);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al cargar canciones del álbum: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error cerrando conexión secundaria: " + e.getMessage());
            }
        }

        return lista;
    }
}