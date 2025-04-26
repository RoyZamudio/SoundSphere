package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import PAC_BD.Conector_BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricaConcretaPlaylist extends FabricaAbstractaListaR {

    private int seleccion;

    public FabricaConcretaPlaylist(int seleccion) {
        this.seleccion = seleccion;
    }

    @Override
    public ListaR crearLista() {
        Playlist playlist = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conector_BD.conectar();
            String query = "SELECT * FROM playlist WHERE idPlaylist = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, seleccion);
            rs = stmt.executeQuery();

            if (rs.next()) {
                playlist = new Playlist(rs); // Usa directamente el constructor que recibe un ResultSet
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al cargar playlist desde la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error cerrando la conexión: " + e.getMessage());
            }
        }

        return playlist;
    }
}