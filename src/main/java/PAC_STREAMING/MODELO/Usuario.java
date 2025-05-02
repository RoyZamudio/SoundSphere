package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public class Usuario {
    private int idPerfil;
    private String nombre;
    private boolean accesoPermitido;

    public Usuario(int idPerfil, String nombre, boolean accesoPermitido) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.accesoPermitido = accesoPermitido;
    }

    public boolean tienePermiso() {
        return accesoPermitido;
    }

    public int getId() {
        return idPerfil;
    }

    public void solicitarStreaming(Streaming servicioStreaming, HttpServletRequest request, HttpServletResponse response) {
        servicioStreaming.verTransmision(request, response);
    }
}
