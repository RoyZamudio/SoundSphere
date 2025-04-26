package PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR;

public abstract class Musica {
    protected int idCancion;
    protected int idArtista;
    protected String titulo;
    protected int duracion; // En segundos
    protected int tiempoActual;
    protected int numReproducciones;
    protected String fechaLanzamiento;
    private String tipo;

    public Musica(int idCancion, int idArtista, String titulo, int duracion, int tiempoActual,
                  int numReproducciones, String fechaLanzamiento) {
        this.idCancion = idCancion;
        this.idArtista = idArtista;
        this.titulo = titulo;
        this.duracion = duracion;
        this.tiempoActual = tiempoActual;
        this.numReproducciones = numReproducciones;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(int tiempoActual) {
        this.tiempoActual = tiempoActual;
    }

    public int getNumReproducciones() {
        return numReproducciones;
    }

    public void setNumReproducciones(int numReproducciones) {
        this.numReproducciones = numReproducciones;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
