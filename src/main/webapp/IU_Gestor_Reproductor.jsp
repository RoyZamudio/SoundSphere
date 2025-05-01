<%@ page import="PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.Musica" %>
<%@ page import="PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.Cancion" %>
<%@ page import="PAC_REPRODUCTOR_DE_MUSICA.MODELO.ReproductorMusical" %>
<%@ page import="java.util.List" %>
<%@ page import="PAC_SERVICIOS.FABRICAS_ABSTRACTAS.FABRICA_ABSTRACTA_REPRODUCTOR.Melodia" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>🎧 Reproductor de Música - SoundSphere</title>
    <link rel="stylesheet" type="text/css" href="estilos/reproductor.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

<div class="app-container">
    <!-- Barra superior -->
    <div class="top-nav-bar">
        <div class="title-area">
            <div class="logo-container">
                <img src="img/logo.png" alt="SoundSphere Logo" class="app-logo">
            </div>
            <h1>SoundSphere</h1>
        </div>

        <div class="search-container">
            <div class="search-input-wrapper">
                <input type="text" placeholder="Buscar canciones..." class="search-input">
                <i class="fas fa-search search-icon"></i>
            </div>
        </div>

        <div class="nav-buttons">
            <button class="nav-button active">
                <i class="fas fa-music"></i>
                <span>Reproductor</span>
            </button>
            <button class="nav-button">
                <i class="fas fa-broadcast-tower"></i>
                <span>Streaming</span>
            </button>
            <button class="nav-button">
                <i class="fas fa-users"></i>
                <span>Red Social</span>
            </button>
            <button class="nav-button">
                <i class="fas fa-user"></i>
                <span>Mi Cuenta</span>
            </button>

            <label class="dark-mode-toggle">
                <input type="checkbox" id="darkModeToggle">
                <div class="toggle-slider"></div>
            </label>
        </div>
    </div>

    <div class="main-content">
        <!-- Panel izquierdo (Playlists) -->
        <div class="left-panel">
            <div class="panel-header">
                <span>Playlists</span>
            </div>

            <div class="panel-content">
                <form method="post" action="reproductor">
                    <input type="hidden" name="accion" value="verGestorListas" />
                    <button type="submit" class="playlist-add-button">
                        <i class="fas fa-plus"></i> Nueva Playlist
                    </button>
                </form>

            </div>
        </div>

        <!-- Panel central (Contenido principal) -->
        <div class="center-panel">
            <div class="panel-header">
                <span>Reproductor de Música</span>
            </div>

            <div class="panel-content">
                <!-- Lista de canciones -->
                <div class="songs-list-container">
                    <h3 class="list-title">Todas las canciones</h3>

                    <div class="songs-list">
                        <%
                            List<Musica> listaPistas = (List<Musica>) request.getAttribute("listaPistas");
                            if (listaPistas != null && !listaPistas.isEmpty()) {
                                int index = 1;
                                for (Musica pista : listaPistas) {
                        %>
                        <%
                            // Check if this is the currently playing track
                            boolean isCurrentTrack = false;
                            Musica currentMusic = (Musica)request.getAttribute("musica");
                            if (currentMusic != null && currentMusic.getIdCancion() == pista.getIdCancion()) {
                                if ((currentMusic instanceof Cancion && pista instanceof Cancion) ||
                                        (currentMusic instanceof Melodia && pista instanceof Melodia)) {
                                    isCurrentTrack = true;
                                }
                            }

                            // Check if the track is currently playing
                            boolean isPlaying = false;
                            ReproductorMusical reproductor = (ReproductorMusical)request.getAttribute("reproductor");
                            if (reproductor != null && reproductor.isReproduciendo() && isCurrentTrack) {
                                isPlaying = true;
                            }
                        %>
                        <div class="song-item <%= isCurrentTrack ? "active" : "" %>" data-id="<%= pista.getIdCancion() %>">
                            <div class="song-number"><%= index %></div>
                            <div class="song-play-icon">
                                <i class="fas <%= isPlaying ? "fa-pause" : "fa-play" %>"></i>
                            </div>
                            <div class="song-info">
                                <div class="song-title"><%= pista.getTitulo() %></div>
                                <div class="song-artist"><%= (pista instanceof Cancion) ? "Canción" : "Melodía" %> - C<%= pista.getIdCancion() %></div>
                            </div>
                            <form method="post" action="reproductor" class="play-song-form">
                                <input type="hidden" name="seleccion" value="<%= pista.getIdCancion() %>" />
                                <input type="hidden" name="accion" value="<%= (pista instanceof Cancion) ? "crearCancion" : "crearMelodia" %>" />
                                <button type="submit" class="play-song-button">
                                    <i class="fas fa-play"></i>
                                </button>
                            </form>
                        </div>
                        <%
                                index++;
                            }
                        } else {
                        %>
                        <div class="no-songs-message">
                            <p>No hay canciones disponibles</p>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>

                <div class="song-info-display">
                    <%-- Si hay datos de la pista, mostrarlos aquí --%>
                    <%
                        String mensaje = (String) request.getAttribute("mensaje");
                        if (mensaje != null && !mensaje.isEmpty()) {
                    %>
                    <div class="message">
                        <p><strong><%= mensaje %></strong></p>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>

        <!-- Panel derecho (Información) -->
        <div class="right-panel">
            <div class="panel-header">
                <span>Información</span>
            </div>

            <div class="panel-content">
                <div class="song-info-container">
                    <!-- Song Cover / Image -->
                    <div class="song-cover-large">
                        <%
                            Musica currentMusic = (Musica)request.getAttribute("musica");
                            if (currentMusic != null && currentMusic.getImagen() != null && currentMusic.getImagen().length > 0) {
                                // Debug the image bytes
                                byte[] imageData = currentMusic.getImagen();
                                System.out.println("Image data first bytes: " +
                                        (imageData.length > 0 ? String.format("%02X", imageData[0]) : "") + " " +
                                        (imageData.length > 1 ? String.format("%02X", imageData[1]) : "") + " " +
                                        (imageData.length > 2 ? String.format("%02X", imageData[2]) : "") + " " +
                                        (imageData.length > 3 ? String.format("%02X", imageData[3]) : ""));

                                // Use image/* MIME type to let browser determine format
                                String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
                        %>
                        <img src="data:image/*;base64,<%= base64Image %>" alt="<%= currentMusic.getTitulo() %>" class="song-cover-image">
                        <% } else { %>
                        <i class="fas fa-music"></i>
                        <% } %>
                    </div>
                    <%-- La información de la canción se actualiza dinámicamente --%>
                    <%
                        String datosMusica = (String) request.getAttribute("datosMusica");
                        if (datosMusica != null && !datosMusica.isEmpty()) {
                    %>
                    <div class="song-details">
                        <%= datosMusica %>
                    </div>
                    <!-- Audio player -->
                    <%
                        if (currentMusic != null && currentMusic.getAudio() != null && currentMusic.getAudio().length > 0) {
                            String base64Audio = java.util.Base64.getEncoder().encodeToString(currentMusic.getAudio());
                    %>
                    <div class="audio-player-container">
                        <audio controls autoplay id="audioPlayer">
                            <source src="data:audio/mpeg;base64,<%= base64Audio %>" type="audio/mpeg">
                            Your browser does not support the audio element.
                        </audio>
                    </div>
                    <% } %>
                    <%
                    } else {
                    %>
                    <p class="no-song-selected">No hay canción seleccionada</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>

    <!-- Controles de reproducción en la parte inferior -->
    <div class="playback-controls">
        <div class="player-controls">
            <form method="post" action="reproductor" class="control-form">
                <input type="hidden" name="accion" value="retroceder" />
                <button type="submit" class="control-button">
                    <i class="fas fa-step-backward"></i>
                </button>
            </form>

            <%-- Toggle play/pause button --%>
            <div class="play-pause-toggle">
                <%
                    // Determinar si el reproductor está reproduciendo actualmente
                    boolean isReproduciendo = false;
                    ReproductorMusical reproductor = (ReproductorMusical)request.getAttribute("reproductor");
                    if (reproductor != null) {
                        isReproduciendo = reproductor.isReproduciendo();
                    }
                %>
                <form method="post" action="reproductor" id="playForm">
                    <input type="hidden" name="accion" value="play" />
                    <button type="submit" class="play-button" id="playButton" <%= isReproduciendo ? "style=\"display: none;\"" : "" %>>
                        <i class="fas fa-play"></i>
                    </button>
                </form>

                <form method="post" action="reproductor" id="pauseForm">
                    <input type="hidden" name="accion" value="pause" />
                    <button type="submit" class="pause-button" id="pauseButton" <%= isReproduciendo ? "" : "style=\"display: none;\"" %>>
                        <i class="fas fa-pause"></i>
                    </button>
                </form>
            </div>

            <form method="post" action="reproductor" class="control-form">
                <input type="hidden" name="accion" value="adelantar" />
                <button type="submit" class="control-button">
                    <i class="fas fa-step-forward"></i>
                </button>
            </form>
        </div>

        <div class="progress-control">
            <span class="time-current">--:--</span>
            <div class="progress-bar">
                <div class="progress-filled"></div>
            </div>
            <span class="time-total">--:--</span>
        </div>

        <!-- Display currently playing track info -->
        <div class="now-playing-info">
            <%
                // Show currently playing track info
                if (currentMusic != null) {
                    // Simple artist name retrieval (could be enhanced to call DB)
                    String tipoMusica = (currentMusic instanceof Cancion) ? "Canción" : "Melodía";
                    out.print("Reproduciendo: " + currentMusic.getTitulo() + " (" + tipoMusica + ")");
                } else {
                    out.print("No hay canción seleccionada");
                }
            %>
        </div>
    </div>
</div>

<script>
    // Script para cambiar entre modo claro/oscuro
    document.getElementById('darkModeToggle').addEventListener('change', function() {
        document.body.classList.toggle('light-mode');
    });

    // Formato de tiempo en MM:SS
    function formatTime(seconds) {
        if (isNaN(seconds)) return "--:--";

        const minutes = Math.floor(seconds / 60);
        const remainingSeconds = Math.floor(seconds % 60);

        return `${minutes.toString().padStart(2, '0')}:${remainingSeconds.toString().padStart(2, '0')}`;
    }

    // Función para alternar entre play y pause
    function setupPlayPauseToggle() {
        const playButton = document.getElementById('playButton');
        const pauseButton = document.getElementById('pauseButton');
        const audioPlayer = document.getElementById('audioPlayer');

        if (playButton && pauseButton && audioPlayer) {
            // Al hacer clic en play, reproducir audio y actualizar el estado
            playButton.addEventListener('click', function(e) {
                // Prevent default form submission to handle it ourselves
                e.preventDefault();

                // Play the audio
                audioPlayer.play().catch(error => {
                    console.error("Error playing audio:", error);
                });

                // Show pause button, hide play button
                playButton.style.display = 'none';
                pauseButton.style.display = 'block';

                // Update all play/pause states
                updateAllPlayPauseStates(true);

                // Submit the form to update server state
                document.getElementById('playForm').submit();
            });

            // Al hacer clic en pausa, pausar audio y actualizar el estado
            pauseButton.addEventListener('click', function(e) {
                // Prevent default form submission to handle it ourselves
                e.preventDefault();

                // Pause the audio
                audioPlayer.pause();

                // Show play button, hide pause button
                pauseButton.style.display = 'none';
                playButton.style.display = 'block';

                // Update all play/pause states
                updateAllPlayPauseStates(false);

                // Submit the form to update server state
                document.getElementById('pauseForm').submit();
            });
        }
    }

    // Actualizar todos los estados de play/pause
    function updateAllPlayPauseStates(isPlaying) {
        // Obtener el elemento activo
        const activeItem = document.querySelector('.song-item.active');

        if (activeItem) {
            // Actualizar el icono en el elemento activo
            const icon = activeItem.querySelector('.song-play-icon i');
            if (icon) {
                if (isPlaying) {
                    icon.classList.remove('fa-play');
                    icon.classList.add('fa-pause');
                } else {
                    icon.classList.remove('fa-pause');
                    icon.classList.add('fa-play');
                }
            }
        }
    }

    // Manejar clics en los elementos de la lista de canciones
    function setupSongListHandlers() {
        const songItems = document.querySelectorAll('.song-item');
        songItems.forEach(item => {
            item.addEventListener('click', function(e) {
                // Si ya está activo, no hacemos nada
                if (this.classList.contains('active')) {
                    // Pero si se hace clic en el icono, cambiamos entre play/pause
                    if (e.target.closest('.song-play-icon')) {
                        const isPlaying = this.querySelector('.song-play-icon i').classList.contains('fa-pause');

                        if (isPlaying) {
                            // Si está reproduciendo, pausamos
                            document.getElementById('pauseForm').submit();
                        } else {
                            // Si está pausado, reproducimos
                            document.getElementById('playForm').submit();
                        }

                        return;
                    }
                    return;
                }

                // Prevenir el comportamiento predeterminado si se hace clic en el botón de reproducción
                if (e.target.closest('.play-song-button')) {
                    return;
                }

                // Quitar clase active de todos los elementos
                songItems.forEach(i => i.classList.remove('active'));

                // Añadir clase active al elemento clickeado
                this.classList.add('active');

                // Cambiar el icono de reproducción a pausa para el elemento activo
                updatePlayIcons();

                // Activar el formulario de reproducción
                const form = this.querySelector('.play-song-form');
                if (form) {
                    form.submit();
                }
            });
        });
    }

    // Actualizar los iconos de reproducción en la lista de canciones
    function updatePlayIcons() {
        const songItems = document.querySelectorAll('.song-item');
        songItems.forEach(item => {
            const isActive = item.classList.contains('active');
            const icon = item.querySelector('.song-play-icon i');
            if (icon) {
                if (isActive) {
                    icon.classList.remove('fa-play');
                    icon.classList.add('fa-pause');
                } else {
                    icon.classList.remove('fa-pause');
                    icon.classList.add('fa-play');
                }
            }
        });
    }

    // Configurar la actualización de la barra de progreso
    function setupProgressBar() {
        const audioPlayer = document.getElementById('audioPlayer');
        if (audioPlayer) {
            // Update the progress bar as the audio plays
            audioPlayer.addEventListener('timeupdate', function() {
                const currentTime = audioPlayer.currentTime;
                const duration = audioPlayer.duration;

                // Update progress bar
                if (!isNaN(duration)) {
                    const progressFilled = document.querySelector('.progress-filled');
                    if (progressFilled) {
                        progressFilled.style.width = (currentTime / duration) * 100 + '%';
                    }

                    // Update time displays
                    const timeCurrentEl = document.querySelector('.time-current');
                    const timeTotalEl = document.querySelector('.time-total');

                    if (timeCurrentEl) {
                        timeCurrentEl.textContent = formatTime(currentTime);
                    }

                    if (timeTotalEl) {
                        timeTotalEl.textContent = formatTime(duration);
                    }
                }
            });

            // When audio is loaded, update total duration
            audioPlayer.addEventListener('loadedmetadata', function() {
                const timeTotalEl = document.querySelector('.time-total');
                if (timeTotalEl && !isNaN(audioPlayer.duration)) {
                    timeTotalEl.textContent = formatTime(audioPlayer.duration);
                }
            });
        }
    }

    // Inicializar cuando el DOM esté cargado
    document.addEventListener('DOMContentLoaded', function() {
        setupPlayPauseToggle();
        setupSongListHandlers();
        setupProgressBar();

        // Comprobamos si hay una canción activa al cargar la página
        const activeItem = document.querySelector('.song-item.active');
        if (activeItem) {
            const songId = activeItem.getAttribute('data-id');
            console.log('Canción activa al cargar: ' + songId);
        }

        // Si el audio está disponible, aplicamos estilos para ocultarlo visualmente
        const audioContainer = document.querySelector('.audio-player-container');
        if (audioContainer) {
            audioContainer.style.margin = '0';
            audioContainer.style.width = '0';
            audioContainer.style.height = '0';
            audioContainer.style.overflow = 'hidden';

            const audioPlayer = document.getElementById('audioPlayer');
            if (audioPlayer) {
                audioPlayer.style.width = '0';
                audioPlayer.style.height = '0';
                audioPlayer.style.opacity = '0';
                audioPlayer.style.position = 'absolute';

                // Remove controls attribute but keep autoplay
                audioPlayer.removeAttribute('controls');
            }
        }
    });
</script>
</body>
</html>