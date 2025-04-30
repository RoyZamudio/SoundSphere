package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import PAC_BD.Conector_BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricaConcretaMelodia extends FabricaAbstractaMusica {

    private int seleccion;

    public FabricaConcretaMelodia(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public Musica crearMusica() {
        Melodia melodia = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conector_BD.conectar();
            String query = "SELECT * FROM melodia WHERE idMelodia = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, seleccion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idMelodia = rs.getInt("idMelodia");
                int idCancion = rs.getInt("idCancion"); // porque hereda de Musica
                int idArtista = rs.getInt("idArtista");
                String titulo = rs.getString("titulo");
                int duracion = rs.getTime("duracion").toLocalTime().toSecondOfDay();
                int numReproducciones = rs.getInt("numReproducciones");
                String fecha = rs.getString("fechaLanzamiento");
                int nivelRelajacion = rs.getInt("nivelRelajamiento");

                // 👇 Obtener binarios
                byte[] imagen = rs.getBytes("imagenPortada");
                byte[] audio = rs.getBytes("archivoAudio");

                // Instrumentos no están en la tabla, se inicializa vacío
                List<String> instrumentos = new ArrayList<>();

                // 👇 Constructor actualizado con binarios
                melodia = new Melodia(
                        idCancion, idArtista, titulo, duracion, 0,
                        numReproducciones, fecha, imagen, audio,
                        instrumentos, nivelRelajacion
                );
                melodia.setIdMelodia(idMelodia);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar melodía desde la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }

        return melodia;
    }
}