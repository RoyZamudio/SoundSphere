package PAC_CANALES.MODELO;

public class Canal {
    private int id;
    private int idArtista;
    private String nombre;
    private String descripcion;

    public Canal() {}

    public Canal(int id, int idArtista, String nombre, String descripcion) {
        this.id = id;
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}