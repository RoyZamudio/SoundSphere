package PAC_STREAMING.MODELO;

import PAC_BD.Conector_BD;

import java.sql.*;

public class ValidadorUsuario {
    public static String obtenerTipoUsuario(int idPerfil) {
        String tipo = "";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = Conector_BD.conectar();
            stmt = con.prepareStatement("SELECT tipoCuenta FROM perfil WHERE idPerfil = ?");
            stmt.setInt(1, idPerfil);
            rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = rs.getString("tipoCuenta");
                System.out.println("🎯 Tipo de usuario encontrado: " + tipo);
            } else {
                System.out.println("⚠️ No se encontró ningún perfil con ID: " + idPerfil);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error SQL: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return tipo;
    }

    public static boolean esPremium(int idPerfil) {
        return "premium".equalsIgnoreCase(obtenerTipoUsuario(idPerfil));
    }

    public static boolean esArtista(int idPerfil) {
        return "artista".equalsIgnoreCase(obtenerTipoUsuario(idPerfil));
    }
}
