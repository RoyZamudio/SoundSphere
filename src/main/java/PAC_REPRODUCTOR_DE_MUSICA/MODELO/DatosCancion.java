package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

public class DatosCancion {

    public String generarVista(Musica musica) {
        if (musica == null) return "❌ No hay música en reproducción.";

        StringBuilder vista = new StringBuilder();
        vista.append("<h2>").append(musica.getTitulo()).append("</h2>");
        vista.append("<p>🎤 Artista: ").append(musica.getArtista()).append("</p>");
        vista.append("<p>⏱️ Duración: ").append(musica.getDuracion()).append(" segundos</p>");
        vista.append("<p>▶️ Tiempo actual: ").append(musica.getTiempoActual()).append(" segundos</p>");

        if (musica instanceof Cancion) {
            Cancion c = (Cancion) musica;
            vista.append("<p>🎬 Video: <a href='").append(c.getVideoURL()).append("'>Ver</a></p>");
            vista.append("<h4>Letra:</h4><ul>");
            for (String verso : c.getLetra()) {
                vista.append("<li>").append(verso).append("</li>");
            }
            vista.append("</ul>");
        }

        if (musica instanceof Melodia) {
            Melodia m = (Melodia) musica;
            vista.append("<h4>🎼 Instrumentos:</h4><ul>");
            for (String instrumento : m.getInstrumentos()) {
                vista.append("<li>").append(instrumento).append("</li>");
            }
            vista.append("</ul>");
            vista.append("<p>🧘 Nivel de relajación: ").append(m.getNivelRelajacion()).append("/10</p>");
        }

        return vista.toString();
    }
}
