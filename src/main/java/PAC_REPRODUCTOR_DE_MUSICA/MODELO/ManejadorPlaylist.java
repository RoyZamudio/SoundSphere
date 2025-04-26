package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_BD.Conector_BD;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorPlaylist {

    // Retorna una vista tipo HTML con los contenidos de la lista
    public String obtenerResumen(ListaR lista) {
        StringBuilder sb = new StringBuilder();

        if (lista == null) {
            return "❌ No hay lista cargada.";
        }

        sb.append("📀 Lista: ").append(lista.getNombre()).append("<br/>");

        if (lista.getContenido() == null || lista.getContenido().isEmpty()) {
            sb.append("📭 Lista vacía.");
        } else {
            for (Musica m : lista.getContenido()) {
                String nombreArtista = obtenerNombreArtista(m.getIdArtista());
                sb.append("🎵 ").append(m.getTitulo())
                        .append(" - ").append(nombreArtista != null ? nombreArtista : "Artista desconocido")
                        .append(" (").append(m.getDuracion()).append("s)<br/>");
            }
        }

        return sb.toString();
    }

    private String obtenerNombreArtista(int idArtista) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String nombreArtista = null;

        try {
            conn = Conector_BD.conectar();
            String query = "SELECT nombreArtistico FROM artista WHERE idArtista = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idArtista);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nombreArtista = rs.getString("nombreArtistico");
            }

        } catch (SQLException e) {
            System.err.println("⚠️ Error al buscar el nombre del artista: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error cerrando conexión: " + e.getMessage());
            }
        }

        return nombreArtista;
    }

    // Agrega una canción o melodía a la lista
    public void agregarMusica(ListaR lista, Musica musica) {
        if (lista != null && musica != null) {
            lista.agregarMusica(musica);
        }
    }

    // Elimina una pista de la lista
    public void eliminarMusica(ListaR lista, Musica musica) {
        if (lista != null && musica != null) {
            lista.eliminarMusica(musica);
        }
    }

    // Elimina todo el contenido de la lista
    public void vaciarLista(ListaR lista) {
        if (lista != null) {
            lista.getContenido().clear();
        }
    }

    // Devuelve cuántas canciones o melodías hay en la lista
    public int getCantidadCanciones(ListaR lista) {
        if (lista != null && lista.getContenido() != null) {
            return lista.getContenido().size();
        }
        return 0;
    }
}