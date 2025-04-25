package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.Musica;

public class ReproductorMusical {

    private boolean reproduciendo;

    public ReproductorMusical() {
        this.reproduciendo = false;
    }

    public String reproducir(Musica musica) {
        if (musica == null) return "❌ No hay música cargada.";
        reproduciendo = true;
        return "▶️ Reproduciendo: " + musica.getTitulo() + " - " + musica.getArtista();
    }

    public void pausar() {
        reproduciendo = false;
        System.out.println("⏸️ Música pausada.");
    }

    public void adelantar(Musica musica, int segundos) {
        if (!reproduciendo) {
            System.out.println("⏩ No se puede adelantar: la música no está reproduciéndose.");
            return;
        }
        int nuevoTiempo = Math.min(musica.getTiempoActual() + segundos, musica.getDuracion());
        musica.setTiempoActual(nuevoTiempo);
        System.out.println("⏩ Adelantado " + segundos + " segundos.");
    }

    public void retroceder(Musica musica, int segundos) {
        if (!reproduciendo) {
            System.out.println("⏪ No se puede retroceder: la música no está reproduciéndose.");
            return;
        }
        int nuevoTiempo = Math.max(musica.getTiempoActual() - segundos, 0);
        musica.setTiempoActual(nuevoTiempo);
        System.out.println("⏪ Retrocedido " + segundos + " segundos.");
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }
}
