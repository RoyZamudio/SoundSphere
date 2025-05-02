package PAC_CANALES.MODELO;

public class MensajeCanal {
    private int idMensaje;
    private int idCanal;
    private String tipoMensaje;  // texto, nota_voz, imagen, video
    private String contenido;
    private String fecha;

    // 🆕 Reacciones
    private int reacciones;

    // Getters y Setters

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getReacciones() {
        return reacciones;
    }

    public void setReacciones(int reacciones) {
        this.reacciones = reacciones;
    }
}