package PAC_STREAMING.MODELO;

import jakarta.servlet.http.*;

public interface Streaming {
    void verTransmision(HttpServletRequest request, HttpServletResponse response);
}
