package PAC_BD;

import PAC_CANALES.MODELO.Canal;
import PAC_CANALES.MODELO.MensajeCanal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Canal_BD {

    public static boolean insertar(Canal canal) {
        String sql = "INSERT INTO Canal (idArtista, nombre, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, canal.getIdArtista());
            ps.setString(2, canal.getNombre());
            ps.setString(3, canal.getDescripcion());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizar(Canal canal) {
        String sql = "UPDATE Canal SET nombre = ?, descripcion = ? WHERE idCanal = ?";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, canal.getNombre());
            ps.setString(2, canal.getDescripcion());
            ps.setInt(3, canal.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Canal buscarPorId(int id) {
        String sql = "SELECT * FROM Canal WHERE idCanal = ?";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Canal canal = new Canal();
                canal.setId(rs.getInt("idCanal"));
                canal.setIdArtista(rs.getInt("idArtista"));
                canal.setNombre(rs.getString("nombre"));
                canal.setDescripcion(rs.getString("descripcion"));
                return canal;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Canal> obtenerTodos() {
        List<Canal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Canal";

        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Canal canal = new Canal();
                canal.setId(rs.getInt("idCanal"));
                canal.setIdArtista(rs.getInt("idArtista"));
                canal.setNombre(rs.getString("nombre"));
                canal.setDescripcion(rs.getString("descripcion"));
                lista.add(canal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean insertarMensaje(MensajeCanal mensaje) {
        String sql = "INSERT INTO Mensaje (idCanal, tipoMensaje, contenido) VALUES (?, ?, ?)";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, mensaje.getIdCanal());
            ps.setString(2, mensaje.getTipoMensaje());
            ps.setString(3, mensaje.getContenido());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static List<MensajeCanal> obtenerMensajes(int idCanal) {
        List<MensajeCanal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Mensaje WHERE idCanal = ? ORDER BY fechaEnvio DESC";

        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCanal);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MensajeCanal m = new MensajeCanal();
                m.setIdMensaje(rs.getInt("idMensaje"));
                m.setIdCanal(rs.getInt("idCanal"));
                m.setTipoMensaje(rs.getString("tipoMensaje"));
                m.setContenido(rs.getString("contenido"));
                m.setFecha(rs.getString("fechaEnvio"));
                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static boolean reaccionarMensaje(int idMensaje, int idOyente) {
        String sql = "INSERT INTO ReaccionMensaje (idMensaje, idOyente) VALUES (?, ?)";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            ps.setInt(2, idOyente);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static int obtenerIdCanalDesdeMensaje(int idMensaje) {
        String sql = "SELECT idCanal FROM Mensaje WHERE idMensaje = ?";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMensaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idCanal");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int contarReacciones(int idMensaje) {
        String sql = "SELECT COUNT(*) AS total FROM ReaccionMensaje WHERE idMensaje = ?";
        try (Connection conn = Conector_BD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMensaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}