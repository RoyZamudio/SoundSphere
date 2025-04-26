package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Playlist extends ListaR {
    private int idOyente;
    private String descripcion;
    private LocalDateTime fechaCreacion;

    public Playlist(String nombre) {
        super(nombre);
    }

    public Playlist(String nombre, String descripcion, int idOyente, LocalDateTime fechaCreacion) {
        super(nombre);
        this.descripcion = descripcion;
        this.idOyente = idOyente;
        this.fechaCreacion = fechaCreacion;
    }

    public Playlist(ResultSet rs) throws SQLException {
        super(rs.getString("nombre"));
        this.id = rs.getInt("idPlaylist");
        this.idOyente = rs.getInt("idOyente");
        this.descripcion = rs.getString("descripcion");
        this.fechaCreacion = rs.getTimestamp("fechaCreacion").toLocalDateTime();
    }

    public int getIdOyente() {
        return idOyente;
    }

    public void setIdOyente(int idOyente) {
        this.idOyente = idOyente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}