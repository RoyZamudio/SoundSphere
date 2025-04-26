package PAC_REPRODUCTOR_DE_MUSICA.MODELO;

import PAC_BD.Conector_BD;
import PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.Musica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReproductorMusical {

    private boolean reproduciendo;

    public ReproductorMusical() {
        this.reproduciendo = false;
    }

    public String reproducir(Musica musica) {
        if (musica == null) return "❌ No hay música cargada.";
        reproduciendo = true;

        String nombreArtista = "Desconocido";
        if (musica.getIdArtista() > 0) {
            nombreArtista = obtenerNombreArtista(musica.getIdArtista());
        }

        return "▶️ Reproduciendo: " + musica.getTitulo() + " - " + nombreArtista;
    }

    private String obtenerNombreArtista(int idArtista) {
        String nombreArtista = "Desconocido";

        try (Connection conn = Conector_BD.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT nombreArtistico FROM artista WHERE idArtista = ?"
             )) {

            stmt.setInt(1, idArtista);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    nombreArtista = rs.getString("nombreArtistico");
                }
            }
        } catch (SQLException e) {
            System.err.println("⚠️ Error al obtener nombre del artista: " + e.getMessage());
        }

        return nombreArtista;
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