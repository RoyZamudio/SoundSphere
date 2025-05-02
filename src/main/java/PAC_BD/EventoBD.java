package PAC_BD;

import PAC_EVENTOS.MODELO.Evento;
import java.sql.*;
import java.util.*;

public class EventoBD {
    public boolean create(Evento e) {
        String sql = "INSERT INTO Evento (idArtista, nombre, tipoEvento, descripcion, fechaEvento, horaEvento, ubicacion, capacidad, multimedia) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conector_BD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("Creando evento con idArtista: " + e.getIdArtista());
            ps.setInt(1, e.getIdArtista());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getTipoEvento());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getFechaEvento());
            ps.setString(6, e.getHoraEvento());
            ps.setString(7, e.getUbicacion());
            ps.setInt(8, e.getCapacidad());
            ps.setString(9, e.getMultimedia());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("ERROR AL GUARDAR EVENTO:");
            ex.printStackTrace();
            return false;
        }
    }

    public List<Evento> findAll() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evento";
        try (Connection conn = Conector_BD.conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Evento e = new Evento();
                e.setIdEvento(rs.getInt("idEvento"));
                e.setIdArtista(rs.getInt("idArtista"));
                e.setNombre(rs.getString("nombre"));
                e.setTipoEvento(rs.getString("tipoEvento"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setFechaEvento(rs.getString("fechaEvento"));
                e.setHoraEvento(rs.getString("horaEvento"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setCapacidad(rs.getInt("capacidad"));
                e.setMultimedia(rs.getString("multimedia"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public Evento findById(int idEvento) {
        String sql = "SELECT * FROM Evento WHERE idEvento = ?";
        try (Connection conn = Conector_BD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Evento e = new Evento();
                e.setIdEvento(rs.getInt("idEvento"));
                e.setIdArtista(rs.getInt("idArtista"));
                e.setNombre(rs.getString("nombre"));
                e.setTipoEvento(rs.getString("tipoEvento"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setFechaEvento(rs.getString("fechaEvento"));
                e.setHoraEvento(rs.getString("horaEvento"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setCapacidad(rs.getInt("capacidad"));
                e.setMultimedia(rs.getString("multimedia"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Evento findByNombre(String nombre) {
        String sql = "SELECT * FROM Evento WHERE nombre = ?";
        try (Connection conn = Conector_BD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Evento e = new Evento();
                e.setIdEvento(rs.getInt("idEvento"));
                e.setIdArtista(rs.getInt("idArtista"));
                e.setNombre(rs.getString("nombre"));
                e.setTipoEvento(rs.getString("tipoEvento"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setFechaEvento(rs.getString("fechaEvento"));
                e.setHoraEvento(rs.getString("horaEvento"));
                e.setUbicacion(rs.getString("ubicacion"));
                e.setCapacidad(rs.getInt("capacidad"));
                e.setMultimedia(rs.getString("multimedia"));
                return e;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean update(Evento e) {
        String sql = "UPDATE Evento SET idArtista = ?, nombre = ?, tipoEvento = ?, descripcion = ?, fechaEvento = ?, horaEvento = ?, ubicacion = ?, capacidad = ?, multimedia = ? WHERE idEvento = ?";
        try (Connection conn = Conector_BD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getIdArtista());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getTipoEvento());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getFechaEvento());
            ps.setString(6, e.getHoraEvento());
            ps.setString(7, e.getUbicacion());
            ps.setInt(8, e.getCapacidad());
            ps.setString(9, e.getMultimedia());
            ps.setInt(10, e.getIdEvento());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(int idEvento) {
        String deleteAsistencias = "DELETE FROM AsistenciaEvento WHERE idEvento = ?";
        String deleteEvento = "DELETE FROM Evento WHERE idEvento = ?";

        try (Connection conn = Conector_BD.conectar()) {
            conn.setAutoCommit(false); //
            try (PreparedStatement ps1 = conn.prepareStatement(deleteAsistencias);
                 PreparedStatement ps2 = conn.prepareStatement(deleteEvento)) {

                ps1.setInt(1, idEvento);
                ps1.executeUpdate();

                ps2.setInt(1, idEvento);
                int filas = ps2.executeUpdate();

                conn.commit(); //
                return filas > 0;

            } catch (SQLException e) {
                conn.rollback(); //
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}