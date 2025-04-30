package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cancion extends Musica {
    private List<String> letra;
    private String videoURL;

    public Cancion(int idCancion, int idArtista, String titulo, int duracion, int tiempoActual,
                   int numReproducciones, String fechaLanzamiento, byte[] imagen, byte[] audio, List<String> letra, String videoURL) {
        super(idCancion, idArtista, titulo, duracion, tiempoActual, numReproducciones, fechaLanzamiento, audio, imagen);
        this.letra = letra;
        this.videoURL = videoURL;
        this.setTipo("Cancion");
    }

    // Constructor desde ResultSet (para consultas SQL)
    public Cancion(ResultSet rs) throws SQLException {
        super(
                rs.getInt("idCancion"),
                rs.getInt("idArtista"),
                rs.getString("titulo"),
                rs.getTime("duracion").toLocalTime().toSecondOfDay(), // Convierte TIME a int (segundos)
                0, // tiempoActual inicia en 0
                rs.getInt("numReproducciones"),
                rs.getDate("fechaLanzamiento").toString(),
                rs.getBytes("imagen"),
                rs.getBytes("audio")
        );

        String letraSQL = rs.getString("letra");
        this.letra = letraSQL != null ? Arrays.asList(letraSQL.split("\n")) : new ArrayList<>();
        this.videoURL = rs.getString("enlaceVideo");
        this.setTipo("Cancion");
    }

    public List<String> getLetra() {
        return letra;
    }

    public void setLetra(List<String> letra) {
        this.letra = letra;
    }

    public void agregarVerso(String verso) {
        this.letra.add(verso);
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}