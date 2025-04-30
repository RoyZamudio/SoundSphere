package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Melodia extends Musica {
    private int idMelodia;
    private int nivelRelajacion;
    private List<String> instrumentos; // Aunque no está en BD aún

    // Constructor normal
    public Melodia(int idCancion, int idArtista, String titulo, int duracion, int tiempoActual,
                   int numReproducciones, String fechaLanzamiento, byte[] imagen, byte[]audio, List<String> instrumentos, int nivelRelajacion) {
        super(idCancion, idArtista, titulo, duracion, tiempoActual, numReproducciones, fechaLanzamiento, imagen, audio);
        this.instrumentos = new ArrayList<>(instrumentos);
        this.nivelRelajacion = nivelRelajacion;
        this.setTipo("Melodia");
    }

    // Constructor desde ResultSet (BD)
    public Melodia(ResultSet rs) throws SQLException {
        super(
                rs.getInt("idCancion"),
                rs.getInt("idArtista"),
                rs.getString("titulo"),
                rs.getTime("duracion").toLocalTime().toSecondOfDay(),
                0, // tiempoActual siempre inicia en 0
                rs.getInt("numReproducciones"),
                rs.getDate("fechaLanzamiento").toString(),
                rs.getBytes("imagen"),
                rs.getBytes("audio")
        );
        this.idMelodia = rs.getInt("idMelodia");
        this.nivelRelajacion = rs.getInt("nivelRelajamiento"); // cuidado con el nombre en BD, que sea correcto
        this.instrumentos = new ArrayList<>();
        this.setTipo("Melodia");
    }

    public int getIdMelodia() {
        return idMelodia;
    }

    public void setIdMelodia(int idMelodia) {
        this.idMelodia = idMelodia;
    }

    public int getNivelRelajacion() {
        return nivelRelajacion;
    }

    public void setNivelRelajacion(int nivelRelajacion) {
        this.nivelRelajacion = nivelRelajacion;
    }

    public List<String> getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(List<String> instrumentos) {
        this.instrumentos = instrumentos;
    }

    public void agregarInstrumento(String instrumento) {
        this.instrumentos.add(instrumento);
    }
}
