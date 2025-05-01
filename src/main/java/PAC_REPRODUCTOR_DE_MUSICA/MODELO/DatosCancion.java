package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_BD.Conector_BD;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class DatosCancion {

    public String generarVista(Musica musica) {

        StringBuilder vista = new StringBuilder();
        vista.append("<h2>").append(musica.getTitulo()).append("</h2>");

        // Obtener nombre del artista usando el idArtista
        String nombreArtista = obtenerNombreArtista(musica.getIdArtista());
        vista.append("<p>Artista: ").append(nombreArtista != null ? nombreArtista : "Desconocido").append("</p>");

        vista.append("<p>Duración: ").append(musica.getDuracion()).append(" segundos</p>");

        // Fecha de lanzamiento
        vista.append("<p>Fecha de lanzamiento: ").append(musica.getFechaLanzamiento()).append("</p>");

        // Detalles específicos por tipo de música
        if (musica instanceof Cancion) {
            Cancion c = (Cancion) musica;
            vista.append("<p>🎬 Video: <a href='").append(c.getVideoURL()).append("' target='_blank'>Ver</a></p>");
            vista.append("<h4>Letra:</h4><ul class='letra-cancion'>");
            for (String verso : c.getLetra()) {
                vista.append("<li>").append(verso).append("</li>");
            }
            vista.append("</ul>");
        }

        if (musica instanceof Melodia) {
            Melodia m = (Melodia) musica;
            vista.append("<h4>Instrumentos:</h4><ul class='instrumentos-lista'>");
            for (String instrumento : m.getInstrumentos()) {
                vista.append("<li>").append(instrumento).append("</li>");
            }
            vista.append("</ul>");
            vista.append("<p>Nivel de relajación: ").append(m.getNivelRelajacion()).append("/10</p>");
        }

        return vista.toString();
    }

    private String obtenerNombreArtista(int idArtista) {
        String nombre = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conector_BD.conectar();
            String sql = "SELECT nombreArtistico FROM artista WHERE idArtista = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idArtista);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombreArtistico");
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al obtener el nombre del artista: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error cerrando conexión: " + e.getMessage());
            }
        }

        return nombre;
    }
}