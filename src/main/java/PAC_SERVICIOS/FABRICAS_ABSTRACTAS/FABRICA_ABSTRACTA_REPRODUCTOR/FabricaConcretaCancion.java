package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import PAC_BD.Conector_BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaCancion extends FabricaAbstractaMusica {

    private int seleccion;

    public FabricaConcretaCancion(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public Musica crearMusica() {
        Cancion cancion = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conector_BD.conectar();
            String query = "SELECT * FROM cancion WHERE idCancion = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, seleccion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idCancion = rs.getInt("idCancion");
                int idArtista = rs.getInt("idArtista");
                String titulo = rs.getString("titulo");
                int duracion = rs.getTime("duracion").toLocalTime().toSecondOfDay();
                int numReproducciones = rs.getInt("numReproducciones");
                String fecha = rs.getString("fechaLanzamiento");
                String videoURL = rs.getString("enlaceVideo");

                // 👇 Obtener binarios
                byte[] imagen = rs.getBytes("imagenPortada");
                byte[] audio = rs.getBytes("archivoAudio");

                // Convertir la letra (TEXT) a lista de versos
                List<String> letra = new ArrayList<>();
                String texto = rs.getString("letra");
                if (texto != null) {
                    for (String verso : texto.split("\\n")) {
                        letra.add(verso.trim());
                    }
                }

                // 👇 Crear objeto Cancion con todo
                cancion = new Cancion(
                        idCancion, idArtista, titulo, duracion, 0,
                        numReproducciones, fecha, imagen, audio, letra, videoURL
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar canción desde la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }

        return cancion;
    }
}