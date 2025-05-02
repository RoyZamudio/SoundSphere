package PAC_EVENTOS.MODELO;

public class Evento {
    private int idEvento;
    private int idArtista;
    private String nombre;
    private String tipoEvento;
    private String descripcion;
    private String fechaEvento;
    private String horaEvento;
    private String ubicacion;
    private int capacidad;
    private String multimedia;

    // Getters y Setters
    public int getIdEvento() { return idEvento; }
    public void setIdEvento(int idEvento)
    { this.idEvento = idEvento; }

    public int getIdArtista() { return idArtista; }
    public void setIdArtista(int idArtista)
    { this.idArtista = idArtista; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre)
    { this.nombre = nombre; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento)
    { this.tipoEvento = tipoEvento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion)
    { this.descripcion = descripcion; }

    public String getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(String fechaEvento)
    { this.fechaEvento = fechaEvento; }

    public String getHoraEvento() { return horaEvento; }
    public void setHoraEvento(String horaEvento)
    { this.horaEvento = horaEvento; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion)
    { this.ubicacion = ubicacion; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad)
    { this.capacidad = capacidad; }

    public String getMultimedia() { return multimedia; }
    public void setMultimedia(String multimedia)
    { this.multimedia = multimedia; }
}