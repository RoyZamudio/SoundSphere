package PAC_USUARIOS_Y_CUENTAS.MODELO;

import PAC_BD.Conector_BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor_Cuentas {
    private Connection conectorBD;

    public Gestor_Cuentas() {
        try {
            conectorBD = Conector_BD.conectar();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }
    }

    public boolean iniciarSesion(String correo, String contrasena) {
        String sql = "SELECT * FROM perfil WHERE correo = ? AND contrasena = ?";

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ConstructorCuentas cuenta = instanciarObjetoCuentas(correo, contrasena);

                if (cuenta instanceof CuentaOyente) {
                    CuentaOyente cuentaOyente = (CuentaOyente) cuenta;
                    instanciaCuentaOyente(cuentaOyente);
                    cuentaOyente.mostrarDetalles();

                } else if (cuenta instanceof CuentaAdministrador) {
                    CuentaAdministrador cuentaAdministrador = (CuentaAdministrador) cuenta;
                    instanciaCuentaAdministrador(cuentaAdministrador);
                    cuentaAdministrador.mostrarDetalles();

                } else if (cuenta instanceof CuentaArtista) {
                    CuentaArtista cuentaArtista = (CuentaArtista) cuenta;
                    instanciaCuentaArtista(cuentaArtista);
                    cuentaArtista.mostrarDetalles();
                }

                SesionCuenta.setCuentaActual(cuenta);

                return true;
            } else {
                System.out.println("Credenciales incorrectas.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
            return false;
        }
    }

    public ConstructorCuentas instanciarObjetoCuentas(String correo, String contrasena) {
        String sql = "SELECT idPerfil, nombreUsuario, imagenPerfil, fechaCreacion, tipoCuenta FROM perfil WHERE correo = ? AND contrasena = ?";

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("No se encontró un usuario.");
                return null;
            }

            int idPerfil = rs.getInt("idPerfil");
            String nombreUsuario = rs.getString("nombreUsuario");
            String imagenPerfil = rs.getString("imagenPerfil");
            String fechaCreacion = rs.getString("fechaCreacion");
            String tipoCuenta = rs.getString("tipoCuenta");

            ConstructorCuentas cuenta = PedidoCuenta.obtenerCuenta(tipoCuenta);
            Perfil perfil = new Perfil(idPerfil, nombreUsuario, correo, contrasena, imagenPerfil, fechaCreacion, tipoCuenta);
            cuenta.setCuenta(perfil);

            System.out.println("Se ha creado una cuenta de tipo: " + tipoCuenta);
            return cuenta;

        } catch (SQLException e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
            return null;
        }
    }

    public void instanciaCuentaArtista(CuentaArtista cuenta) {
        Perfil perfil = cuenta.getCuenta();
        int idPerfil = perfil.getIdUsuario();

        String sql = "SELECT a.nombreArtistico, a.redesSociales, a.oyentesMensuales " +
                "FROM artista a " +
                "JOIN perfil p ON a.idArtista = p.idPerfil " +
                "WHERE p.idPerfil = ?";

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuenta.setNombreArtista(rs.getString("nombreArtistico"));
                cuenta.setRedesSociales(rs.getString("redesSociales"));
                cuenta.setOyentesMensuales(rs.getInt("oyentesMensuales"));
            } else {
                System.out.println("No se obtuvieron datos del artista.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
        }
    }

    public void instanciaCuentaAdministrador(CuentaAdministrador cuenta) {
        Perfil perfil = cuenta.getCuenta();
        int idPerfil = perfil.getIdUsuario();

        String sql = "SELECT a.nivelAcceso " +
                "FROM administrador a " +
                "JOIN perfil p ON a.idAdmin = p.idPerfil " +
                "WHERE p.idPerfil = ?";

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuenta.setNivelAcceso(rs.getInt("nivelAcceso"));
            } else {
                System.out.println("No se obtuvieron datos del administrador.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
        }
    }

    public void instanciaCuentaOyente(CuentaOyente cuenta) {
        Perfil perfil = cuenta.getCuenta();
        int idPerfil = perfil.getIdUsuario();

        String sql = "SELECT o.esPremium, o.puntosRecompensa " +
                "FROM oyente o " +
                "JOIN perfil p ON o.idOyente = p.idPerfil " +
                "WHERE p.idPerfil = ?";

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idPerfil);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuenta.setEsPremium(rs.getInt("esPremium"));
                cuenta.setPuntosRecompensa(rs.getInt("puntosRecompensa"));
            } else {
                System.out.println("No se obtuvieron datos del oyente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos: " + e.getMessage());
        }
    }

    public boolean registrarUsuario(String nombreUsuario, String correo, String contrasena, boolean esArtista) {
        String sql = "INSERT INTO Perfil (nombreUsuario, correo, contrasena, imagenPerfil, tipoCuenta) VALUES (?, ?, ?, ?, ?)";
        String tipoCuenta = esArtista ? "artista" : "basico";
        String imagenPerfil = null;

        try (Connection conexion = Conector_BD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, correo);
            ps.setString(3, contrasena);
            ps.setString(4, imagenPerfil);
            ps.setString(5, tipoCuenta);

            int confirmacion = ps.executeUpdate();
            return confirmacion > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
}
