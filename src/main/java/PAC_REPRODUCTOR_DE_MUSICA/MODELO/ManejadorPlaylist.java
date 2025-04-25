package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.*;

public class ManejadorPlaylist {

    // Retorna una vista tipo HTML con los contenidos de la lista
    public String obtenerResumen(ListaR lista) {
        StringBuilder sb = new StringBuilder();

        sb.append("📀 Lista: ").append(lista.getNombre()).append("<br/>");

        if (lista.getContenido().isEmpty()) {
            sb.append("Lista vacía.");
        } else {
            for (Musica m : lista.getContenido()) {
                sb.append("🎵 ").append(m.getTitulo())
                        .append(" - ").append(m.getArtista())
                        .append(" (").append(m.getDuracion()).append("s)<br/>");
            }
        }

        return sb.toString();
    }

    // Agrega una canción o melodía a la lista
    public void agregarMusica(ListaR lista, Musica musica) {
        lista.agregarMusica(musica);
    }

    // Elimina una pista de la lista
    public void eliminarMusica(ListaR lista, Musica musica) {
        lista.eliminarMusica(musica);
    }

    // Elimina todo el contenido de la lista
    public void vaciarLista(ListaR lista) {
        lista.getContenido().clear();
    }

    // Devuelve cuántas canciones o melodías hay en la lista
    public int getCantidadCanciones(ListaR lista) {
        return lista.getContenido().size();
    }
}