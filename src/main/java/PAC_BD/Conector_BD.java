package PAC_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector_BD {

    private static final String URL = "jdbc:mysql://localhost:3306/SoundSphere?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "09082002"; //

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}