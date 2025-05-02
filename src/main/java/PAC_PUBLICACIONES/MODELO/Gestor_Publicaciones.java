package PAC_PUBLICACIONES.MODELO;

import PAC_BD.Conector_BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Gestor_Publicaciones {
    private List<Abstraccion_publicacion> publicaciones = new ArrayList<>();

    public void crearPublicacion(String tipo, int idPerfil, String contenido, String imagen) {
        try (Connection con = Conector_BD.conectar()) {
            String sql = "INSERT INTO publicacion (idPerfil, contenidoTexto, fechaPublicacion) VALUES (?, ?, NOW())";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idPerfil);
            stmt.setString(2, contenido);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                Implementacion_publicacion imp = tipo.equals("multimedia") ?
                        new Ipublicacion_multimedia(imagen) :
                        new Ipublicacion_texto(contenido);
                Abstraccion_publicacion pub = tipo.equals("multimedia") ?
                        new AbstraccionPublicacion_multimedia(imp) :
                        new AbstraccionPublicacion_texto(imp);
                pub.setIdPublicacion(id);
                pub.setIdPerfil(idPerfil);
                pub.setFechaPublicacion(obtenerFechaPublicacion(id));
                pub.setNombreUsuario(obtenerNombrePerfil(idPerfil));
                publicaciones.add(pub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String obtenerFechaPublicacion(int idPublicacion) {
        try (Connection con = Conector_BD.conectar()) {
            String sql = "SELECT fechaPublicacion FROM publicacion WHERE idPublicacion = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPublicacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("fechaPublicacion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String obtenerNombrePerfil(int idPerfil) {
        try (Connection con = Conector_BD.conectar()) {
            String sql = "SELECT nombrePerfil FROM perfil WHERE idPerfil = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPerfil);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getString("nombrePerfil");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario Desconocido";
    }

    public void eliminarPublicacion(int idPublicacion) {
        try (Connection con = Conector_BD.conectar()) {
            String sql = "DELETE FROM publicacion WHERE idPublicacion = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPublicacion);
            stmt.executeUpdate();
            publicaciones.removeIf(p -> p.getIdPublicacion() == idPublicacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Abstraccion_publicacion> obtenerPublicaciones() {
        publicaciones.clear();
        try (Connection con = Conector_BD.conectar()) {
            String sql = "SELECT p.idPublicacion, p.idPerfil, p.contenidoTexto, p.fechaPublicacion, pf.nombrePerfil " +
                    "FROM publicacion p JOIN perfil pf ON p.idPerfil = pf.idPerfil ORDER BY p.fechaPublicacion DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int idPublicacion = rs.getInt("idPublicacion");
                int idPerfil = rs.getInt("idPerfil");
                String contenido = rs.getString("contenidoTexto");
                String fecha = rs.getString("fechaPublicacion");
                String nombre = rs.getString("nombrePerfil");

                Implementacion_publicacion imp = (contenido != null && !contenido.isEmpty()) ?
                        new Ipublicacion_texto(contenido) :
                        new Ipublicacion_multimedia("imagen_no_disponible.png");

                Abstraccion_publicacion pub = (contenido != null && !contenido.isEmpty()) ?
                        new AbstraccionPublicacion_texto(imp) :
                        new AbstraccionPublicacion_multimedia(imp);

                pub.setIdPublicacion(idPublicacion);
                pub.setIdPerfil(idPerfil);
                pub.setFechaPublicacion(fecha);
                pub.setNombreUsuario(nombre);
                publicaciones.add(pub);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicaciones;
    }

    public void cargarPublicacionesDesdeBaseDeDatos() {
        obtenerPublicaciones();
    }
}