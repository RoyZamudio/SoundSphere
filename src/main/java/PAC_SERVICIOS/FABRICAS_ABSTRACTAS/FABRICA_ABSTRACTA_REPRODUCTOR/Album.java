package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Album extends ListaR {
    private int idAlbum;
    private int idArtista;
    private String fechaLanzamiento;

    // Constructor desde ResultSet
    public Album(ResultSet rs, List<Musica> contenidoInicial) throws SQLException {
        super(
                rs.getString("titulo"),
                rs.getString("imagenPortada")
        );

        this.idAlbum = rs.getInt("idAlbum");
        this.idArtista = rs.getInt("idArtista");
        this.fechaLanzamiento = rs.getDate("fechaLanzamiento").toString();

        if (contenidoInicial == null || contenidoInicial.isEmpty()) {
            throw new IllegalArgumentException("Un álbum debe tener al menos una canción o melodía");
        }

        this.contenido.addAll(contenidoInicial);
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}