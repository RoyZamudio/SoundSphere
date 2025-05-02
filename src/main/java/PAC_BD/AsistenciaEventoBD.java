package PAC_BD;

import java.sql.*;

public class AsistenciaEventoBD {

    public boolean confirmarAsistencia(int idEvento, int idOyente) {
        String sqlSelect = "SELECT estado FROM AsistenciaEvento WHERE idEvento=? AND idOyente=?";
        String sqlUpdate = "UPDATE AsistenciaEvento SET estado='confirmado' WHERE idEvento=? AND idOyente=?";
        String sqlInsert = "INSERT INTO AsistenciaEvento (idEvento, idOyente, estado) VALUES (?, ?, 'confirmado')";

        try (Connection conn = Conector_BD.conectar()) {
            // Verificar si ya existe la asistencia
            try (PreparedStatement select = conn.prepareStatement(sqlSelect)) {
                select.setInt(1, idEvento);
                select.setInt(2, idOyente);
                ResultSet rs = select.executeQuery();

                if (rs.next()) {

                    try (PreparedStatement update = conn.prepareStatement(sqlUpdate)) {
                        update.setInt(1, idEvento);
                        update.setInt(2, idOyente);
                        return update.executeUpdate() > 0;
                    }
                } else {

                    try (PreparedStatement insert = conn.prepareStatement(sqlInsert)) {
                        insert.setInt(1, idEvento);
                        insert.setInt(2, idOyente);
                        return insert.executeUpdate() > 0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean cancelarAsistencia(int idEvento, int idOyente) {
        String sql = "UPDATE AsistenciaEvento SET estado='cancelado' WHERE idEvento=? AND idOyente=?";
        try (Connection conn = Conector_BD.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEvento);
            ps.setInt(2, idOyente);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
